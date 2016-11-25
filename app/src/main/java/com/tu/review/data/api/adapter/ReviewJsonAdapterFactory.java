package com.tu.review.data.api.adapter;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.tu.review.data.model.Store;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author tu enum@foxmail.com.
 */

public class ReviewJsonAdapterFactory implements JsonAdapter.Factory {
  @Override
  public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
    if (type.equals(Store.class)) {
      return new StoreAdapter();
    }
    return null;
  }
}
