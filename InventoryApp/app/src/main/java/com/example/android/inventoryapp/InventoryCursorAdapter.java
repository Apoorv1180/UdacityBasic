package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;


public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_list, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        final int inventoryID = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry._ID));
        String inventoryName = cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME));
        float inventoryPrice = cursor.getFloat(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE));
        final int inventoryStock = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK));


        TextView inventoryNameText = view.findViewById(R.id.inventory_name_id);
        TextView inventoryPriceText = view.findViewById(R.id.inventory_price_id);
        TextView inventoryStockText = view.findViewById(R.id.inventory_stock_id);

        inventoryNameText.setText(inventoryName);
        inventoryPriceText.setText(String.valueOf(inventoryPrice));
        inventoryStockText.setText(String.valueOf(inventoryStock));
        ImageButton saleButton = view.findViewById(R.id.sell_button);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inventoryStock > 0) {
                    int newStock = inventoryStock - 1;
                    Uri productUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, inventoryID);
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_STOCK, newStock);
                    context.getContentResolver().update(productUri, values, null, null);
                    Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, context.getString(R.string.no_stock), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
