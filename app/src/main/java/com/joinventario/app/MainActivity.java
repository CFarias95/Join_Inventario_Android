package com.joinventario.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private WebView mWebView;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSafeBrowsingEnabled(false);
        webSettings.setAllowContentAccess(true);
        mWebView.setNetworkAvailable(true);
        mWebView.setWebViewClient(new com.joinventario.app.MyWebViewClient());
        mWebView.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_IMPORTANT, false);
        // LOCAL RESOURCE
        mWebView.loadUrl("file:///android_asset/www/index.html");
    }


    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
    {
        handler.proceed(); // When an error occurs, ignore and go on
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
