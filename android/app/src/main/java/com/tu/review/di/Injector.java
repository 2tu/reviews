package com.tu.review.di;

import android.content.Context;
import com.tu.review.di.components.AppComponent;

/**
 * @author tu enum@foxmail.com.
 */

public final class Injector {
  public static final String INJECTOR_SERVICE = "com.tu.reviews.injector";

  private Injector() {
    throw new AssertionError("No instances.");
  }

  @SuppressWarnings({ "ResourceType", "WrongConstant" }) // Explicitly doing a custom service.
  public static AppComponent obtain(Context context) {
    return (AppComponent) context.getSystemService(INJECTOR_SERVICE);
  }

  public static boolean matchesService(String name) {
    return INJECTOR_SERVICE.equals(name);
  }
}
