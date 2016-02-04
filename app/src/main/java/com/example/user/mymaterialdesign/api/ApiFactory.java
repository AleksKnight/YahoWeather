package com.example.user.mymaterialdesign.api;

import android.support.annotation.NonNull;

import com.example.user.mymaterialdesign.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by user on 02.02.2016.
 */
public class ApiFactory {
  private static final int CONNECT_TIMEOUT = 15;
  private static final int WRITE_TIMEOUT = 60;
  private static final int TIMEOUT = 60;
  private static final OkHttpClient CLIENT = new OkHttpClient();

  static {
    CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
    CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
    CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
    CLIENT.interceptors().add(new Interceptor() {
      @Override
      public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
        System.out.println("Fovno " + chain.request());
        com.squareup.okhttp.Response response = chain.proceed(chain.request());
        final String content = response.body().string();
        System.out.println("Fovno " + content);
        return response.newBuilder()
            .body(ResponseBody.create(response.body().contentType(), content))
            .build();
      }
    });
  }

  private static final Gson GSON = new GsonBuilder()
      .create();

  @NonNull
  public static WeatherService getWeatherService() {
    return getRetrofit().create(WeatherService.class);
  }

  @NonNull
  private static Retrofit getRetrofit() {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(GSON))
        .client(CLIENT)
        .build();
  }
}
