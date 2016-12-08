package com.tu.review.di.components;

import android.content.Context;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;
import com.tu.review.ReviewApplication;
import com.tu.review.data.api.service.Mi;
import com.tu.review.data.api.service.MyApp;
import com.tu.review.di.modules.AppModule;
import dagger.Component;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author tu enum@foxmail.com.
 */

@Singleton @Component(modules = { AppModule.class }) public interface AppComponent {
  /**
   * Expose application {@link Context} for others with different scope to use.
   *
   * @return {@link Context}
   */
  Context appContext();

  /**
   * Expose {@link OkHttpClient} for others with different scope to use.
   *
   * @return {@link OkHttpClient}
   */
  OkHttpClient okHttpClient();

  /**
   * Expose {@link Retrofit} for others with different scope to use.
   *
   * @return {@link Retrofit}
   */
  @MyApp  Retrofit retrofit();

  @Mi Retrofit retrofitMi();

  /**
   * Expose {@link Picasso} for others with different scope to use.
   *
   * @return {@link Picasso}
   */
  Picasso picasso();

  Moshi moshi();

  /**
   *
   */
  void inject(ReviewApplication application);
}
