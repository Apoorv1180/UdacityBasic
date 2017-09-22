package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Apoorv on 21-09-2017.
 */

public class InventoryContract {

    //Content authority name
    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp";
    //Database Path
    public static final String INVENTORY_PATH = "inventory";
    //Base Uri
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static abstract class InventoryEntry implements BaseColumns {

        //Uri to access database value
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, INVENTORY_PATH);

        //URI to access all list of products
        public static final String CONTENT_ALL_INVENTORY =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + INVENTORY_PATH;

        //URI to access a particular data item
        public static final String CONTENT_ONE_INVENTORY =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + INVENTORY_PATH;

        //DECLARE TABLE NAME AND COLUMN NAMES
        //Table name
        public static final String TABLE_NAME = "product";
        //Column name
        public static final String _ID = BaseColumns._ID;
        //Name of Product
        public static final String COLUMN_INVENTORY_NAME = "inventory_name";
        //Category of Product
        public static final String COLUMN_INVENTORY_CATEGORY = "inventory_category";
        //Price of Product
        public static final String COLUMN_INVENTORY_PRICE = "inventory_price";
        //Stock of Product
        public static final String COLUMN_INVENTORY_STOCK = "inventory_stock";
        //Image of Product
        public static final String COLUMN_INVENTORY_IMAGE = "inventory_image";
        //Supplier name of Product
        public static final String COLUMN_INVENTORY_SUPPLIER_NAME = "inventory_supplier_name";
        //Supplier email of Product
        public static final String COLUMN_INVENTORY_SUPPLIER_EMAIL = "inventory_supplier_email";
        //Supplier phone number of product
        public static final String COLUMN_INVENTORY_SUPPLIER_PHONE = "inventory_supplier_phone";

        //Constants for category of Inventory
        public static final int CATEGORY_CLOTHING = 0;
        public static final int CATEGORY_ELECTRONICS = 1;
        public static final int CATEGORY_HOUSEHOLD = 2;
        public static final int CATEGORY_OTHERS = 3;

        //CHECK VALID CATEGORIES
        public static boolean isValidCategory(int category) {
            if (category == CATEGORY_CLOTHING || category == CATEGORY_ELECTRONICS || category == CATEGORY_HOUSEHOLD || category == CATEGORY_OTHERS) {
                return true;
            }
            return false;
        }


    }


}
