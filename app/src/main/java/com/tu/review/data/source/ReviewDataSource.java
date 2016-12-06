package com.tu.review.data.source;

import com.tu.review.data.model.AppInfo;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface ReviewDataSource {
  Observable<AppInfo> getAppInfo(String packageName, com.tu.review.data.model.Store store);

  Observable<ResponseBody> save(AppInfo appInfo);

  Observable<AppInfo> getAppInfo(AppInfo appInfo);
}
