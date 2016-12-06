package com.tu.review.presentation.myapp;

import com.tu.core.mvp.RxPresenter;
import com.tu.core.rx.EndSubscriber;
import com.tu.review.data.model.AppInfo;
import com.tu.review.data.model.Store;
import com.tu.review.data.source.ReviewRepository;
import javax.inject.Inject;

/**
 * @author tu enum@foxmail.com.
 */

public final class MyAppPresenter extends RxPresenter<MyAppView> {
  private final ReviewRepository repository;

  @Inject public MyAppPresenter(ReviewRepository repository) {
    this.repository = repository;
  }

  /**
   * 先从leancloud拿，再去Store拿，再更新到eancloud
   */
  public void load(final String packageName) {
    baseView.showLoading();
    subscriptions.add(observeOnView(repository.getAppInfo(packageName, Store.MY_APP)).subscribe(
        new EndSubscriber<AppInfo>() {
          @Override public void onEnd() {
            baseView.hideLoading();
          }

          @Override public void onNext(AppInfo appInfo) {
            if (null != appInfo) {
              baseView.showMyAppInfo(appInfo);
            } else {
              baseView.showEmptyView();
            }
          }
        }));
  }
}
