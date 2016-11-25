package com.tu.review.di.modules;

import com.tu.review.data.api.datasource.MyAppDataSource;
import com.tu.review.di.scope.MyAppScope;
import com.tu.review.domain.repository.MyAppRepository;
import dagger.Module;
import dagger.Provides;

/**
 * @author tu enum@foxmail.com.
 */

@Module public final class MyAppModule {
  @Provides @MyAppScope MyAppRepository provideMyAppRepository(MyAppDataSource dataSource) {
    return dataSource;
  }
}
