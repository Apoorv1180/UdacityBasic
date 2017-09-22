package com.example.android.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract;

/**
 * Created by Apoorv on 21-09-2017.
 */

public class QueryUtils {
    //delete all inventory
    private static void deleteAllInventory(Context context) {
        int numberOfRows = context.getContentResolver().delete(InventoryContract.InventoryEntry.CONTENT_URI, null, null);
        if (numberOfRows > 0) {
            Toast.makeText(context, context.getString(R.string.delete_all).replace("#", String.valueOf(numberOfRows)), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.toast_error_deleting), Toast.LENGTH_SHORT).show();
        }
    }

    //delete one inventory
    private static void deleteOneInventory(Activity ctx, Uri uri) {
        int selectDeletedRow = ctx.getContentResolver().delete(uri, null, null);
        if (selectDeletedRow == 0) {
            Toast.makeText(ctx, ctx.getString(R.string.editor_delete_product_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, ctx.getString(R.string.editor_delete_product_successful),
                    Toast.LENGTH_SHORT).show();
        }
        ctx.finish();
    }

    //
    //Confirmation Dialogue boxes
    //

    //For all delete
    public static void deleteAllInventoryConfirmation(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_all_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAllInventory(context);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //For one delete
    public static void deleteOneInventoryConfirmation(final Activity context, final Uri uri) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteOneInventory(context, uri);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Show Unsaved or Discard message
    public static void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
