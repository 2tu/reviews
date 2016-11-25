package com.tu.review;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.tu.review.data.model.AppInfo;
import com.tu.review.di.Injector;
import com.tu.review.di.components.DaggerMyAppComponent;
import com.tu.review.presentation.myapp.MyAppPresenter;
import com.tu.review.presentation.myapp.MyAppView;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MyAppView{
  @Inject MyAppPresenter presenter;
  @BindView(R.id.edittext) EditText editText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DaggerMyAppComponent.builder()
        .appComponent(Injector.obtain(getApplicationContext()))
        .build()
        .inject(this);
    delegatePresenter(presenter, this);
  }

  @OnClick(R.id.save_button) void save() {
    //startActivity(new Intent(this, WebActivity.class));
    presenter.loadDetail(editText.getText().toString());
    //AVObject testObject = new AVObject("Test" + editText.getText().toString());
    //testObject.put("words", "Hello World!");
    //testObject.saveInBackground(new SaveCallback() {
    //  @Override public void done(AVException e) {
    //    if (e == null) {
    //      Log.d("saved", "success!");
    //    } else {
    //      Log.e("av", "save error", e);
    //    }
    //  }
    //});
  }

  @Override public void showEmptyView() {

  }

  @Override public void showRetryView() {

  }

  @Override public void showMyAppInfo(AppInfo appInfo) {
    Toast.makeText(this,appInfo.appName,Toast.LENGTH_LONG).show();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }
}
