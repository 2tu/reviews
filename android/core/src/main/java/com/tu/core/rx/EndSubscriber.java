package com.tu.core.rx;

import android.support.annotation.CallSuper;
import rx.Subscriber;

/**
 * @author tu enum@foxmail.com
 */
public abstract class EndSubscriber<T> extends Subscriber<T> {

  @CallSuper @Override public void onCompleted() {
    onEnd();
  }

  @CallSuper @Override public void onError(Throwable e) {
    onEnd();
  }

  public abstract void onEnd();
}
