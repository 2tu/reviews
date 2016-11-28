package com.tu.review.data.source;

import com.avos.avoscloud.AVObject;
import com.tu.review.data.model.AppInfo;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public class LeanCloudRepository implements LeanCloudDataSource {
  private final LeanCloudDataSource dataSource;

  @Inject public LeanCloudRepository(LeanCloudDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override public Observable<ResponseBody> save(AppInfo appInfo) {
    return dataSource.save(appInfo);
  }

  @Override public Observable<AVObject> query(AppInfo appInfo) {
    return dataSource.query(appInfo);
  }
}
