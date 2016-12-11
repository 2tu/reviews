package com.tu.review.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Tu enum@foxmail.com.
 */

public class ReviewInfo implements Parcelable {
  public String packageName;
  public float score;
  public Store store;
  public String objectId;
  public String version;
  public String content;
  public String reviewTime;
  public String nickName;
  public String device;

  private ReviewInfo(Builder builder) {
    packageName = builder.packageName;
    score = builder.score;
    store = builder.store;
    objectId = builder.objectId;
    version = builder.version;
    content = builder.content;
    reviewTime = builder.reviewTime;
    nickName = builder.nickName;
    device = builder.device;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String packageName;
    private float score;
    private Store store;
    private String objectId;
    private String version;
    private String content;
    private String reviewTime;
    private String nickName;
    private String device;

    private Builder() {
    }

    public Builder packageName(String val) {
      packageName = val;
      return this;
    }

    public Builder score(float val) {
      score = val;
      return this;
    }

    public Builder store(Store val) {
      store = val;
      return this;
    }

    public Builder objectId(String val) {
      objectId = val;
      return this;
    }

    public Builder version(String val) {
      version = val;
      return this;
    }

    public Builder content(String val) {
      content = val;
      return this;
    }

    public Builder reviewTime(String val) {
      reviewTime = val;
      return this;
    }

    public Builder nickName(String val) {
      nickName = val;
      return this;
    }

    public Builder device(String val) {
      device = val;
      return this;
    }

    public ReviewInfo build() {
      return new ReviewInfo(this);
    }
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.packageName);
    dest.writeFloat(this.score);
    dest.writeInt(this.store == null ? -1 : this.store.ordinal());
    dest.writeString(this.objectId);
    dest.writeString(this.version);
    dest.writeString(this.content);
    dest.writeString(this.reviewTime);
    dest.writeString(this.nickName);
    dest.writeString(this.device);
  }

  protected ReviewInfo(Parcel in) {
    this.packageName = in.readString();
    this.score = in.readFloat();
    int tmpStore = in.readInt();
    this.store = tmpStore == -1 ? null : Store.values()[tmpStore];
    this.objectId = in.readString();
    this.version = in.readString();
    this.content = in.readString();
    this.reviewTime = in.readString();
    this.nickName = in.readString();
    this.device = in.readString();
  }

  public static final Creator<ReviewInfo> CREATOR =
      new Creator<ReviewInfo>() {
        @Override public ReviewInfo createFromParcel(Parcel source) {
          return new ReviewInfo(source);
        }

        @Override public ReviewInfo[] newArray(int size) {
          return new ReviewInfo[size];
        }
      };
}
