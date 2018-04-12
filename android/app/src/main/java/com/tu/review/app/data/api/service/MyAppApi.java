package com.tu.review.app.data.api.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface MyAppApi {
  @GET("myapp/detail.htm") Observable<ResponseBody> detail(@Query("apkName") String packageName);
}
