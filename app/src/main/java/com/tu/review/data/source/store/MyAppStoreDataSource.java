package com.tu.review.data.source.store;

import com.tu.review.data.api.service.MyAppApi;
import com.tu.review.data.source.MyAppDataSource;
import com.tu.review.di.scope.MyAppScope;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public class MyAppStoreDataSource implements MyAppDataSource {
  private final MyAppApi api;

  public MyAppStoreDataSource(Retrofit retrofit) {
    api = retrofit.create(MyAppApi.class);
  }

  @Override public Observable<ResponseBody> detail(String packageName) {
    return api.detail(packageName);
  }
}
