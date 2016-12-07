package com.tu.review.data.model;

/**
 * @author tu enum@foxmail.com.
 */

public enum Store {
  MY_APP("MY_APP", "应用宝"),
  QIHU_360("360", "360"),
  WANDOUJIA("WANDOUJIA", "豌豆荚"),
  MI("Mi", "小米");

  public final String state;
  public final String description;

  Store(String state, String description) {
    this.state = state;
    this.description = description;
  }

  public static Store from(String state) {
    for (Store repaymentState : Store.values()) {
      if (repaymentState.state.equals(state)) {
        return repaymentState;
      }
    }

    return null;
  }
}
