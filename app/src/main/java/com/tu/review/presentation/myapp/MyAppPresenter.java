package com.tu.review.presentation.myapp;

import com.tu.core.mvp.RxPresenter;
import com.tu.core.rx.EndSubscriber;
import com.tu.review.data.model.AppInfo;
import com.tu.review.di.scope.MyAppScope;
import com.tu.review.domain.repository.MyAppRepository;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rx.functions.Func1;

/**
 * @author tu enum@foxmail.com.
 */

@MyAppScope public final class MyAppPresenter extends RxPresenter<MyAppView> {
  private final MyAppRepository repository;

  @Inject public MyAppPresenter(MyAppRepository repository) {
    this.repository = repository;
  }

  public void loadDetail(String packageName) {
    baseView.showLoading();
    subscriptions.add(
        observeOnView(repository.detail(packageName).map(new Func1<ResponseBody, AppInfo>() {
          @Override public AppInfo call(ResponseBody responseBody) {
            AppInfo.Builder builder;
            try {
              Document document = Jsoup.parse(responseBody.byteStream(), "utf-8", "");
              Elements elements = document.getElementsByClass("det-ins-container J_Mod ");
              builder = AppInfo.newBuilder();
              if (!elements.isEmpty()) {
                Element element = elements.get(0);
                Elements baseInfoElements = element.getElementsByClass("det-ins-data");
                if (!baseInfoElements.isEmpty()) {
                  Element baseInfoElement = baseInfoElements.get(0);
                  Elements appNameElements = baseInfoElement.getElementsByClass("det-name-int");
                  if (!appNameElements.isEmpty()) {
                    builder.appName(appNameElements.get(0).text());
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
            }
          }
        }));
  }
}
