package com.example.bks.recjsontabtoolbar.respond;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bks on 4/24/2017.
 */

public class MovieVideos {

    @SerializedName("results")
    private ArrayList<MovieResult> movieResults= new ArrayList<>();

    public ArrayList<MovieResult> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(ArrayList<MovieResult> movieResults) {
        this.movieResults = movieResults;
    }
}
