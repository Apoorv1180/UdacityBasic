package com.example.android.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Apoorv on 21-09-2017.
 */

public class InventoryProvider extends ContentProvider {
    private static final String LOG_TAG = InventoryProvider.class.getSimpleName();
    private static final int PRODUCT = 100;
    private static final int PRODUCT_ID = 101;
    private InventoryDbHelper inventoryDbHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //URI MATCHER FOR INDIVIDUAL AND ALL PRODUCTS
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.INVENTORY_PATH, PRODUCT);
        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.INVENTORY_PATH + "/#", PRODUCT_ID);
    }

    @Override
    public boolean onCreate() {
        inventoryDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = inventoryDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);
        //Result in cursor in accordance to the uri matched as 100 or 101
        switch (match) {
            case PRODUCT:
                cursor = db.query(InventoryContract.InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PRODUCT_ID:
                selection = InventoryContract.InventoryEntry._ID + "=?";
                selectionArgs = new String[]{uri.getLastPathSegment()};
                cursor = db.query(InventoryContract.InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown Query" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PRODUCT:
                return InventoryContract.InventoryEntry.CONTENT_ALL_INVENTORY;
            case PRODUCT_ID:
                return InventoryContract.InventoryEntry.CONTENT_ONE_INVENTORY;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case PRODUCT:
                return insertInventory(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = inventoryDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case PRODUCT:
                rowsDeleted = db.delete(InventoryContract.InventoryEntry.TABLE_NAME, s, strings);
                break;
            case PRODUCT_ID:
                s = InventoryContract.InventoryEntry._ID + "=?";
                strings = new String[]{uri.getLastPathSegment()};
                rowsDeleted = db.delete(InventoryContract.InventoryEntry.TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for" + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case PRODUCT:
                return updateInventory(uri, contentValues, s, strings);
            case PRODUCT_ID:
                s = InventoryContract.InventoryEntry._ID + "=?";
                strings = new String[]{uri.getLastPathSegment()};
                return updateInventory(uri, contentValues, s, strings);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    //Function to check whether values entered are correct or not null
    private void checkValidityOfValues(ContentValues values) {
        if (values.containsKey(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME)) {
            String name = values.getAsString(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME);
            Integer category = values.getAsInteger(InventoryContract.InventoryEntry.COLUMN_INVENTORY_CATEGORY);
            Float price = values.getAsFloat(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE);
            Integer stock = values.getAsInteger(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK);
            String image = values.getAsString(InventoryContract.InventoryEntry.COLUMN_INVENTORY_IMAGE);
            String suppName = values.getAsString(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
            String suppEmail = values.getAsString(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL);
            String suppPhone = values.getAsString(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE);
            if (name == null) {
                throw new IllegalArgumentException("Product requires name");
            }
            if (category == null || !InventoryContract.InventoryEntry.isValidCategory(category)) {
                throw new IllegalArgumentException("Invalid category");
            }
            if (price != null && price < 0) {
                throw new IllegalArgumentException("Product requires valid price");
            }
            if (stock != null && stock < 0) {
                throw new IllegalArgumentException("Invalid stock");
            }
            if (image == null) {
                throw new IllegalArgumentException("Product requires image");
            }
            if (suppName == null) {
                throw new IllegalArgumentException("Product requires supplier name");
            }
            if (suppEmail == null) {
                throw new IllegalArgumentException("Product requires supplier email");
            }
            if (suppPhone == null) {
                throw new IllegalArgumentException("Product requires supplier phone");
            }
        }
    }

    //insert inventory method
    private Uri insertInventory(Uri uri, ContentValues values) {
        //Check valid insertion
        checkValidityOfValues(values);
        //Get Writabale database
        SQLiteDatabase database = inventoryDbHelper.getWritableDatabase();
        long id = database.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.d(LOG_TAG, "Failed to insert row for " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    //update inventory method
    private int updateInventory(Uri uri, ContentValues contentValues, String s, String[] strings) {

        //Check validity of values
        checkValidityOfValues(contentValues);

        //Return early if no data is changed
        if (contentValues.size() == 0) {
            return 0;
        }
        //get writabale database
        SQLiteDatabase database = inventoryDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(InventoryContract.InventoryEntry.TABLE_NAME, contentValues, s, strings);
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

}
