package com.codepath.daggerexample.network.interfaces;

import com.codepath.daggerexample.models.MovieRespone;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GitHubApiInterface {
    @GET("movie/top_rated")
    Call<MovieRespone> getTopRatedMovies(@Query("api_key") String apiKey);

}