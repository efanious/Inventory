package com.android.inventory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.android.inventory.data.ProductContract.ProductEntry;
import com.android.inventory.data.ProductDbHelper;

public class InventoryActivity extends AppCompatActivity {

    private ProductDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new ProductDbHelper(this);
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " + ProductEntry.TABLE_NAME, null);
        try{

            TextView displayView = (TextView) findViewById(R.id.text_view_products);
            displayView.setText("Number of rows in products database table: " + cursor.getCount());
        } finally {

            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertProduct();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                //TODO: Does nothing yet
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertProduct(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Iphone X");
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 10);;
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 700);

        long newRowId = db.insert(ProductEntry.TABLE_NAME, null,values);

        Log.v("InventoryActivity", "New row ID " + newRowId);


    }

}
