package com.tu.review.di.components;

import com.tu.review.MainActivity;
import com.tu.review.di.modules.MyAppModule;
import com.tu.review.di.scope.MyAppScope;
import dagger.Component;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope @Component(dependencies = AppComponent.class, modules = MyAppModule.class)
public interface MyAppComponent {
  void inject(MainActivity activity);
}
