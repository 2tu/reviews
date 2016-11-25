package com.tu.core.mvp;

import android.os.Bundle;

import static com.tu.core.common.Preconditions.checkNotNull;

/**
 * @author tu enum@foxmail.com.
 */

public final class RxPresenterDelegate<T extends BaseView> implements Presenter<T> {
  private Presenter<T> presenter;

  public RxPresenterDelegate(Presenter<T> presenter) {
    this.presenter = presenter;
  }

  public void delegate(Presenter<T> presenter) {
    this.presenter = checkNotNull(presenter);
  }

  @Override public void attach(T view) {
    presenter.attach(view);
  }

  @Override public void detach() {
    presenter.detach();
  }

  @Override public void restoreInstanceState(Bundle bundle) {
    presenter.restoreInstanceState(bundle);
  }

  @Override public void saveInstanceState(Bundle bundle) {
    presenter.saveInstanceState(bundle);
  }
}
