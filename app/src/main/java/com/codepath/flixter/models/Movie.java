package com.codepath.flixter.models;

import android.provider.Telephony;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arunesh on 10/17/16.
 */

public class Movie {
    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w780/" + backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Movie(JSONObject jsonObject) throws JSONException{
        posterPath = jsonObject.getString("poster_path");
        originalTitle = jsonObject.getString("original_title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array) throws JSONException {
        ArrayList<Movie> arrayList = new ArrayList<Movie>(array.length());
        for (int i = 0; i < array.length(); i++) {
            arrayList.add(new Movie(array.getJSONObject(i)));
        }
        return arrayList;
    }
}
