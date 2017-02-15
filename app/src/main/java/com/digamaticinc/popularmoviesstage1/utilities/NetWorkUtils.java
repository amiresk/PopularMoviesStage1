package com.digamaticinc.popularmoviesstage1.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Amir Eskandari on 1/28/17.
 */


public class NetWorkUtils implements Config{

    private static final String TAG = NetWorkUtils.class.getSimpleName();


    public static URL buildUrl(String movieQuery){

        Uri.Builder builder = new Uri.Builder();

        builder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movieQuery)
                .appendQueryParameter("api_key", Movie_API_KEY);


        URL url = null;

        try {

            url = new URL(builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;

    }



    public static String getResoponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{


            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);

            // Read the entire content to the next string...
            // (Automaticly buffering deferent sizes, transfor UTF-8(json) to UTF-16(android))

            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput){

                return scanner.next();
            }else{

                return null;
            }

        }finally {

            urlConnection.disconnect();
        }




    }

}
