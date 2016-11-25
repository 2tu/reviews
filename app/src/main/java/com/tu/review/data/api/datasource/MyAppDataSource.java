package com.tu.review.data.api.datasource;

import com.tu.review.data.api.service.MyAppApi;
import com.tu.review.domain.repository.MyAppRepository;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author tu enum@foxmail.com.
 */

public class MyAppDataSource implements MyAppRepository {
  private final MyAppApi api;

  @Inject public MyAppDataSource(Retrofit retrofit) {
    api = retrofit.create(MyAppApi.class);
  }

  @Override public Observable<ResponseBody> detail(String packageName) {
    return api.detail(packageName);
  }
}
