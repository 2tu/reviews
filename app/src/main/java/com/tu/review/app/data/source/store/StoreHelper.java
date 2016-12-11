package com.tu.review.app.data.source.store;

import com.tu.review.app.data.model.AppInfo;
import com.tu.review.app.data.model.Store;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import timber.log.Timber;

import static com.tu.review.app.data.model.Store.MY_APP;

/**
 * @author tu enum@foxmail.com.
 */

public final class StoreHelper {

  public static AppInfo parse(String packageName, Store store, ResponseBody responseBody) {
    switch (store) {
      case MY_APP:
        return parseByMyApp(responseBody, packageName);
      case MI:
        return parseByMi(responseBody, packageName);
    }
    return null;
  }

  /**
   * @param responseBody
   * @param packageName
   * @return
   */
  private static AppInfo parseByMyApp(ResponseBody responseBody, String packageName) {
    AppInfo.Builder builder;
    try {
      Document document = Jsoup.parse(responseBody.byteStream(), "utf-8", "");
      Elements elements = document.getElementsByClass("det-ins-container J_Mod ");
      builder = AppInfo.newBuilder();
      if (!elements.isEmpty()) {
        builder.store(MY_APP).packageName(packageName);
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
            // FIXME: 16/11/25 未能获取到下载量(动态)
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
                      (long) (Float.parseFloat(count.substring(0, count.length() - 1)) * 10000));
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
      } else {
        return null;
      }
    } catch (IOException e) {
      return null;
    }
    return builder.build();
  }

  /**
   * @param responseBody
   * @param packageName
   * @return
   */
  private static AppInfo parseByMi(ResponseBody responseBody, String packageName) {
    return null;
  }
}
