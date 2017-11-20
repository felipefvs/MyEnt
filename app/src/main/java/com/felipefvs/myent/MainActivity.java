package com.felipefvs.myent;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.felipefvs.myent.adapter.EntAdapter;
import com.felipefvs.myent.database.FirebaseInterface;
import com.felipefvs.myent.model.Ent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Ent> mEntList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private EntAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.mRecyclerView);

        mAdapter = new EntAdapter(mEntList);

        mRecyclerView.setAdapter(mAdapter);

        prepareData();
    }

    private void prepareData() {
        Ent movie = new Ent("Mad Max: Fury Road");
        mEntList.add(movie);

        movie = new Ent("Inside Out");
        mEntList.add(movie);

        movie = new Ent("Star Wars: Episode VII - The Force Awakens");
        mEntList.add(movie);

        movie = new Ent("Shaun the Sheep");
        mEntList.add(movie);

        movie = new Ent("The Martian");
        mEntList.add(movie);

        movie = new Ent("Mission: Impossible Rogue Nation");
        mEntList.add(movie);

        movie = new Ent("Up");
        mEntList.add(movie);

        movie = new Ent("Star Trek");
        mEntList.add(movie);

        movie = new Ent("The LEGO Movie");
        mEntList.add(movie);

        movie = new Ent("Iron Man");
        mEntList.add(movie);

        movie = new Ent("Aliens");
        mEntList.add(movie);

        movie = new Ent("Chicken Run");
        mEntList.add(movie);

        movie = new Ent("Back to the Future");
        mEntList.add(movie);

        movie = new Ent("Raiders of the Lost Ark");
        mEntList.add(movie);

        movie = new Ent("Goldfinger");
        mEntList.add(movie);

        movie = new Ent("Guardians of the Galaxy");
        mEntList.add(movie);

        mAdapter.notifyDataSetChanged();
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

