package com.redfeet.movieapp;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie{
    private static final String BASE_IMAGE_URL = "https://image.tmdb/t/p";
    public static final String BASE_POSTER_LARGE_URL = BASE_IMAGE_URL + "/w342";

    @SerializedName("title") String title;
    @SerializedName("vote_average") float votes;
    @SerializedName("poster_path") String posterpath;

    public String getLargePosterUrl(){ return BASE_POSTER_LARGE_URL + posterpath;}

}