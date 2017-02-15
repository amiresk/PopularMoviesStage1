package com.digamaticinc.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.digamaticinc.popularmoviesstage1.adapter.MovieAdapter;
import com.digamaticinc.popularmoviesstage1.models.Movies;
import com.digamaticinc.popularmoviesstage1.utilities.NetWorkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.digamaticinc.popularmoviesstage1.utilities.MovieJsonParser.getPopMoviesFromJson;

/**
 * Created by Amir Eskandari on 1/28/17.
 */



public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{


    private static final String EXTRA_MOVIES = "EXTRA_MOVIES";

    private static final String POPULAR_MOVIE_QUERY = "popular";
    private static final String TOPRATED_MOVIE_QUERY = "top_rated";
    private static final String UPCOMMING_MOVIE_QUERY = "upcoming";

    private TextView mNetworErrorMessage;
    private ProgressBar mLoadingIndicator;

    private RecyclerView mRecyclerView;
    private MovieAdapter adapter;

    private final int viewCacheSize = 20;

    private ArrayList<Movies> mMovies = new ArrayList<>(); // Collections.emptyList();

    Movies movie_obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_action_popmv_content);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mNetworErrorMessage = (TextView) findViewById(R.id.movie_error_message_id);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator_id);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(viewCacheSize);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        getPopularMovies();

        adapter = new MovieAdapter(getApplicationContext(), mMovies, this);

        mRecyclerView.setAdapter(adapter);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        ArrayList<Movies> movies = (ArrayList<Movies>) adapter.getMovies();

        if(movies != null && !movies.isEmpty()){

            outState.putParcelableArrayList(EXTRA_MOVIES, movies);


        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        mMovies = savedInstanceState.getParcelable(EXTRA_MOVIES);

    }


    private void showMovieDataResult(){

        mRecyclerView.setVisibility(View.VISIBLE);
        mNetworErrorMessage.setVisibility(View.INVISIBLE);

    }


    private void showConnectionError(){

        mRecyclerView.setVisibility(View.INVISIBLE);
        mNetworErrorMessage.setVisibility(View.VISIBLE);

    }


    private void getPopularMovies(){


        showMovieDataResult();

        getSupportActionBar().setTitle("Popular Movies");

        new FetchPopularMovies().execute(POPULAR_MOVIE_QUERY);
    }

    private void getTopRatedMovies(){


        showMovieDataResult();

        getSupportActionBar().setTitle("Top Rated Movies");

        new FetchPopularMovies().execute(TOPRATED_MOVIE_QUERY);
    }

    private void getUpCommingMovies(){


        showMovieDataResult();

        getSupportActionBar().setTitle("Upcomming Movies");

        new FetchPopularMovies().execute(UPCOMMING_MOVIE_QUERY);
    }

    @Override
    public void onClick(Movies mv) {

        Context context = this;

        Class<DetailActivity> detailActivityClass = DetailActivity.class;

        movie_obj = new Movies(mv.getTitle(), mv.getPosterPath(), mv.getOverview()
                , mv.getReleaseDate(), mv.getMovieId(), mv.getThumbnail(), mv.getVotecount(),
                mv.getVoteavarage());



        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MOVIES, movie_obj);


        Intent intent = new Intent(context, detailActivityClass).putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.action_popular_movie:

                getPopularMovies();

                return true;

            case R.id.action_toprated_movie:

                getTopRatedMovies();

                return true;

            case R.id.action_upcomming_movie:

                getUpCommingMovies();

                return true;

            default:

                return super.onOptionsItemSelected(item);
        }




    }

    public class FetchPopularMovies extends AsyncTask<String, Void, ArrayList<Movies>> {


        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            mLoadingIndicator.setVisibility(View.VISIBLE);

        }


        @Override
        protected ArrayList<Movies> doInBackground(String... params) {

            String movieJsonString = null;
            if (params == null) {

                return null;
            }

            String movie_query = params[0];

            URL movieRequestURL = NetWorkUtils.buildUrl(movie_query);

            try {

                movieJsonString = NetWorkUtils.getResoponseFromHttpUrl(movieRequestURL);

                return mMovies = getPopMoviesFromJson(movieJsonString);

            } catch (IOException e) {

                e.printStackTrace();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movies> movies) {



            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if(movies == null) {

                showConnectionError();

            }else{

                adapter.setMovieData(movies);


            }


        }
    }
}