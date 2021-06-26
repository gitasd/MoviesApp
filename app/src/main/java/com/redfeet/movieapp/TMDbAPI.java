package com.redfeet.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface TMDbAPI {
    @GET("movie/popular")
    Call<MovieNetworkResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieNetworkResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
