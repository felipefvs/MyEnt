package com.felipefvs.myent.model;

import com.felipefvs.myent.database.FirebaseInterface;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class User {

    String name;
    String lastname;
    String email;
    HashMap<String, String> favorites;

    public User() {
    }

    public User(String name, String lastName, String email, HashMap<String, String> favorites) {
        this.name = name;
        this.lastname = lastName;
        this.email = email;
        this.favorites = favorites;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String mLastName) {
        this.lastname = mLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mEmail) {
        this.email = mEmail;
    }

    public HashMap<String, String> getFavorites() {
        return favorites;
    }

    public void setFavorites(HashMap<String, String> favorites) {
        this.favorites = favorites;
    }
}
