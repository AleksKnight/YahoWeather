package com.example.user.mymaterialdesign.api;

import com.example.user.mymaterialdesign.weatherdata.GetQuery;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by user on 02.02.2016.
 */
public interface WeatherService {

  @GET("/v1/public/yql")
  Call<GetQuery> getWeather(@Query("q") String post,@Query("format") String format);

}
