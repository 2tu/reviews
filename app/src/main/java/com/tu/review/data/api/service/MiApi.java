package com.tu.review.data.api.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface MiApi {
  @GET("details") Observable<ResponseBody> detail(@Query("id") String packageName);
}
