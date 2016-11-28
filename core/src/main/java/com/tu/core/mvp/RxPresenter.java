package com.tu.core.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Abstract class representing a Presenter with rxJava in a model view presenter (MVP) pattern.
 *
 * @author tu enum@foxmail.com
 */
public abstract class RxPresenter<V extends BaseView> implements Presenter<V> {
  protected final CompositeSubscription subscriptions = new CompositeSubscription();
  protected V baseView;
  protected Bundle bundle;

  @CallSuper @Override public void attach(V view, Bundle bundle) {
    this.baseView = view;
    this.bundle = bundle;
  }

  @CallSuper @Override public void attach(V view) {
    this.baseView = view;
  }

  @Override public void restoreInstanceState(Bundle savedInstanceState) {
  }

  @Override public void saveInstanceState(Bundle outState) {
  }

  @CallSuper @Override public void detach() {
    subscriptions.clear();
    baseView = null;
    bundle = null;
  }

  protected <T> Observable<T> observeOnView(Observable<T> observable) {
    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}
