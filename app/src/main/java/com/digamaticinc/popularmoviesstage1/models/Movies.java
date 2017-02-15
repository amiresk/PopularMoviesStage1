package com.digamaticinc.popularmoviesstage1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amhoes on 1/28/17.
 */


public class Movies implements Parcelable {



    private String mTitle;          //original_title
    private String mPosterPath;     //poster_path

    private String mOverview;       // overview
    private String mReleaseDate;    //release_date
    private int mMovieId;           //id
    private String mThumbnail;      //backdrop_path
    private int mVotecount;         //vote_count
    private double mVoteavarage;      //vote_average

    private int[] mGenre;

    public Movies(){

    }

    public Movies(String mTitle, String mPosterPath) {
        this.mTitle = mTitle;
        this.mPosterPath = mPosterPath;
    }

    public Movies(String mTitle, String mPosterPath, String mOverview, String mReleaseDate
            , int mMovieId, String mThumbnail, int mVotecount, double mVoteavarage) {

        this.mTitle = mTitle;
        this.mPosterPath = mPosterPath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mMovieId = mMovieId;
        this.mThumbnail = mThumbnail;
        this.mVotecount = mVotecount;
        this.mVoteavarage = mVoteavarage;
    }

    public Movies(String mTitle, String mPosterPath, String mOverview, String mReleaseDate
            , int mMovieId, String mThumbnail, int mVotecount, double mVoteavarage, int[] mGenre) {
        this.mTitle = mTitle;
        this.mPosterPath = mPosterPath;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
        this.mMovieId = mMovieId;
        this.mThumbnail = mThumbnail;
        this.mVotecount = mVotecount;
        this.mVoteavarage = mVoteavarage;
        this.mGenre = mGenre;
    }



    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int mMovieId) {
        this.mMovieId = mMovieId;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public int getVotecount() {
        return mVotecount;
    }

    public void setVotecount(int mVotecount) {
        this.mVotecount = mVotecount;
    }

    public double getVoteavarage() {
        return mVoteavarage;
    }

    public void setVoteavarage(double mVoteavarage) {
        this.mVoteavarage = mVoteavarage;
    }

    public int[] getGenre() {
        return mGenre;
    }

    public void setGenre(int[] mGenre) {
        this.mGenre = mGenre;
    }

    public Movies(Parcel in){

        this.mTitle = in.readString();
        this.mPosterPath = in.readString();
        this.mOverview = in.readString();
        this.mReleaseDate = in.readString();
        this.mMovieId = in.readInt();
        this.mThumbnail = in.readString();
        this.mVoteavarage = in.readDouble();

        //this.mGenre = in.createIntArray();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        //dest.writeStringArray(new String[]{this.mPosterPath, this.mTitle});
        dest.writeString(this.mTitle);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mOverview);
        dest.writeString(this.mReleaseDate);
        dest.writeInt(this.mMovieId);
        dest.writeString(this.mThumbnail);
        dest.writeDouble(this.mVoteavarage);

       // dest.writeIntArray(this.mGenre);

    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {

            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {

            return new Movies[size];
        }
    };


}