package com.tu.review.app.di.components;

import com.tu.review.MainActivity;
import com.tu.review.app.di.modules.ReviewModule;
import com.tu.review.app.di.scope.ReviewScope;
import com.tu.review.di.components.AppComponent;
import dagger.Component;

/**
 * @author tu enum@foxmail.com.
 */

@ReviewScope @Component(dependencies = AppComponent.class, modules = ReviewModule.class)
public interface ReviewComponent {
  void inject(MainActivity activity);
}
