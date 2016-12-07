package com.tu.review.di.components;

import com.tu.review.MainActivity;
import com.tu.review.di.modules.ReviewModule;
import com.tu.review.di.scope.ReviewScope;
import dagger.Component;

/**
 * @author tu enum@foxmail.com.
 */

@ReviewScope @Component(dependencies = AppComponent.class, modules = ReviewModule.class)
public interface ReviewComponent {
  void inject(MainActivity activity);
}
