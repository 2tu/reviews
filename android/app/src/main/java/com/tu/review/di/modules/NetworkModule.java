package com.tu.review.di.modules;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.moshi.Moshi;
import com.tu.review.BuildConfig;
import com.tu.review.app.data.api.service.Mi;
import com.tu.review.app.data.api.service.MyApp;
import com.tu.review.common.UrlConstants;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * @author tu enum@foxmail.com.
 */

@Module public final class NetworkModule {
  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS);

    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor loggingInterceptor =
          new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
              Timber.i(message);
            }
          });
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(loggingInterceptor);
    }

    return builder.build();
  }

  @Provides @Singleton @MyApp Retrofit provideMyAppRetrofit(OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder().baseUrl(UrlConstants.DOMAIN_MY_APP)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
  }

  @Provides @Singleton @Mi Retrofit provideMiRetrofit(OkHttpClient client, Moshi moshi) {
    return new Retrofit.Builder().baseUrl(UrlConstants.DOMAIN_MI)
        .client(client)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
  }

  @Provides @Singleton OkHttp3Downloader providePicassoDownloader(OkHttpClient client) {
    return new OkHttp3Downloader(client);
  }
}
