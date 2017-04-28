package com.example.bks.recjsontabtoolbar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bks.recjsontabtoolbar.BuildConfig;
import com.example.bks.recjsontabtoolbar.R;
import com.example.bks.recjsontabtoolbar.activity.DetailActivity;
import com.example.bks.recjsontabtoolbar.activity.MainActivity;
import com.example.bks.recjsontabtoolbar.adapter.MovieListingAdapter;
import com.example.bks.recjsontabtoolbar.respond.MovieListing;
import com.example.bks.recjsontabtoolbar.respond.Result;
import com.example.bks.recjsontabtoolbar.rest.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bks on 4/23/2017.
 */
public class Fragment1 extends Fragment {
    private ArrayList<Result> upComingMovieList = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();
    private MovieListingAdapter mv_adapter;




    public Fragment1()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment , container, false);
        RecyclerView recyclerView=(RecyclerView)rootview.findViewById(R.id.recycle_view);
        recyclerView.hasFixedSize();

        mv_adapter= new MovieListingAdapter(getContext(), upComingMovieList);
        recyclerView.setAdapter(mv_adapter);

        getMovieListing();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);


        recyclerView.setLayoutManager(gridLayoutManager);
        return rootview;

    }

    private void getMovieListing() {
        RetrofitManager.getInstance().getUpcomingMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
            @Override
            public void onResponse(Call<MovieListing> call, Response<MovieListing> response) {

                if (response.code() == 200) {
                    upComingMovieList.addAll(response.body().getResults());
                    //notifydatachanged kasari haalnay thaha chaina..
                    mv_adapter.notifyDataSetChanged();


                    mv_adapter.setClickListener(new MovieListingAdapter.MovieItemClickListener() {
                        @Override
                        public void onClick(Result result) {
                            Intent startDetailActivity = new Intent(getContext(), DetailActivity.class);
                            startDetailActivity.putExtra("Movie_Detail", (Parcelable) result);
                            startActivity(startDetailActivity);
                        }
                    });
                } else {
                    Log.i(TAG, "onResponse: " + response);
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieListing> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

}
