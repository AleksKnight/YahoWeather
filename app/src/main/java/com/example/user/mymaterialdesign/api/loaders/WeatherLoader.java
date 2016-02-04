package com.example.user.mymaterialdesign.api.loaders;

import android.content.Context;

import com.example.user.mymaterialdesign.api.ApiFactory;
import com.example.user.mymaterialdesign.api.RetrofitCallback;
import com.example.user.mymaterialdesign.api.WeatherService;
import com.example.user.mymaterialdesign.weatherdata.GetQuery;

import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by user on 02.02.2016.
 */
public class WeatherLoader extends MyBaseLoader {
  private String town;
  private WeatherService mWeatherService;
  private GetQuery mGetQuery;

  public WeatherLoader(Context context, String _town) {
    super(context);
    this.town = _town;
    this.mWeatherService = ApiFactory.getWeatherService();
  }

  @Override
  protected void onForceLoad() {
    String yql = "select * from weather.forecast where woeid in (select woeid from geo.places(1) " +
        "where text=\"" + town + ", ru\")";
    //yql=URLEncoder.encode(yql);
    //System.out.println("++ "+yql);
    Call<GetQuery> call = mWeatherService.getWeather(yql,"json");
    call.enqueue(new RetrofitCallback<GetQuery>() {
      @Override
      public void onResponse(Response<GetQuery> response, Retrofit retrofit) {
        if (response.isSuccess()) {
          mGetQuery = response.body();
          deliverResult(mGetQuery);
          System.out.println("1");
        } else {
          deliverResult(null);
          System.out.println("2");
        }

      }

      @Override
      public void onFailure(Throwable t) {
        System.out.println("3" +t);
      }

    });

  }

}
