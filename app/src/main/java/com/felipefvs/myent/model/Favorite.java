package com.felipefvs.myent.model;

public class Favorite {

    int entId;
    String entName;

    public Favorite() {
    }

    public Favorite(int entId, String entName) {
        this.entId = entId;
        this.entName = entName;
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }
}
