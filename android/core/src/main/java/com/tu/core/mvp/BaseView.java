package com.tu.core.mvp;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 *
 * @author tu enum@foxmail.com.
 */

public interface BaseView {
  /**
   * Show loading when retrieving data.
   */
  void showLoading();

  /**
   * Hide loading dialog after retrieving data.
   */
  void hideLoading();
}
