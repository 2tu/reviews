package com.tu.review.app.data.source.store;

import com.tu.review.app.data.api.service.MiApi;
import com.tu.review.app.data.api.service.MyAppApi;
import com.tu.review.app.data.model.AppInfo;
import com.tu.review.app.data.model.Store;
import com.tu.review.app.data.source.ReviewDataSource;
import com.tu.review.app.di.scope.ReviewScope;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author tu enum@foxmail.com.
 */

@ReviewScope public class ReviewStoreDataSource implements ReviewDataSource {
  private final MyAppApi myAppApi;
  private final MiApi miApi;

  public ReviewStoreDataSource(Retrofit myAppRetrofit, Retrofit miRetrofit) {
    myAppApi = myAppRetrofit.create(MyAppApi.class);
    miApi = miRetrofit.create(MiApi.class);
  }

  @Override public Observable<AppInfo> getAppInfo(final String packageName,
      final Store store) {
    switch (store) {
      case MY_APP:
        return myAppApi.detail(packageName).flatMap(new Func1<ResponseBody, Observable<AppInfo>>() {
          @Override public Observable<AppInfo> call(ResponseBody responseBody) {
            return Observable.just(StoreHelper.parse(packageName, store, responseBody));
          }
        }).subscribeOn(Schedulers.io());
      case MI:
        return miApi.detail(packageName).flatMap(new Func1<ResponseBody, Observable<AppInfo>>() {
          @Override public Observable<AppInfo> call(ResponseBody responseBody) {
            return Observable.just(StoreHelper.parse(packageName, store, responseBody));
          }
        }).subscribeOn(Schedulers.io());
    }
    return Observable.just(null);
  }

  @Override public Observable<ResponseBody> save(AppInfo appInfo) {
    return Observable.error(new IllegalAccessException("store no save"));
  }

  @Override public Observable<AppInfo> getAppInfo(AppInfo appInfo) {
    return getAppInfo(appInfo.packageName, appInfo.store);
  }
}
