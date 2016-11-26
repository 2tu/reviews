package com.tu.review.presentation.myapp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.tu.core.mvp.RxPresenter;
import com.tu.core.rx.EndSubscriber;
import com.tu.review.data.model.AppInfo;
import com.tu.review.data.model.Store;
import com.tu.review.data.source.MyAppRepository;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * @author tu enum@foxmail.com.
 */

public final class MyAppPresenter extends RxPresenter<MyAppView> {
  private final MyAppRepository repository;

  @Inject public MyAppPresenter(MyAppRepository repository) {
    this.repository = repository;
  }

  public void loadDetail(final String packageName) {
    baseView.showLoading();
    subscriptions.add(
        observeOnView(repository.detail(packageName).map(new Func1<ResponseBody, AppInfo>() {
          @Override public AppInfo call(ResponseBody responseBody) {
            if (MediaType.parse("application/json; charset=utf-8").equals(responseBody.contentType())) {
              Moshi moshi = new Moshi.Builder().build();
              JsonAdapter<AppInfo> jsonAdapter = moshi.adapter(AppInfo.class);
              try {
                return jsonAdapter.fromJson(responseBody.string());
              } catch (IOException e) {
                e.printStackTrace();
                return null;
              }
            }
            AppInfo.Builder builder;
            try {
              Document document = Jsoup.parse(responseBody.byteStream(), "utf-8", "");
              Elements elements = document.getElementsByClass("det-ins-container J_Mod ");
              builder = AppInfo.newBuilder();
              if (!elements.isEmpty()) {
                builder.store(Store.MY_APP).packageName(packageName);
                Element element = elements.first();
                Elements iconElements = element.getElementsByClass("det-icon");
                if (!iconElements.isEmpty()) {
                  builder.icon(iconElements.first().select("img[src]").attr("src"));
                }

                Elements baseInfoElements = element.getElementsByClass("det-ins-data");
                if (!baseInfoElements.isEmpty()) {
                  Element baseInfoElement = baseInfoElements.first();
                  Elements appNameElements = baseInfoElement.getElementsByClass("det-name-int");
                  if (!appNameElements.isEmpty()) {
                    builder.appName(appNameElements.first().text());
                  }
                  Elements starElements = baseInfoElement.getElementsByClass("det-star-box");
                  if (!starElements.isEmpty()) {
                    final Element starElement = starElements.first();
                    Elements scoreElements = starElement.getElementsByClass("com-blue-star-num");
                    if (!scoreElements.isEmpty()) {
                      try {
                        builder.score(Float.parseFloat(scoreElements.first()
                            .text()
                            .substring(0, scoreElements.first().text().length() - 1)));
                      } catch (NumberFormatException e) {
                        Timber.e(e);
                      }
                    }
                    Element downloadCountElement = baseInfoElement.getElementById("J_CommentCount");
                    if (null != downloadCountElement) {
                      builder.reviewCount(Integer.valueOf(downloadCountElement.text()));
                    }
                  }
                  Elements appElements = baseInfoElement.getElementsByClass("det-insnum-line");
                  Element appElement = appElements.first();
                  if (null != appElement) {
                    // FIXME: 16/11/25 未能动态获取到值
                    Element downCountElement = appElement.getElementsByClass("det-ins-num").first();
                    if (null != downCountElement) {
                      String count = downCountElement.text().replace("下载", "");
                      String suffix = count.substring(count.length() - 1);
                      try {
                        if ("亿".equals(suffix)) {
                          builder.downCount(Long.parseLong(String.valueOf(
                              Float.parseFloat(count.substring(count.length() - 1)) * 100000000)));
                        } else if ("万".equals(suffix)) {
                          builder.downCount(
                              (long) (Float.parseFloat(count.substring(0, count.length() - 1))
                                  * 10000));
                        } else {
                          builder.downCount(Long.parseLong(count));
                        }
                      } catch (NumberFormatException e) {
                        Timber.e("download count error", e);
                      }
                    }

                    Element sizeElement = appElement.getElementsByClass("det-size").first();
                    if (null != sizeElement) {
                      builder.appSize(sizeElement.text());
                    }
                    Element categoryElement = appElement.getElementById("J_DetCate");
                    if (null != categoryElement) {
                      builder.category(categoryElement.text());
                    }
                  }
                }
              }
            } catch (IOException e) {
              return null;
            }
            return builder.build();
          }
        })).subscribe(new EndSubscriber<AppInfo>() {
          @Override public void onEnd() {
            baseView.hideLoading();
          }

          @Override public void onError(Throwable e) {
            super.onError(e);
          }

          @Override public void onNext(AppInfo appInfo) {
            if (null == appInfo) {
              baseView.showEmptyView();
            } else {
              baseView.showMyAppInfo(appInfo);
              saveAppInfo(appInfo);
            }
          }
        }));
  }

  public void saveAppInfo(AppInfo appInfo) {
    appInfo.getAVObject().saveInBackground();
  }
}
