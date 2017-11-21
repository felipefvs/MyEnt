package com.felipefvs.myent.model;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class Ent {

    int    mId;
    String mName;

    public Ent() {
    }

    public Ent(int id, String name) {
        this.mId = mId;
        this.mName = name;
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
}
