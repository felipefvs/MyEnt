package com.felipefvs.myent.utils;

import android.os.AsyncTask;

import com.felipefvs.myent.model.EntItem;

/**
 * Created by felip on 13/11/2017.
 */

public class GetEntDataTask extends AsyncTask<String, Void, EntItem[]> {

    @Override
    protected EntItem[] doInBackground(String... strings) {
        return new EntItem[0];
    }

}
