package com.tu.review.data.source.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.tu.review.data.model.AppInfo;
import com.tu.review.data.model.Store;
import com.tu.review.data.source.ReviewDataSource;
import com.tu.review.di.scope.MyAppScope;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public class ReviewLeanCloudDataSource implements ReviewDataSource {
  public ReviewLeanCloudDataSource() {

  }

  @Override public Observable<AppInfo> getAppInfo(final String packageName,
      final com.tu.review.data.model.Store store) {
    return Observable.just("").flatMap(new Func1<String, Observable<AppInfo>>() {
      @Override public Observable<AppInfo> call(String s) {
        AVQuery<AVObject> query = new AVQuery<>("app_info");
        query.whereEqualTo("packageName", packageName);
        query.whereEqualTo("store", store.state);
        try {
          List<AVObject> list = query.find();
          if (list != null && !list.isEmpty()) {
            AVObject avObject = list.get(0);
            return Observable.just(AppInfo.newBuilder()
                .store(Store.from(avObject.getString("store")))
                .packageName(avObject.getString("packageName"))
                .category(avObject.getString("category"))
                .appSize(avObject.getString("appSize"))
                .appName(avObject.getString("appName"))
                .downCount(avObject.getLong("downCount"))
                .icon(avObject.getString("icon"))
                .reviewCount(avObject.getInt("reviewCount"))
                .score(avObject.getNumber("score").floatValue())
                .objectId(avObject.getObjectId())
                .build());
          } else {
            return Observable.empty();
          }
        } catch (AVException e) {
          return Observable.empty();
        }
      }
    }).subscribeOn(Schedulers.io());
  }

  @Override public Observable<ResponseBody> save(final AppInfo appInfo) {
    return getAppInfo(appInfo).flatMap(new Func1<AppInfo, Observable<ResponseBody>>() {
      @Override public Observable<ResponseBody> call(AppInfo appInfoResult) {
        if (null != appInfoResult) {
          appInfo.objectId = appInfoResult.objectId;
        }
        appInfo.getAVObject().saveInBackground();
        return Observable.just(ResponseBody.create(MediaType.parse("application/json"), "save"));
      }
    }).subscribeOn(Schedulers.io());
  }

  @Override public Observable<AppInfo> getAppInfo(final AppInfo appInfo) {
    return getAppInfo(appInfo.packageName, appInfo.store);
  }
}
