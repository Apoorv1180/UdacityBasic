package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    private TextView mInventoryName;
    private TextView mInventoryCategory;
    private TextView mInventoryPrice;
    private TextView mInventoryStock;
    private TextView mInventorySupplierName;
    private TextView mInventorySupplierEmail;
    private TextView mInventorySupplierPhone;
    private ImageView mImageView;
    private int inventoryNameIndex;
    private int inventoryCategoryIndex;
    private int inventoryPriceIndex;
    private int inventoryStockIndex;
    private int inventoryImageIndex;
    private int suppNameIndex;
    private int suppEmailIndex;
    private int suppPhoneIndex;
    private String name;
    private Integer category;
    private Float price;
    private Integer stock;
    private String image;
    private String suppName;
    private String suppEmail;
    private String suppPhone;
    private ImageButton mDecreaseStockButton;
    private ImageButton mIncreaseStockButton;
    private ImageButton mEmailSupplierButton;
    private ImageButton mOhoneSupplierButton;
    private Uri mCurrentInventoryUri;
    private static int LOADER_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Reference views and set OnClickListner
        mInventoryName = (TextView) findViewById(R.id.inventory_name_text);
        mInventoryCategory = (TextView) findViewById(R.id.inventory_category_text);
        mInventoryPrice = (TextView) findViewById(R.id.inventory_price_text);
        mInventoryStock = (TextView) findViewById(R.id.inventory_stock_text);
        mInventorySupplierName = (TextView) findViewById(R.id.inventory_supplier_name_text);
        mInventorySupplierEmail = (TextView) findViewById(R.id.inventory_supplier_email_text);
        mInventorySupplierPhone = (TextView) findViewById(R.id.inventory_supplier_phone_text);
        mImageView = (ImageView) findViewById(R.id.image_detail_text);

        mIncreaseStockButton = (ImageButton) findViewById(R.id.inventory_stock_increase_button);
        mDecreaseStockButton = (ImageButton) findViewById(R.id.inventory_stock_decrease_button);
        mEmailSupplierButton = (ImageButton) findViewById(R.id.inventory_supplier_email_button);
        mOhoneSupplierButton = (ImageButton) findViewById(R.id.inventory_supplier_phone_button);
        Intent intent = getIntent();
        mCurrentInventoryUri = intent.getData();

        mDecreaseStockButton.setOnClickListener(this);
        mIncreaseStockButton.setOnClickListener(this);
        mEmailSupplierButton.setOnClickListener(this);
        mOhoneSupplierButton.setOnClickListener(this);
        //Start loader
        getLoaderManager().initLoader(LOADER_ID, null, this);

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
        return new CursorLoader(this, mCurrentInventoryUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        //If cursor has data move to first and read
        if (cursor.moveToFirst()) {
            //Get index of column in cursor
            getColumnIndex(cursor);
            //Get data from column using column index and store them in variables
            getDatafromIndex(cursor);
            //Present the data to user
            mInventoryName.setText(name);
            String categoryString;
            if (category == InventoryContract.InventoryEntry.CATEGORY_CLOTHING)
                categoryString = getString(R.string.category_clothing);
            else if (category == InventoryContract.InventoryEntry.CATEGORY_ELECTRONICS)
                categoryString = getString(R.string.category_electronics);
            else if (category == InventoryContract.InventoryEntry.CATEGORY_HOUSEHOLD)
                categoryString = getString(R.string.category_household);
            else categoryString = getString(R.string.category_others);
            mInventoryCategory.setText(categoryString);
            mInventoryPrice.setText(String.valueOf(price));
            mInventoryStock.setText(String.valueOf(stock));
            Uri uri = Uri.parse(image);
            mImageView.setImageURI(uri);
            mInventorySupplierName.setText(suppName);
            mInventorySupplierEmail.setText(suppEmail);
            mInventorySupplierPhone.setText(suppPhone);
        }

    }

    private void getDatafromIndex(Cursor cursor) {
        name = cursor.getString(inventoryNameIndex);
        category = cursor.getInt(inventoryCategoryIndex);
        price = cursor.getFloat(inventoryPriceIndex);
        stock = cursor.getInt(inventoryStockIndex);
        image = cursor.getString(inventoryImageIndex);
        suppName = cursor.getString(suppNameIndex);
        suppEmail = cursor.getString(suppEmailIndex);
        suppPhone = cursor.getString(suppPhoneIndex);
    }

    private void getColumnIndex(Cursor cursor) {
        inventoryNameIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME);
        inventoryCategoryIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_CATEGORY);
        inventoryPriceIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE);
        inventoryStockIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK);
        inventoryImageIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_IMAGE);
        suppNameIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
        suppEmailIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_EMAIL);
        suppPhoneIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE);
    }

    private void changeStockValue(int value) {
        //Get the previous value first
        int prevValue = Integer.valueOf(mInventoryStock.getText().toString());
        if ((prevValue > 0) || (prevValue >= 0 && value > 0)) {
            //Add the new value. Make key-pair data and update
            value += prevValue;
            ContentValues contentValues = new ContentValues();
            contentValues.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK, value);
            getContentResolver().update(mCurrentInventoryUri, contentValues, null, null);
        }
    }

    private void emailSupplier() {
        String email = mInventorySupplierEmail.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{ email} );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.no_email_receiptant), Toast.LENGTH_SHORT).show();
        }
    }

    private void callSupplier() {
        String phoneNumber = mInventorySupplierPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mInventoryName.setText("");
        mInventoryCategory.setText("");
        mInventoryPrice.setText("");
        mInventoryStock.setText("");
        mImageView.setImageURI(null);
        mInventorySupplierName.setText("");
        mInventorySupplierEmail.setText("");
        mInventorySupplierPhone.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(DetailsActivity.this, EditorActivity.class);
                intent.setData(mCurrentInventoryUri);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                QueryUtils.deleteOneInventoryConfirmation(this, mCurrentInventoryUri);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.inventory_stock_decrease_button:
                changeStockValue(-1);
                break;
            case R.id.inventory_stock_increase_button:
                changeStockValue(1);
                break;
            case R.id.inventory_supplier_email_button:
                emailSupplier();
                break;
            case R.id.inventory_supplier_phone_button:
                callSupplier();
                break;
        }
    }
}
