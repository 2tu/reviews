package com.tu.review.presentation;

import com.tu.core.mvp.BaseView;
import com.tu.review.data.model.AppInfo;

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