package com.bobbyzhang.bestblog;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class ColumnDetailsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private WebView webView;
    private String blogUrl;
    private static final String TAG="@xun";
    SwipeRefreshLayout srf_ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_column);
        srf_ac= (SwipeRefreshLayout) findViewById(R.id.srf_ac);
        srf_ac.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        srf_ac.setOnRefreshListener(this);
        srf_ac.setRefreshing(true);

        blogUrl=getIntent().getStringExtra("url");
        Log.e("@xun","111"+blogUrl);
        openUrl(blogUrl);
    }

    private void openUrl(String url){
        webView = (WebView) findViewById(R.id.wv);
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);//支持js
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.loadUrl(url);

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
                    Log.e(TAG,"完成");
                    srf_ac.setRefreshing(false);
                } else {
                    // 加载中
                    Log.e(TAG,"加载中");
                }

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onRefresh() {
        srf_ac.postDelayed(new Runnable() {
            @Override
            public void run() {
                srf_ac.setRefreshing(false);
            }
        }, 2000);
    }
}
