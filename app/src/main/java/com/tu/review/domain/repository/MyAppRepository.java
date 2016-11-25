package com.tu.review.domain.repository;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public interface MyAppRepository {
  Observable<ResponseBody> detail(String packageName);
}
