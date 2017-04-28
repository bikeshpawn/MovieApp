package com.example.bks.recjsontabtoolbar.rest;

import com.example.bks.recjsontabtoolbar.respond.MovieDetail;
import com.example.bks.recjsontabtoolbar.respond.MovieListing;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bks on 4/21/2017.
 */

public class RetrofitManager {
    public static Retrofit retrofit = null;
    public static MovieListingService movieListingService = null;
    public static RetrofitManager retroFitManager = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private RetrofitManager(){
        HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client= new OkHttpClient.Builder();

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        movieListingService=retrofit.create(MovieListingService.class);
    }

    public static RetrofitManager getInstance(){
        if (retroFitManager==null){
            retroFitManager=new RetrofitManager();
        }
        return retroFitManager;
    }

    public static void getUpcomingMovieList(String apiKey, retrofit2.Callback<MovieListing> getMovieListingCallback){
        Call<MovieListing> getMovieListing =movieListingService.getUpcomingMovies(apiKey);
        getMovieListing.enqueue(getMovieListingCallback);
    }

    public static void getMovieMoreDetail(String movieID,String apiKey, retrofit2.Callback<MovieDetail> getMovieDetailCallback) {
        Call<MovieDetail> getMovieMoreDetails = movieListingService.getMovieMoreDetails(movieID, apiKey);
        getMovieMoreDetails.enqueue(getMovieDetailCallback);
    }
}

