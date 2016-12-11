package com.tu.review.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.avos.avoscloud.AVObject;

/**
 * @author tu enum@foxmail.com.
 */

public class AppInfo implements Parcelable {
  public String appName;
  public String packageName;
  public float score;
  public int reviewCount;
  public long downCount;
  public String appSize;
  public String category;
  public String icon;
  public Store store;
  public String objectId;
  public String version;

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
    objectId = builder.objectId;
    version = builder.version;
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
    private String objectId;
    private String version;
    private String createAt;
    private String updateAt;

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

    public Builder objectId(String val) {
      objectId = val;
      return this;
    }

    public Builder version(String val) {
      version = val;
      return this;
    }

    public Builder createAt(String val) {
      createAt = val;
      return this;
    }

    public Builder updateAt(String val) {
      updateAt = val;
      return this;
    }
  }

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
        ", objectId='" + objectId + '\'' +
        ", version='" + version + '\'' +
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
    appInfo.put("version", version);
    appInfo.setObjectId(objectId);
    if (null != store) {
      appInfo.put("store", store.state);
    }
    return appInfo;
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
    dest.writeString(this.objectId);
    dest.writeString(this.version);
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
    this.objectId = in.readString();
    this.version = in.readString();
  }

  public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
    @Override public AppInfo createFromParcel(Parcel source) {
      return new AppInfo(source);
    }

    @Override public AppInfo[] newArray(int size) {
      return new AppInfo[size];
    }
  };
}
