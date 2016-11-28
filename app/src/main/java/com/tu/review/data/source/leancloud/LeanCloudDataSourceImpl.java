package com.tu.review.data.source.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.tu.review.data.model.AppInfo;
import com.tu.review.data.source.LeanCloudDataSource;
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

@MyAppScope public class LeanCloudDataSourceImpl implements LeanCloudDataSource {
  public LeanCloudDataSourceImpl() {

  }

  @Override public Observable<ResponseBody> save(final AppInfo appInfo) {
    return query(appInfo).flatMap(new Func1<AVObject, Observable<ResponseBody>>() {
      @Override public Observable<ResponseBody> call(AVObject avObject) {
        AVObject saveAVObject = appInfo.getAVObject();
        if (null != avObject) {
          saveAVObject.setObjectId(avObject.getObjectId());
        }
        saveAVObject.saveInBackground();
        return Observable.just(ResponseBody.create(MediaType.parse("application/json"), "save"));
      }
    }).subscribeOn(Schedulers.io());
  }

  @Override public Observable<AVObject> query(final AppInfo appInfo) {
    return Observable.just("").flatMap(new Func1<String, Observable<AVObject>>() {
      @Override public Observable<AVObject> call(String s) {
        AVQuery<AVObject> query = new AVQuery<>("app_info");
        query.whereEqualTo("packageName", appInfo.packageName);
        query.whereEqualTo("store", appInfo.store.state);
        try {
          List<AVObject> list = query.find();
          if (list != null && !list.isEmpty()) {
            AVObject avObject = list.get(0);
            return Observable.just(avObject);
          } else {
            return Observable.empty();
          }
        } catch (AVException e) {
          return Observable.empty();
        }
      }
    }).subscribeOn(Schedulers.io());
  }
}
