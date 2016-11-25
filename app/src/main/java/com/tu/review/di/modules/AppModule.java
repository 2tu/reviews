package com.tu.review.di.modules;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import timber.log.Timber;

/**
 * @author tu enum@foxmail.com.
 */

@Module(includes = {
    NetworkModule.class
}) public final class AppModule {
  private Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Context provideAppContext() {
    return application.getApplicationContext();
  }

  @Provides @Singleton Moshi provideMoshi() {
    return new Moshi.Builder().build();
  }

  @Provides @Singleton Picasso providePicasso(Context appContext, OkHttp3Downloader downloader) {
    return new Picasso.Builder(appContext).downloader(downloader).listener(new Picasso.Listener() {
      @Override public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
        Timber.e(e, "Failed to load image: %s", uri);
      }
    }).build();
  }
}
