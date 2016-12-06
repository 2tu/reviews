package com.tu.review.di.modules;

import com.tu.review.data.source.LeanCloud;
import com.tu.review.data.source.ReviewDataSource;
import com.tu.review.data.source.Store;
import com.tu.review.data.source.leancloud.ReviewLeanCloudDataSource;
import com.tu.review.data.source.store.ReviewStoreDataSource;
import com.tu.review.di.scope.MyAppScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author tu enum@foxmail.com.
 */

@Module public final class MyAppModule {

  @Provides @Store @MyAppScope ReviewDataSource provideMyAppStoreDataSource(Retrofit retrofit) {
    return new ReviewStoreDataSource(retrofit);
  }

  @Provides @LeanCloud @MyAppScope ReviewDataSource provideMyAppLeanCloudDataSource() {
    return new ReviewLeanCloudDataSource();
  }
}
