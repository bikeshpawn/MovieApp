package com.example.bks.recjsontabtoolbar.rest;

import com.example.bks.recjsontabtoolbar.respond.MovieDetail;
import com.example.bks.recjsontabtoolbar.respond.MovieListing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Bks on 4/21/2017.
 */

interface MovieListingService {
    @GET("upcoming")
    Call<MovieListing> getUpcomingMovies(@Query("api_key") String apiKey);
    //Query is url manipulators , Path, body, full, part

    @GET("{id}?append_to_response=videos")
    Call<MovieDetail> getMovieMoreDetails(@Path("id") String movieId, @Query("api_key") String apiKey);
}
