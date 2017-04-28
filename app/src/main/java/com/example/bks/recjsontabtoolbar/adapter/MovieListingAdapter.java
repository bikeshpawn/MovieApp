package com.example.bks.recjsontabtoolbar.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bks.recjsontabtoolbar.R;
import com.example.bks.recjsontabtoolbar.activity.MainActivity;
import com.example.bks.recjsontabtoolbar.fragments.Fragment1;
import com.example.bks.recjsontabtoolbar.respond.Result;

import java.util.ArrayList;





public class MovieListingAdapter extends RecyclerView.Adapter<MovieListingAdapter.MovieViewHolder> {

    private ArrayList<Result> movieListingDetailsArrayList;
    private Context context;
    private MovieItemClickListener movieItemClickListener;

    public void setClickListener (MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
    }

    public MovieListingAdapter(Context context, ArrayList<Result> movieListingDetailsArrayList) {
        this.movieListingDetailsArrayList = movieListingDetailsArrayList;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()). inflate(R.layout.movie_listing, parent, false);
        MovieViewHolder rvh = new MovieViewHolder(layoutView);
        return rvh;

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.tvMovieTitle.setText(movieListingDetailsArrayList.get(position).getOriginalTitle());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185/"+movieListingDetailsArrayList.get(position).getPosterPath())
                .into(holder.ivMovieImage);
        holder.tvmovieReleaseDate.setText(movieListingDetailsArrayList.get(position).getReleaseDate());
        holder.tvMovieRatings.setText("" + movieListingDetailsArrayList.get(position).getVoteAverage());

        holder.rlMovieContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movieItemClickListener != null) {
                    movieItemClickListener.onClick(movieListingDetailsArrayList.get(position));



                }


            }
        });

    }


    @Override
    public int getItemCount() {
        return movieListingDetailsArrayList.size();
    }



    public interface MovieItemClickListener{
        void onClick(Result result);


    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMovieTitle, tvMovieRatings, tvmovieReleaseDate;
        private ImageView ivMovieImage;
        private CardView rlMovieContainer;

        public MovieViewHolder(View itemView) {
            super(itemView);

            tvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            tvMovieRatings = (TextView) itemView.findViewById(R.id.tv_movie_ratings);
            tvmovieReleaseDate = (TextView) itemView.findViewById(R.id.tv_movie_release_date);
            ivMovieImage = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            rlMovieContainer = (CardView)itemView.findViewById(R.id.rl_movie_container);

        }


    }
}

