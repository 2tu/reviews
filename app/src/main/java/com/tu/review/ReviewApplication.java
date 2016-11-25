package com.tu.review;

import android.app.Application;
import android.support.multidex.MultiDex;
import com.avos.avoscloud.AVOSCloud;
import com.tu.review.di.Injector;
import com.tu.review.di.components.AppComponent;
import com.tu.review.di.components.DaggerAppComponent;
import com.tu.review.di.modules.AppModule;

/**
 * @author tu enum@foxmail.com.
 */

public class ReviewApplication extends Application {
  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();
    MultiDex.install(this);
    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    appComponent.inject(this);
    // 初始化参数依次为 this, AppId, AppKey
    AVOSCloud.initialize(this, "lPDJSjjC6wLoumFuLHtFV3TQ-gzGzoHsz", "A6TbDtb0w6yEyk83YKW6AlJy");
  }

  @Override public Object getSystemService(String name) {
    if (Injector.matchesService(name)) {
      return appComponent;
    }
    return super.getSystemService(name);
  }
}
