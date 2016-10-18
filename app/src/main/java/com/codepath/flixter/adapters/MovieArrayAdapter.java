package com.codepath.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixter.R;
import com.codepath.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by arunesh on 10/17/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data for the given position.
        Movie movie = getItem(position);

        // check if existing view can be re-used or create a new one.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,
                    parent, false);
        }

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        // clear out image from last time incase it is being re-used.
        ivImage.setImageResource(0);
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        boolean isLandscape = getContext().getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_LANDSCAPE;

        Picasso.with(getContext()).load(
                isLandscape ? movie.getBackdropPath() : movie.getPosterPath()).into(ivImage);

        // return the view
        return convertView;
    }

    private void populateViewForPortrait(View view, Movie movie, ViewGroup parent) {

        // check if existing view can be re-used or create a new one.
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,
                    parent, false);
        }

        ImageView ivImage = (ImageView) view.findViewById(R.id.ivMovieImage);

        // clear out image from last time incase it is being re-used.
        ivImage.setImageResource(0);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)view.findViewById(R.id.tvOverview);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
    }


    private void populateViewForLandscape(View view, Movie movie, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,
                    parent, false);
        }
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivMovieImage);

        // clear out image from last time incase it is being re-used.
        ivImage.setImageResource(0);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)view.findViewById(R.id.tvOverview);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
    }
}
