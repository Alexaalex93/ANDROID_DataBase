package com.example.cice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cice on 28/3/17.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "mId";
    public static final String COLUMN_PRODUCT_NAME = "mProductName";
    public static final String COLUMN_QUANTITY = "quantity";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCT_NAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS); //Elimina la tabla anterior y la crea si la version es mas nueva
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, product.getmProductName());
        contentValues.put(COLUMN_QUANTITY, product.getmQuantity());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, contentValues);
        db.close();

    }

    public Product findProduct(String productName){
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCT_NAME + " = '" + productName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();//Vas a la priemra posicion
            product.setmId(cursor.getInt(0));
            product.setmProductName(cursor.getString(1));
            product.setmQuantity(cursor.getInt(2));
            cursor.close();
        } else {
            product = null;
        }
        db.close();

        return product;
    }

    public boolean deleteProduct(String productName){
        boolean result = false;
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCT_NAME + " = '" + productName +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Product product = new Product();
        if(cursor.moveToFirst()){
            product.setmId(cursor.getInt(0));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{ String.valueOf(product.getmId())});
        }
        db.close();
        return result;
    }
}
