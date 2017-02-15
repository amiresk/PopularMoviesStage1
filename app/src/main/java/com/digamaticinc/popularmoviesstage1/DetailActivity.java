package com.digamaticinc.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.digamaticinc.popularmoviesstage1.models.Movies;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIES = "EXTRA_MOVIES";

    private static final String Popularmovie_SHARE_HASHTAG = " #PopularMoviesApp";

    private ImageView thumbnailImage, postImage;

    private TextView title, date, rate, overview;
    private TextView titleContent, dateContent, rateContent, overviewContent;

    Movies movieContent;// = (Movies) Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);


        thumbnailImage = (ImageView) findViewById(R.id.thumbnail_view_id);
        postImage = (ImageView) findViewById(R.id.poster_view_id);

        title = (TextView) findViewById(R.id.title_text);
        titleContent = (TextView) findViewById(R.id.title_content);

        date = (TextView) findViewById(R.id.date_text);
        dateContent = (TextView) findViewById(R.id.date_content);

        rate = (TextView) findViewById(R.id.rate_text);
        rateContent = (TextView) findViewById(R.id.rate_content);

        overview = (TextView) findViewById(R.id.overview_text);
        overviewContent = (TextView) findViewById(R.id.overview_content);


        //Getting content of movie

        movieContent = getIntent().getExtras().getParcelable(EXTRA_MOVIES);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(movieContent.getTitle());

        //Thumbnail

        String image_url = "http://image.tmdb.org/t/p/w500" + movieContent.getThumbnail();
        Glide.with(this).load(image_url).into(thumbnailImage);

        //Poster

        String image_url2 = "http://image.tmdb.org/t/p/w500" + movieContent.getPosterPath();
        Glide.with(this).load(image_url2).into(postImage);

        //Movie Details

        titleContent.setText(movieContent.getTitle());

        dateContent.setText(movieContent.getReleaseDate());


        // Movie rate

        rateContent.setText(String.valueOf(movieContent.getVoteavarage()));

        overviewContent.setText(movieContent.getOverview());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.detail, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        menuItem.setIntent(createShareForcastIntent());


        return true;
    }

    private Intent createShareForcastIntent() {



        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(movieContent.getTitle() + Popularmovie_SHARE_HASHTAG)
                .getIntent();


        return shareIntent;



    }
}
