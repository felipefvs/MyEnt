package com.felipefvs.myent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by felip on 13/11/2017.
 */

public class EntItem implements Parcelable{

    String name;

    public EntItem(String name) {
        this.name = name;
    }

    private EntItem(Parcel in)
    {
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);

    }

    public static final Creator<EntItem> CREATOR = new Creator<EntItem>() {
        @Override
        public EntItem createFromParcel(Parcel parcel) {
            return new EntItem(parcel);
        }

        @Override
        public EntItem[] newArray(int i) {
            return new EntItem[0];
        }
    };
}
