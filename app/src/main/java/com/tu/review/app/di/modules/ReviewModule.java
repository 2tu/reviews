package com.tu.review.app.di.modules;

import com.tu.review.app.data.api.service.Mi;
import com.tu.review.app.data.api.service.MyApp;
import com.tu.review.app.data.source.LeanCloud;
import com.tu.review.app.data.source.ReviewDataSource;
import com.tu.review.app.data.source.Store;
import com.tu.review.app.data.source.leancloud.ReviewLeanCloudDataSource;
import com.tu.review.app.data.source.store.ReviewStoreDataSource;
import com.tu.review.app.di.scope.ReviewScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author tu enum@foxmail.com.
 */

@Module public final class ReviewModule {

  @Provides @Store @ReviewScope ReviewDataSource provideStoreDataSource(
      @MyApp Retrofit myAppRetrofit, @Mi Retrofit miRetrofit) {
    return new ReviewStoreDataSource(myAppRetrofit, miRetrofit);
  }

  @Provides @LeanCloud @ReviewScope ReviewDataSource provideMyAppLeanCloudDataSource() {
    return new ReviewLeanCloudDataSource();
  }
}
