package com.codepath.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.Log;
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

import static com.codepath.flixter.R.id.tvOverview;
import static com.codepath.flixter.R.id.tvTitle;

/**
 * Created by arunesh on 10/17/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class MovieViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView overviewTextView;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        long ts1 = 0, ts2 = 0;
        long ts0 = System.nanoTime();
        // get the data for the given position.
        Movie movie = getItem(position);
        MovieViewHolder viewHolder;

        // check if existing view can be re-used or create a new one.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,
                    parent, false);

            viewHolder = new MovieViewHolder();
            ts1 = System.nanoTime();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.titleTextView =  (TextView)convertView.findViewById(tvTitle);
            viewHolder.overviewTextView = (TextView)convertView.findViewById(tvOverview);
            ts2 = System.nanoTime();

            // Store a reference to the MovieViewHolder object so that we can use it later.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MovieViewHolder) convertView.getTag();
        }

        long ts3 = System.nanoTime();
        // clear out image from last time incase it is being re-used.
        viewHolder.imageView.setImageResource(0);
        viewHolder.titleTextView.setText(movie.getOriginalTitle());
        viewHolder.overviewTextView.setText(movie.getOverview());
        boolean isLandscape = getContext().getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_LANDSCAPE;

        Picasso.with(getContext()).load(
                isLandscape ? movie.getBackdropPath() : movie.getPosterPath())
                .into(viewHolder.imageView);

        long ts4 = System.nanoTime();
        Log.d("ViewHolder", "Deltas Overall: (ts4 - ts0) = "
                + (ts4-ts0)
        + " ViewHolder (ts2 - ts1 )" + (ts2-ts1)
                + " Rest of the rendering (ts4-ts3) " + (ts4-ts3));
        // return the view
        return convertView;
    }

}
