package com.example.bks.recjsontabtoolbar.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.bks.recjsontabtoolbar.BuildConfig;
import com.example.bks.recjsontabtoolbar.R;
import com.example.bks.recjsontabtoolbar.adapter.MovieListingAdapter;
import com.example.bks.recjsontabtoolbar.adapter.ViewPagerAdapter;
import com.example.bks.recjsontabtoolbar.fragments.Fragment1;
import com.example.bks.recjsontabtoolbar.fragments.Fragment2;
import com.example.bks.recjsontabtoolbar.fragments.Fragment3;
import com.example.bks.recjsontabtoolbar.respond.MovieListing;
import com.example.bks.recjsontabtoolbar.respond.Result;
import com.example.bks.recjsontabtoolbar.rest.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //private static final String TAG = MainActivity.class.getSimpleName();
    //private ArrayList<Result> upComingMovieList = new ArrayList<>();
    //private RecyclerView recyclerView;
    //private MovieListingAdapter adapter;
    private Toolbar toolbar;
    private TabLayout tabLayout ;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tablayout
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager=(ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    /*    recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(linearLayoutManager);

        getMovieListing();
        adapter = new MovieListingAdapter(MainActivity.this, upComingMovieList);
        recyclerView.setAdapter(adapter);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:
                break;

            case R.id.option2:
                Toast.makeText(this, "Option2 Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.option3:
                Toast.makeText(this, "Option3 Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.option4:
                Toast.makeText(this, "Option4 Selected", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    //prepare for pre exist value.. not now needed
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment1(), "UPCOMING");
        adapter.addFragment(new Fragment2(), "POPULAR");
        adapter.addFragment(new Fragment3(), "NOW SHOWING");
        viewPager.setAdapter(adapter);
    }

    /*private void getMovieListing() {
        RetrofitManager.getInstance().getUpcomingMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
            @Override
            public void onResponse(Call<MovieListing> call, Response<MovieListing> response) {

                if (response.code() == 200) {
                    upComingMovieList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();

                    adapter.setClickListener(new MovieListingAdapter.MovieItemClickListener() {
                        @Override
                        public void onClick(Result result) {
                            Intent startDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
                            startDetailActivity.putExtra("Movie_Detail", result);
                            startActivity(startDetailActivity);
                        }
                    });
                } else {
                    Log.i(TAG, "onResponse: " + response);
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieListing> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }*/
}
