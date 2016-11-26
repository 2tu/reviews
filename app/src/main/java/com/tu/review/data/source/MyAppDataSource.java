package com.tu.review.data.source;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface MyAppDataSource {
  Observable<ResponseBody> detail(String packageName);
}
