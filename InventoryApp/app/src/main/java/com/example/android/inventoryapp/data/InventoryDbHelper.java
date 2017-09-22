package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by Apoorv on 21-09-2017.
 */

public class InventoryDbHelper extends SQLiteOpenHelper {

    //For log purposes
    private static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();
    //DATABASE NAME and VERSION
    private static final String DATABASE_NAME = "inventory";
    private static final int DATABASE_VERSION = 1;
    //CREATE TABLE STRING
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + InventoryEntry.TABLE_NAME + "(" +
            InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            InventoryEntry.COLUMN_INVENTORY_NAME + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_INVENTORY_CATEGORY + " INTEGER NOT NULL , " +
            InventoryEntry.COLUMN_INVENTORY_PRICE + " REAL NOT NULL DEFAULT 1, " +
            InventoryEntry.COLUMN_INVENTORY_STOCK + " INTEGER NOT NULL, " +
            InventoryEntry.COLUMN_INVENTORY_IMAGE + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL + " TEXT NOT NULL, " +
            InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE + " TEXT NOT NULL" +
            ");";
    //DELETE TABLE STRING
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE" + InventoryEntry.TABLE_NAME + ";";

    //Constructor
    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Execute CREATE query
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Execute DELETE query
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        //Execute CREATE query
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }
}
