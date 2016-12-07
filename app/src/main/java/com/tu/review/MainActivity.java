package com.tu.review;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.tu.review.data.model.AppInfo;
import com.tu.review.di.Injector;
import com.tu.review.di.components.DaggerReviewComponent;
import com.tu.review.presentation.ReviewPresenter;
import com.tu.review.presentation.ReviewView;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements ReviewView {
  @Inject ReviewPresenter presenter;
  @BindView(R.id.edittext) EditText editText;
  @BindView(R.id.score_ratingbar) RatingBar scoreRatingBar;
  @BindView(R.id.name_tv) TextView nameTextView;
  @BindView(R.id.score_tv) TextView scoreTextView;
  @BindView(R.id.count_tv) TextView countTextView;
  @BindView(R.id.logo_imageview) ImageView logoImageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DaggerReviewComponent.builder()
        .appComponent(Injector.obtain(getApplicationContext()))
        .build()
        .inject(this);
    delegatePresenter(presenter, this);
  }

  @OnClick(R.id.save_button) void save() {
    presenter.load(editText.getText().toString());
  }

  @Override public void showEmptyView() {

  }

  @Override public void showRetryView() {

  }

  @Override public void showMyAppInfo(AppInfo appInfo) {
    picasso.load(appInfo.icon).into(logoImageView);
    nameTextView.setText(appInfo.appName);
    scoreRatingBar.setRating(appInfo.score);
    scoreTextView.setText(
        String.valueOf(appInfo.score) + "分  （" + String.valueOf(appInfo.reviewCount) + "人评论）");
    countTextView.setText(
        String.valueOf(appInfo.downCount) + "下载  " + String.valueOf(appInfo.appSize));
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }
}
