package com.tu.review;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tu.core.mvp.BaseView;
import com.tu.core.mvp.Presenter;
import com.tu.core.mvp.RxPresenter;
import com.tu.core.mvp.RxPresenterDelegate;

/**
 * @author tu enum@foxmail.com.
 */

public class BaseActivity extends AppCompatActivity {
  public static final RxPresenter RX_PRESENTER = new RxPresenter() {
  };
  @SuppressWarnings("unchecked") private final RxPresenterDelegate rxPresenterDelegate =
      new RxPresenterDelegate(RX_PRESENTER);
  private Unbinder unbinder;

  @Override public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    unbinder = ButterKnife.bind(this);
  }

  @Override public void setContentView(View view) {
    super.setContentView(view);
    unbinder = ButterKnife.bind(this);
  }

  @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
    super.setContentView(view, params);
    unbinder = ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    rxPresenterDelegate.detach();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }
  @SuppressWarnings("unchecked")
  protected void delegatePresenter(Presenter presenter, BaseView view) {
    rxPresenterDelegate.delegate(presenter);
    rxPresenterDelegate.attach(view);
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    rxPresenterDelegate.saveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    rxPresenterDelegate.restoreInstanceState(savedInstanceState);
    super.onRestoreInstanceState(savedInstanceState);
  }
}
