package com.felipefvs.myent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.felipefvs.myent.model.Ent;

public class EntDetaisActivity extends AppCompatActivity {

    private Ent mEnt;
    private TextView mNameTextView;
    private TextView mSynopsisTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_detais);

        Intent starterIntent = getIntent();

        mNameTextView = findViewById(R.id.mEntNameTextView);
        mSynopsisTextView = findViewById(R.id.mEntSynopsisTextView);

        if (starterIntent != null) {

            if (starterIntent.hasExtra("Ent")) {
                mEnt = starterIntent.getParcelableExtra("Ent");

                mNameTextView.setText(mEnt.getName());
                mSynopsisTextView.setText(mEnt.getSynopsis());
            }
        }
    }
}
