package com.tu.review.di.modules;

import com.tu.review.data.source.LeanCloud;
import com.tu.review.data.source.MyAppDataSource;
import com.tu.review.data.source.Store;
import com.tu.review.data.source.leancloud.MyAppLeanCloudDataSource;
import com.tu.review.data.source.store.MyAppStoreDataSource;
import com.tu.review.di.scope.MyAppScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author tu enum@foxmail.com.
 */

@Module public final class MyAppModule {

  @Provides @Store @MyAppScope MyAppDataSource provideMyAppStoreDataSource(Retrofit retrofit) {
    return new MyAppStoreDataSource(retrofit);
  }

  @Provides @LeanCloud @MyAppScope MyAppDataSource provideMyAppLeanCloudDataSource() {
    return new MyAppLeanCloudDataSource();
  }
}
