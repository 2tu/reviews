package com.tu.review.data.source;

import com.tu.review.data.model.AppInfo;
import com.tu.review.di.scope.MyAppScope;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public class ReviewRepository implements ReviewDataSource {
  private final ReviewDataSource leanCloudDataSource;
  private final ReviewDataSource storeDataSource;

  @Inject public ReviewRepository(@LeanCloud ReviewDataSource leanCloudDataSource,
      @Store ReviewDataSource storeDataSource) {
    this.leanCloudDataSource = leanCloudDataSource;
    this.storeDataSource = storeDataSource;
  }

  @Override public Observable<AppInfo> getAppInfo(final String packageName,
      final com.tu.review.data.model.Store store) {
    return leanCloudDataSource.getAppInfo(packageName, store)
        .flatMap(new Func1<AppInfo, Observable<AppInfo>>() {
          @Override public Observable<AppInfo> call(AppInfo appInfo) {
            if (appInfo == null) {
              final Observable<AppInfo> observable = storeDataSource.getAppInfo(packageName, store);
              observable.flatMap(new Func1<AppInfo, Observable<?>>() {
                @Override public Observable<?> call(AppInfo appInfo) {
                  appInfo.getAVObject().saveInBackground();
                  return null;
                }
              });
              return observable;
            } else {
              storeDataSource.getAppInfo(packageName, store)
                  .flatMap(new Func1<AppInfo, Observable<?>>() {
                    @Override public Observable<?> call(AppInfo appInfo) {
                      if (null != appInfo) {
                        leanCloudDataSource.save(appInfo).subscribeOn(Schedulers.io()).subscribe();
                      }
                      return null;
                    }
                  })
                  .subscribeOn(Schedulers.io())
                  .subscribe();
              return Observable.just(appInfo);
            }
          }
        });
  }

  @Override public Observable<ResponseBody> save(AppInfo appInfo) {
    return leanCloudDataSource.save(appInfo);
  }

  @Override public Observable<AppInfo> getAppInfo(AppInfo appInfo) {
    return getAppInfo(appInfo.packageName, appInfo.store);
  }
}
