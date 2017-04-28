package com.example.bks.recjsontabtoolbar.respond;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Bks on 4/24/2017.
 */

public class MovieDetail {

    @SerializedName("id")
    private Integer id;

    @SerializedName("generics")
    private ArrayList<Generics> generics=new ArrayList<>();

    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("tagline")
    private String tagline;

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @SerializedName("videos")
    private MovieVideos movieVideos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Generics> getGenerics() {
        return generics;
    }

    public void setGenerics(ArrayList<Generics> generics) {
        this.generics = generics;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public MovieVideos getMovieVideos() {
        return movieVideos;
    }

    public void setMovieVideos(MovieVideos movieVideos) {
        this.movieVideos = movieVideos;
    }
}


