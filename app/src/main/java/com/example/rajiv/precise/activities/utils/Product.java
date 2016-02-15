package com.example.rajiv.precise.activities.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajiv on 15/2/16.
 */
public class Product implements Parcelable {

    private String mTitle;
    private String mImageUrl;
    private String mProductUrl;

    public Product(String mTitle, String mImageUrl, String mProductUrl) {
        this.mTitle = mTitle;
        this.mImageUrl = mImageUrl;
        this.mProductUrl = mProductUrl;
    }

    public Product(){

    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmProductUrl() {
        return mProductUrl;
    }

    public void setmProductUrl(String mProductUrl) {
        this.mProductUrl = mProductUrl;
    }



    public Product(Parcel in){

        this.mTitle = in.readString();
        this.mImageUrl =  in.readString();
        this.mProductUrl =  in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mTitle);
        dest.writeString(mImageUrl);
        dest.writeString(mProductUrl);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
