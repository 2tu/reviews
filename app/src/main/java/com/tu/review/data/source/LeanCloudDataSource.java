package com.tu.review.data.source;

import com.avos.avoscloud.AVObject;
import com.tu.review.data.model.AppInfo;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface LeanCloudDataSource {
  Observable<ResponseBody> save(AppInfo appInfo);

  Observable<AVObject> query(AppInfo appInfo);
}
