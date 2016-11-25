package com.tu.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {
  @BindView(R.id.activity_web) WebView webView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.loadUrl("http://android.myapp.com/myapp/detail.htm?apkName=com.msxf.loan");
  }
}
