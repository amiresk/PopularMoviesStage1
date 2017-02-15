package com.digamaticinc.popularmoviesstage1.utilities;

import com.digamaticinc.popularmoviesstage1.models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amir Eskandari on 1/28/17.
 */

public final class MovieJsonParser{


    public static ArrayList<Movies> getPopMoviesFromJson(String popmoviesJsonStr) throws JSONException {



        final String OWN_RESULT = "results";
        final String OWN_TITLE = "original_title";
        final String OWN_POSTER = "poster_path";

        final String OWN_Overview = "overview";
        final String OWN_ReleaseDate = "release_date";
        final String OWN_MovieId = "id";
        final String OWN_Thumbnail = "backdrop_path";
        final String OWN_Votecount = "vote_count";
        final String OWN_Voteavarage = "vote_average";

        final String OWN_Genre_ids = "genre_ids";


        ArrayList<Movies> movieList = new ArrayList<>();

        JSONObject popmovieJson = new JSONObject(popmoviesJsonStr);

        JSONArray resultArray = popmovieJson.getJSONArray(OWN_RESULT);

        for (int i = 0; i < resultArray.length(); i++) {

            JSONObject movie = resultArray.getJSONObject(i);



            movieList.add(new Movies(movie.getString(OWN_TITLE)
                    , movie.getString(OWN_POSTER)
                    , movie.getString(OWN_Overview)
                    , movie.getString(OWN_ReleaseDate)
                    , movie.getInt(OWN_MovieId)
                    , movie.getString(OWN_Thumbnail)
                    , movie.getInt(OWN_Votecount)
                    , movie.getDouble(OWN_Voteavarage)));

        }

        return movieList;

    }


}
