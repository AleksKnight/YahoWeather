package com.example.user.mymaterialdesign.api;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by user on 02.02.2016.
 */
public abstract class RetrofitCallback<T> implements Callback<T> {

  @Override
  public void onResponse(Response<T> response, Retrofit retrofit) {

  }

  @Override
  public void onFailure(Throwable t) {

  }
}