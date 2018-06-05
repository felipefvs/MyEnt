package com.felipefvs.myent.model;

import android.util.Log;

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

    public User(String name, String lastname, String email, HashMap<String, String> favorites) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.favorites = favorites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getFavorites() {
        return favorites;
    }

    public void setFavorites(HashMap<String, String> favorites) {
        this.favorites = favorites;
    }
}
