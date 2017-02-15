package com.digamaticinc.popularmoviesstage1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.digamaticinc.popularmoviesstage1.R;
import com.digamaticinc.popularmoviesstage1.models.Movies;

import java.util.ArrayList;

/**
 * Created by Amir Eskandari on 1/28/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private Context mContext;

    private ArrayList<Movies> movies = new ArrayList<>();

    final private MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{

        void onClick(Movies mv);
    }

    public MovieAdapter(Context mContext, ArrayList<Movies> mMovies,
                        MovieAdapterOnClickHandler mClickHandler) {

        this.mContext = mContext;
        this.movies = mMovies;
        this.mClickHandler = mClickHandler;

    }

    private Context getContext() {

        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View moviesView = inflater.inflate(R.layout.single_movie_item, parent, false);

        return new ViewHolder(moviesView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies currentMovie = movies.get(position);

        String image_url = "http://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath();

        Glide.with(mContext).load(image_url).into(holder.imageView);


    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public void setMovieData(ArrayList<Movies> mvs){

        movies = mvs;
        notifyDataSetChanged();
    }

    public ArrayList<Movies> getMovies(){

        return movies;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.item_image_id);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            Movies mv = movies.get(adapterPosition);
            mClickHandler.onClick(mv);

        }
    }
}
