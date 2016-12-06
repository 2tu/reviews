package com.tu.review.data.source.store;

import com.tu.review.data.api.service.MyAppApi;
import com.tu.review.data.model.AppInfo;
import com.tu.review.data.source.ReviewDataSource;
import com.tu.review.di.scope.MyAppScope;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public class ReviewStoreDataSource implements ReviewDataSource {
  private final MyAppApi api;

  public ReviewStoreDataSource(Retrofit retrofit) {
    api = retrofit.create(MyAppApi.class);
  }

  @Override public Observable<AppInfo> getAppInfo(final String packageName,
      final com.tu.review.data.model.Store store) {
    switch (store) {
      case MY_APP:
        return api.detail(packageName).flatMap(new Func1<ResponseBody, Observable<AppInfo>>() {
          @Override public Observable<AppInfo> call(ResponseBody responseBody) {
            return Observable.just(StoreHelper.parse(packageName, store, responseBody));
          }
        }).subscribeOn(Schedulers.io());
    }
    return Observable.empty();
  }

  @Override public Observable<ResponseBody> save(AppInfo appInfo) {
    return Observable.error(new IllegalAccessException("store no save"));
  }

  @Override public Observable<AppInfo> getAppInfo(AppInfo appInfo) {
    return getAppInfo(appInfo.packageName, appInfo.store);
  }
}
