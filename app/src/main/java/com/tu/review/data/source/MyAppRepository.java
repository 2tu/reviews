package com.tu.review.data.source;

import com.tu.review.di.scope.MyAppScope;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public class MyAppRepository implements MyAppDataSource {
  private final MyAppDataSource leanCloudDataSource;
  private final MyAppDataSource storeDataSource;

  @Inject public MyAppRepository(@LeanCloud MyAppDataSource leanCloudDataSource,
      @Store MyAppDataSource storeDataSource) {
    this.leanCloudDataSource = leanCloudDataSource;
    this.storeDataSource = storeDataSource;
  }

  @Override public Observable<ResponseBody> detail(final String packageName) {
    return leanCloudDataSource.detail(packageName)
        .flatMap(new Func1<ResponseBody, Observable<ResponseBody>>() {
          @Override public Observable<ResponseBody> call(ResponseBody responseBody) {
            if (responseBody.contentLength() == 0) {
              return storeDataSource.detail(packageName);
            }
            return Observable.just(responseBody);
          }
        });
  }

  public Observable<ResponseBody> detailByMyApp(final String packageName) {
    return storeDataSource.detail(packageName);
  }

  public Observable<ResponseBody> detailByLeanCloud(final String packageName) {
    return leanCloudDataSource.detail(packageName);
  }
}
