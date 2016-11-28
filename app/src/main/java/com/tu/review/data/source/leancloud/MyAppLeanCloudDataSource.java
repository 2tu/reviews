package com.tu.review.data.source.leancloud;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.tu.review.data.model.Store;
import com.tu.review.data.source.MyAppDataSource;
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

@MyAppScope public class MyAppLeanCloudDataSource implements MyAppDataSource {
  public MyAppLeanCloudDataSource() {

  }

  @Override public Observable<ResponseBody> detail(final String packageName) {
    return Observable.just("").flatMap(new Func1<String, Observable<ResponseBody>>() {
      @Override public Observable<ResponseBody> call(String s) {
        AVQuery<AVObject> query = new AVQuery<>("app_info");
        query.whereEqualTo("packageName", packageName);
        query.whereEqualTo("store", Store.MY_APP.state);
        try {
          List<AVObject> list = query.find();
          if (list != null && !list.isEmpty()) {
            AVObject avObject = list.get(0);
            return Observable.just(ResponseBody.create(MediaType.parse("application/json"),
                avObject.toJSONObject().toString()));
          } else {
            return Observable.just(ResponseBody.create(MediaType.parse("application/json"), ""));
          }
        } catch (AVException e) {
          return Observable.just(ResponseBody.create(MediaType.parse("application/json"), ""));
        }
      }
    }).subscribeOn(Schedulers.io());
  }
}
