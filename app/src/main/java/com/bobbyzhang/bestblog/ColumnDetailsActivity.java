package com.bobbyzhang.bestblog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bumiemac001 on 2017/9/13.
 */

public class ColumnDetailsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    @BindView(R.id.wv)
    WebView webView;
    @BindView(R.id.srf_ac)
    SwipeRefreshLayout srf_ac;
    @BindView(R.id.ll_fabs)
    LinearLayout ll_fabs;
    @BindView(R.id.fab_start)
    FloatingActionButton fab_start;
    @BindView(R.id.fab_startok)
    FloatingActionButton fab_startok;

    private String blogUrl;
    private static final String TAG="@xun";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_column);
        ButterKnife.bind(this);

        srf_ac.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        srf_ac.setOnRefreshListener(this);
        srf_ac.setRefreshing(true);

        blogUrl=getIntent().getStringExtra("url");
        Log.e("@xun","address:"+blogUrl);
        openUrl(blogUrl);
    }

    private void openUrl(String url){
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
                    ll_fabs.setVisibility(View.VISIBLE);
                    srf_ac.setRefreshing(false);
                } else {
                    // 加载中
                    Log.e(TAG,"加载中");
                }

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e(TAG,"title:" +title);
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


    @OnClick({R.id.fab_home,R.id.fab_start,R.id.fab_startok,
            R.id.fab_up})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab_home:
                finish();
                break;
            case R.id.fab_start:
                fab_startok.setVisibility(View.VISIBLE);
                fab_start.setVisibility(View.GONE);
                break;
            case R.id.fab_startok:
                fab_start.setVisibility(View.VISIBLE);
                fab_startok.setVisibility(View.GONE);
                break;
            case R.id.fab_up:
                webView.scrollTo(0,0);
                break;
        }
    }


}
