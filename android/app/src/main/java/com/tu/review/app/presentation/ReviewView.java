package com.tu.review.app.presentation;

import com.tu.core.mvp.BaseView;
import com.tu.review.app.data.model.AppInfo;

/**
 * @author tu enum@foxmail.com.
 */

public interface ReviewView extends BaseView {
  /**
   * Show empty view when no data to show.
   */
  void showEmptyView();

  /**
   * Show retry view.
   */
  void showRetryView();

  void showMyAppInfo(AppInfo appInfo);
}
