package com.bobbyzhang.bestblog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bumiemac001 on 2017/9/13.
 */

public class ColumnActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column);
        Toast.makeText(getApplicationContext(),"欢迎回来！",Toast.LENGTH_SHORT).show();
        openUrl();
    }

    private void openUrl(){
        webView = (WebView) findViewById(R.id.wv);
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);//支持js
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl("http://blog.itbobby.top/");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                } else {
                    // 加载中

                }

            }
        });
    }




}
