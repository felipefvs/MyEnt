package com.felipefvs.myent.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by felip on 14/11/2017.
 */

public class FirebaseInterface {

    private static DatabaseReference reference;
    private static FirebaseAuth auth;

    public static DatabaseReference getFirebase(){

        if( reference == null ){
            reference = FirebaseDatabase.getInstance().getReference();
        }

        return reference;
    }

    public static FirebaseAuth getFirebaseAuth(){
        if( auth == null ){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static boolean isConnected() {
        return auth.getCurrentUser() != null;
    }

}
