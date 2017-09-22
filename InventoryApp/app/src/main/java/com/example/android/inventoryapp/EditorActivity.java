package com.example.android.inventoryapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mStockEditText;
    private EditText mSupplierNameEditText;
    private EditText mSupplierEmailEditText;
    private EditText mSupplierPhoneEditText;
    private Spinner mCategorySpinner;
    private ImageView mProductImageView;
    private int nameColumnIndex;
    private int categoryColumnIndex;
    private int priceColumnIndex;
    private int stockColumnIndex;
    private int imageColumnIndex;
    private int suppNameColumnIndex;
    private int suppEmailColumnIndex;
    private int suppPhoneColumnIndex;
    String name;
    Integer category;
    Float price;
    Integer stock;
    String image;
    String suppName;
    String suppEmail;
    String suppPhone;

    private int mCategory = 0;
    private Uri mPickedImage;
    private static final int IMAGE_REQUEST_CODE = 108;
    private Uri mCurrentProductUri;
    //Touch listener to detect whether the user has changed some fields
    private boolean mProductHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mProductHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();
        if (mCurrentProductUri == null) {
            setTitle(getString(R.string.add_inventory));
            invalidateOptionsMenu();

        } else {
            setTitle(getString(R.string.edit_inventory));

            //start loader to get data of lareasy existing product
            getLoaderManager().initLoader(1, null, this);
        }
        //Reference all views
        mNameEditText = (EditText) findViewById(R.id.inventory_name);
        mPriceEditText = (EditText) findViewById(R.id.inventory_price);
        mStockEditText = (EditText) findViewById(R.id.inventory_stock);
        mSupplierNameEditText = (EditText) findViewById(R.id.inventory_supplier_name);
        mSupplierEmailEditText = (EditText) findViewById(R.id.inventory_supplier_email);
        mSupplierPhoneEditText = (EditText) findViewById(R.id.inventory_supplier_phone);
        mCategorySpinner = (Spinner) findViewById(R.id.category_spinner);
        mProductImageView = (ImageView) findViewById(R.id.inventory_image);

        //Image picker intent
        mProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
            }
        });
        setOntouch();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, getString(R.string.error_picking_image), Toast.LENGTH_SHORT).show();
                return;
            }
            mPickedImage = data.getData();
            mProductImageView.setImageURI(mPickedImage);
            mProductImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    private void setOntouch() {
        //Setup OnTouchListener on all fields
        mNameEditText.setOnTouchListener(mTouchListener);
        mPriceEditText.setOnTouchListener(mTouchListener);
        mStockEditText.setOnTouchListener(mTouchListener);
        mSupplierNameEditText.setOnTouchListener(mTouchListener);
        mSupplierEmailEditText.setOnTouchListener(mTouchListener);
        mSupplierPhoneEditText.setOnTouchListener(mTouchListener);
        mCategorySpinner.setOnTouchListener(mTouchListener);
        mProductImageView.setOnTouchListener(mTouchListener);
        setUpSpinner();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_CATEGORY,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_IMAGE,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL,
                InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE
        };
        return new CursorLoader(this, mCurrentProductUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            getColumnIndex(cursor);
            extractInfoFromIndex(cursor);
            showInventoryData();
        }
    }

    private void showInventoryData() {
        mNameEditText.setText(name);
        mCategorySpinner.setSelection(category);
        mPriceEditText.setText(String.valueOf(price));
        mStockEditText.setText(String.valueOf(stock));
        Uri uri = Uri.parse(image);
        mProductImageView.setImageURI(uri);
        mPickedImage = uri;
        mProductImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mSupplierNameEditText.setText(suppName);
        mSupplierEmailEditText.setText(suppEmail);
        mSupplierPhoneEditText.setText(suppPhone);
    }

    private void extractInfoFromIndex(Cursor cursor) {
        name = cursor.getString(nameColumnIndex);
        category = cursor.getInt(categoryColumnIndex);
        price = cursor.getFloat(priceColumnIndex);
        stock = cursor.getInt(stockColumnIndex);
        image = cursor.getString(imageColumnIndex);
        suppName = cursor.getString(suppNameColumnIndex);
        suppEmail = cursor.getString(suppEmailColumnIndex);
        suppPhone = cursor.getString(suppPhoneColumnIndex);
    }

    private void getColumnIndex(Cursor cursor) {
        nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME);
        categoryColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_CATEGORY);
        priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE);
        stockColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK);
        imageColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_IMAGE);
        suppNameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
        suppEmailColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL);
        suppPhoneColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveInventory();
                return true;
            case R.id.action_delete:
                QueryUtils.deleteOneInventoryConfirmation(this, mCurrentProductUri);
                return true;
            case android.R.id.home:
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardClickListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }
                };
                QueryUtils.showUnsavedChangesDialog(discardClickListener, this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        // Show dialog that there are unsaved changes
        QueryUtils.showUnsavedChangesDialog(discardButtonClickListener, this);
    }

    private void saveInventory() {
        //Extract new/updated data from fields
        String name = mNameEditText.getText().toString().trim();
        int category = mCategory;
        String priceString = mPriceEditText.getText().toString().trim();
        String stockString = mStockEditText.getText().toString().trim();
        String image = null;
        if (mCurrentProductUri == null) {
            if (mPickedImage != null) image = mPickedImage.toString();
        } else image = mPickedImage.toString();
        String suppName = mSupplierNameEditText.getText().toString().trim();
        String suppEmail = mSupplierEmailEditText.getText().toString().trim();
        String suppPhone = mSupplierPhoneEditText.getText().toString().trim();
        float price;
        int stock;
        try {
            price = Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            price = 0;
        }
        try {
            stock = Integer.parseInt(stockString);
        } catch (NumberFormatException e) {
            stock = 0;
        }

        //Check if field is empty. And show proper message to fill them.
        if (TextUtils.isEmpty(name)) {
            mNameEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(priceString)) {
            mPriceEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(stockString)) {
            mStockEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(image)) {
            Toast.makeText(this, getString(R.string.field_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(suppName)) {
            mSupplierNameEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(suppEmail)) {
            mSupplierEmailEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        if (TextUtils.isEmpty(suppPhone)) {
            mSupplierPhoneEditText.setError(getString(R.string.field_cannot_be_empty));
            return;
        }
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME, name);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_CATEGORY, category);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE, price);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK, stock);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_IMAGE, image);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME, suppName);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL, suppEmail);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE, suppPhone);
        if (mCurrentProductUri == null) {
            Uri uri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI, values);
            if (uri == null) {
                Toast.makeText(this, getString(R.string.error_saving_inventory), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.successfully_saved_inventory), Toast.LENGTH_SHORT).show();
            }
        } else {
            int numRows = getContentResolver().update(mCurrentProductUri, values, null, null);

            if (numRows > 0) {
                Toast.makeText(this, getString(R.string.successfully_updated_inventory), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.error_updating_inventory), Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    private void setUpSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_category_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mCategorySpinner.setAdapter(genderSpinnerAdapter);
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.category_clothing))) {
                        mCategory = InventoryContract.InventoryEntry.CATEGORY_CLOTHING;
                    } else if (selection.equals(getString(R.string.category_electronics))) {
                        mCategory = InventoryContract.InventoryEntry.CATEGORY_ELECTRONICS;
                    } else if (selection.equals(getString(R.string.category_household))) {
                        mCategory = InventoryContract.InventoryEntry.CATEGORY_HOUSEHOLD;
                    } else {
                        mCategory = InventoryContract.InventoryEntry.CATEGORY_OTHERS;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCategory = 0;
            }
        });
    }

}
