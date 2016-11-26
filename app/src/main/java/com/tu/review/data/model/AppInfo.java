package com.tu.review.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.avos.avoscloud.AVObject;

/**
 * @author tu enum@foxmail.com.
 */

public class AppInfo extends BaseAV implements Parcelable {
  public String appName;
  public String packageName;
  public float score;
  public int reviewCount;
  public long downCount;
  public String appSize;
  public String category;
  public String icon;
  public Store store;

  private AppInfo(Builder builder) {
    appName = builder.appName;
    packageName = builder.packageName;
    score = builder.score;
    reviewCount = builder.reviewCount;
    downCount = builder.downCount;
    appSize = builder.appSize;
    category = builder.category;
    icon = builder.icon;
    store = builder.store;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public AppInfo() {
  }

  public static final class Builder {
    private String appName;
    private String packageName;
    private float score;
    private int reviewCount;
    private long downCount;
    private String appSize;
    private String category;
    private String icon;
    private Store store;

    private Builder() {
    }

    public Builder appName(String val) {
      appName = val;
      return this;
    }

    public Builder packageName(String val) {
      packageName = val;
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

    public Builder downCount(long val) {
      downCount = val;
      return this;
    }

    public Builder appSize(String val) {
      appSize = val;
      return this;
    }

    public Builder category(String val) {
      category = val;
      return this;
    }

    public Builder icon(String val) {
      icon = val;
      return this;
    }

    public Builder store(Store val) {
      store = val;
      return this;
    }

    public AppInfo build() {
      return new AppInfo(this);
    }
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.appName);
    dest.writeString(this.packageName);
    dest.writeFloat(this.score);
    dest.writeInt(this.reviewCount);
    dest.writeLong(this.downCount);
    dest.writeString(this.appSize);
    dest.writeString(this.category);
    dest.writeString(this.icon);
    dest.writeInt(this.store == null ? -1 : this.store.ordinal());
  }

  protected AppInfo(Parcel in) {
    this.appName = in.readString();
    this.packageName = in.readString();
    this.score = in.readFloat();
    this.reviewCount = in.readInt();
    this.downCount = in.readLong();
    this.appSize = in.readString();
    this.category = in.readString();
    this.icon = in.readString();
    int tmpStore = in.readInt();
    this.store = tmpStore == -1 ? null : Store.values()[tmpStore];
  }

  public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
    @Override public AppInfo createFromParcel(Parcel source) {
      return new AppInfo(source);
    }

    @Override public AppInfo[] newArray(int size) {
      return new AppInfo[size];
    }
  };

  @Override public String toString() {
    return "AppInfo{" +
        "appName='" + appName + '\'' +
        ", packageName='" + packageName + '\'' +
        ", score=" + score +
        ", reviewCount=" + reviewCount +
        ", downCount=" + downCount +
        ", appSize='" + appSize + '\'' +
        ", category='" + category + '\'' +
        ", icon='" + icon + '\'' +
        ", store=" + store +
        '}';
  }

  public AVObject getAVObject() {
    AVObject appInfo = new AVObject("app_info");
    appInfo.put("appName", appName);
    appInfo.put("packageName", packageName);
    appInfo.put("score", score);
    appInfo.put("reviewCount", reviewCount);
    appInfo.put("downCount", downCount);
    appInfo.put("appSize", appSize);
    appInfo.put("category", category);
    appInfo.put("icon", icon);
    if (null != store) {
      appInfo.put("store", store.state);
    }
    return appInfo;
  }
}
