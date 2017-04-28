package com.example.bks.recjsontabtoolbar.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bks.recjsontabtoolbar.BuildConfig;
import com.example.bks.recjsontabtoolbar.R;
import com.example.bks.recjsontabtoolbar.respond.Generics;
import com.example.bks.recjsontabtoolbar.respond.MovieDetail;
import com.example.bks.recjsontabtoolbar.respond.MovieResult;
import com.example.bks.recjsontabtoolbar.respond.MovieVideos;
import com.example.bks.recjsontabtoolbar.respond.Result;
import com.example.bks.recjsontabtoolbar.rest.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private TextView Language, release, rating, like, movie_tagline,overview, moviename, tvTItle, tvname, movie_Generics;
    private ImageView movie_img, toolimg;
    private Context context;
    private String movieId;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*toolbar=(Toolbar)findViewById(R.id.toolbar);

        tvTItle=(TextView)toolbar.findViewById(R.id.tool_txt) ;
        tvname=(TextView)toolbar.findViewById(R.id.tool_txt1) ;
        toolimg=(ImageView)toolbar.findViewById(R.id.tool_img) ;


       setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

        movie_img = (ImageView) findViewById(R.id.movie_pic);
        Language = (TextView) findViewById(R.id.movie_language);
        release = (TextView) findViewById(R.id.movie_release);
        rating = (TextView) findViewById(R.id.movie_rating_detail);
        moviename = (TextView) findViewById(R.id.movie_name);

        like = (TextView) findViewById(R.id.movie_likes);

        overview = (TextView) findViewById(R.id.overview);


        Intent intent = getIntent();
        Result movieDetail = (Result) intent.getExtras().get("Movie_Detail");

        //getSupportActionBar().setTitle(movieDetail.getTitle());




        //Log.i(TAG, "onCreate: " + movieDetail.getOriginalTitle());
        Log.i(TAG, "onCreate: " + movieDetail.getReleaseDate());

        //set values on View
        setValuesOnView(movieDetail);

        movieId=movieDetail.getId().toString();
        getMovieMoreDetails(movieId);

    }

    private void setValuesOnView(Result movieDetail) {
        Language.setText(movieDetail.getOriginalLanguage());
        overview.setText(movieDetail.getOverview());
        release.setText(movieDetail.getReleaseDate());
        moviename.setText(movieDetail.getOriginalTitle());
        like.setText("" + movieDetail.getVoteCount());
        rating.setText("" + movieDetail.getVoteAverage());

        movie_tagline = (TextView) findViewById(R.id.tv_detail_tagline);
        movie_Generics = (TextView) findViewById(R.id.tv_detail_genres);

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w185/" + movieDetail.getPosterPath())
                .into(movie_img);
    }

    private void getMovieMoreDetails(String movieId) {
        RetrofitManager.getInstance().getMovieMoreDetail( movieId, BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieDetail>(){

            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.code() == 200){
                    Log.i(TAG, "onResponse: Detsil chk"+ response.body());
                    updateResponseMovieDetails(response);

                } else{
                    Log.i(TAG, "onResponse: " + response.message());
                    Toast.makeText(DetailActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            private void updateResponseMovieDetails(Response<MovieDetail> response) {
                ArrayList<Generics> genreList;
                ArrayList<MovieResult> movieLinkList;

                //Get Movie TagLine value and set it to view
                String movieTagLine = response.body().getTagline();
                if( !TextUtils.isEmpty(movieTagLine)){
                    movie_tagline.setVisibility(View.VISIBLE);
                    movie_tagline.setText(movieTagLine);
                }

                //Get Movie Genres array values and set it to view

                genreList = response.body().getGenerics();
                List<String> genreNames = new ArrayList<>();
                for(int i = 0; i < genreList.size(); i++){
                    genreNames.add(genreList.get(i).getGenericsname());
                }

                String movieGenerics =TextUtils.join(",", genreNames);
                if(!TextUtils.isEmpty(movieGenerics)){
                    movie_Generics.setVisibility(View.VISIBLE);
                    movie_Generics.setText(movieGenerics);
                }

                //Get Movie Video LInks and set value to views
                MovieVideos movieVideos = response.body().getMovieVideos();
                movieLinkList = movieVideos.getMovieResults();
                List<String> videoLinks = new ArrayList<>();
                for(int i = 0; i < movieLinkList.size(); i++){
                    videoLinks.add(movieLinkList.get(i).getKey());
                    Log.i(TAG, "updateResponseMovieDetails: "+ videoLinks);

                    //Dynamically creating button to view videos
                    createVideoBtnLayoutDynamically(videoLinks);
                }

            }

            private void createVideoBtnLayoutDynamically (final List<String> videoLinksArray) {

                for (int i = 0; i < videoLinksArray.size(); i++) {
                    Button vButton = new Button(getApplicationContext());
                    vButton.setText("Video" + i);
                    vButton.setId(i);
                    final int id_ = vButton.getId();

                    LinearLayout layout = (LinearLayout) findViewById(R.id.movie_video_btns);
                    layout.addView(vButton);

                    vButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+ videoLinksArray.get(id_) )));
                            //Toast.makeText(DetailActivity.this, "Button clicked index = " + videoLinksArray.get(id_), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.i(TAG, "onFailure: "+ t.getLocalizedMessage());
            }
        });
    }





    public static Intent getLaunchIntent(Context context, Result result) {
        Intent movieDetailActivityIntent = new Intent(context, DetailActivity.class);
        movieDetailActivityIntent.putExtra("Movie_Detail", result);
        return movieDetailActivityIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return true;
    }

}

/*import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bks.jsonparsing.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}*/

