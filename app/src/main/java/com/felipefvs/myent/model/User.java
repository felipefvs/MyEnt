package com.felipefvs.myent.model;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class User {

    String mName;
    String mLastName;
    String mEmail;
    String mPassword;

    public User(String name, String lastName, String email, String password) {
        this.mName = name;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
