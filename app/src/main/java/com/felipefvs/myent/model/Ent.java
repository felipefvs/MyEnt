package com.felipefvs.myent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class Ent  implements Parcelable {

    int    mId;
    String mName;
    String mSynopsis;

    public Ent() {
    }

    public Ent(int id, String name, String synopsis) {
        this.mId = mId;
        this.mName = name;
        this.mSynopsis = synopsis;
    }

    private Ent(Parcel in)
    {
        this.mId = in.readInt();
        this.mName = in.readString();
        this.mSynopsis = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String synopsis) {
        this.mSynopsis = synopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mSynopsis);

    }

    public static final Parcelable.Creator<Ent> CREATOR = new Parcelable.Creator<Ent>()
    {
        @Override
        public Ent createFromParcel(Parcel parcel)
        {
            return new Ent(parcel);
        }

        @Override
        public Ent[] newArray(int i) {
            return new Ent[i];
        }
    };
}
