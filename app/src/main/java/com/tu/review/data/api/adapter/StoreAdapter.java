package com.tu.review.data.api.adapter;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.tu.review.data.model.Store;
import java.io.IOException;

/**
 * @author tu enum@foxmail.com.
 */

public final class StoreAdapter extends JsonAdapter<Store> {

  @Override public Store fromJson(JsonReader reader) throws IOException {
    return Store.from(reader.nextString());
  }

  @Override public void toJson(JsonWriter writer, Store value) throws IOException {
    writer.value(value == null ? null : value.state);
  }
}