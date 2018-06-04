package com.felipefvs.myent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.felipefvs.myent.adapter.EntAdapter;

import com.felipefvs.myent.database.FirebaseInterface;
import com.felipefvs.myent.model.Ent;
import com.felipefvs.myent.network.JSONUtilities;
import com.felipefvs.myent.network.TheMovieDBInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements EntAdapter.OnItemClickListener {

    private List<Ent> mEntList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private EntAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.mListRecyclerView);

        mAdapter = new EntAdapter(mEntList);
        mAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        new FetchEntDataTask().execute("/top_rated");
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

    @Override
    public void onItemClick(View v, int position) {
        ImageView imgView = (ImageView)v.findViewById(R.id.mImageButton);

        imgView.setImageResource(R.drawable.ic_star_filled);
    }

    private class FetchEntDataTask extends AsyncTask<String, Void, Ent[]>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected Ent[] doInBackground(String... params) {

            Ent[] parsedData = new ArrayList<Ent>().toArray(new Ent[0]);

            /* If there's no url, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String endPoint = params[0];
            URL requestUrl = TheMovieDBInterface.buildUrl(endPoint);

            try
            {
                String jsonWeatherResponse = TheMovieDBInterface
                        .getResponseFromHttpUrl(requestUrl);

                return JSONUtilities.getEntDataFromJson(jsonWeatherResponse);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Ent[] ents) {
            super.onPostExecute(ents);

            if ((ents != null) && (ents.length >= 0))
            {
                //showMoviePostersView();
                mAdapter.setEnts(Arrays.asList(ents));
                mRecyclerView.setAdapter(mAdapter);
            }
            //else
              //  showErrorMessage();
        }
    }
}

