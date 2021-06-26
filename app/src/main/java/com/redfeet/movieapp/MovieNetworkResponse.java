package com.redfeet.movieapp;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MovieNetworkResponse {
    @SerializedName("page") Integer page;
    @SerializedName("total_results") Integer totalResults;
    @SerializedName("total_pages") Integer totalPages;
    @SerializedName("results")
    List<Movie> movies;
}
