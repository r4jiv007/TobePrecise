package com.example.rajiv.precise.activities.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajiv on 15/2/16.
 */
public class Location implements Parcelable {

    private String mLocationName;

    public Location(){

    }

    public Location(String mLocationName) {
        this.mLocationName = mLocationName;
    }

    public String getmLocationName() {
        return mLocationName;
    }

    public void setmLocationName(String mLocationName) {
        this.mLocationName = mLocationName;
    }

    public Location(Parcel in){

        this.mLocationName = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mLocationName);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
