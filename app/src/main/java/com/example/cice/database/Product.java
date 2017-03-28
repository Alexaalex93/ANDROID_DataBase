package com.example.cice.database;

/**
 * Created by cice on 28/3/17.
 */

public class Product {

    private int mId;
    private String mProductName;
    private int mQuantity;

    public Product(){}

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public Product(int mId, String mProductName, int mQuantity) {
        this.mId = mId;
        this.mProductName = mProductName;
        this.mQuantity = mQuantity;
    }

    public Product(String mProductName, int mQuantity) {
        this.mProductName = mProductName;
        this.mQuantity = mQuantity;
    }
}
