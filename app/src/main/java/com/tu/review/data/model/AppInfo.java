package com.tu.review.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author tu enum@foxmail.com.
 */

public class AppInfo implements Parcelable {
  public String appName;
  public float score;
  public int reviewCount;

  private AppInfo(Builder builder) {
    appName = builder.appName;
    score = builder.score;
    reviewCount = builder.reviewCount;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.appName);
    dest.writeFloat(this.score);
    dest.writeInt(this.reviewCount);
  }

  public AppInfo() {
  }

  protected AppInfo(Parcel in) {
    this.appName = in.readString();
    this.score = in.readFloat();
    this.reviewCount = in.readInt();
  }

  public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
    @Override public AppInfo createFromParcel(Parcel source) {
      return new AppInfo(source);
    }

    @Override public AppInfo[] newArray(int size) {
      return new AppInfo[size];
    }
  };

  public static final class Builder {
    private String appName;
    private float score;
    private int reviewCount;

    private Builder() {
    }

    public Builder appName(String val) {
      appName = val;
      return this;
    }

    public Builder score(float val) {
      score = val;
      return this;
    }

    public Builder reviewCount(int val) {
      reviewCount = val;
      return this;
    }

    public AppInfo build() {
      return new AppInfo(this);
    }
  }
}
