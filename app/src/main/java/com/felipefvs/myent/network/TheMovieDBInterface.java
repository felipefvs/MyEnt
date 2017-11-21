package com.felipefvs.myent.network;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by FELIPESIQUEIRAB20588 on 21/11/2017.
 */

public class TheMovieDBInterface {

    private static final String TAG = TheMovieDBInterface.class.getSimpleName();

    private static final String api = "api_key";
    private static final String key = "2ff9ebe7abfb5218014180e75ca2d711";
    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie";

    public static URL buildUrl(String endpoint) {
        Uri builtUri = Uri.parse(BASE_MOVIE_URL + endpoint).buildUpon()
                .appendQueryParameter(api, key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try
        {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        finally
        {
            urlConnection.disconnect();
        }
    }

}
