package com.tu.review.di.modules;

import com.tu.review.data.api.service.Mi;
import com.tu.review.data.api.service.MyApp;
import com.tu.review.data.source.LeanCloud;
import com.tu.review.data.source.ReviewDataSource;
import com.tu.review.data.source.Store;
import com.tu.review.data.source.leancloud.ReviewLeanCloudDataSource;
import com.tu.review.data.source.store.ReviewStoreDataSource;
import com.tu.review.di.scope.ReviewScope;
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
