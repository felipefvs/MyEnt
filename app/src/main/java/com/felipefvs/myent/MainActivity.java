package com.felipefvs.myent;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.felipefvs.myent.database.FirebaseInterface;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.mLogoutItem) {

            FirebaseInterface.getFirebaseAuth().signOut();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            this.startActivity(intent);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }


        /*switch (item.getItemId()) {
            case R.id.mLogoutItem:*/
    }
}

