package com.android.inventory.data;

import android.provider.BaseColumns;

public final class ProductContract {

    private ProductContract (){}

    /* Inner class that defines the table contents of the products table */
    public static final class ProductEntry implements BaseColumns {


        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_PRICE = "price";



    }
}
