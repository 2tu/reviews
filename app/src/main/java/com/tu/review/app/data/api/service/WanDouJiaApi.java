package com.tu.review.app.data.api.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Tu enum@foxmail.com.
 */

public interface WanDouJiaApi {
  @GET("apps/{packageName}") Observable<ResponseBody> detail(
      @Path("packageName") String packageName);
}
