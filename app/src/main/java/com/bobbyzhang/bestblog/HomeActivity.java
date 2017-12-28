package com.bobbyzhang.bestblog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.bobbyzhang.bestblog.base.BaseActivity;
import com.bobbyzhang.bestblog.bean.ArticleBean;
import com.bobbyzhang.bestblog.bean.ColumnBean;
import com.bobbyzhang.bestblog.customview.NoScrollViewPager;
import com.bobbyzhang.bestblog.utils.JsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bumiemac001 on 2017/9/13.
 * 主界面
 */

public class HomeActivity extends BaseActivity implements
        ColumnFragment.OnListFragmentInteractionListener,
        FavoriteFragment.OnListFragmentInteractionListener,
        AboutmeFragment.OnFragmentInteractionListener,
        ViewPager.OnPageChangeListener
{

    private static Boolean isExit = false;
    @BindView(R.id.vp_ha)
    NoScrollViewPager vp_ha;

    private JsonUtil mHelper;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp_ha.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    vp_ha.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    vp_ha.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mHelper=new JsonUtil();
        Gson columnGson=new Gson();
        String columnData=mHelper.ReadJson(getApplicationContext(),"bestblog_columns.json");
        ColumnBean columnBean=columnGson.fromJson(columnData,ColumnBean.class);
        Log.e("@xun-c",columnBean.getColumns().get(0).getRemark());

        Gson articleGson=new Gson();
        String articleData=mHelper.ReadJson(getApplicationContext(),"bestblog_article.json");
        ArticleBean articleBean=articleGson.fromJson(articleData,ArticleBean.class);
        Log.e("@xun-a",articleBean.getArticles().get(0).getTitle());


        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new ColumnFragment(columnBean.getColumns()));
        fgLists.add(new FavoriteFragment(articleBean.getArticles()));
        fgLists.add(new AboutmeFragment());
        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgLists.get(position);
            }

            @Override
            public int getCount() {
                return fgLists.size();
            }
        };
        vp_ha.setAdapter(mAdapter);
        vp_ha.setOffscreenPageLimit(2); //预加载剩下两页
        vp_ha.addOnPageChangeListener(this);
        vp_ha.setNoScroll(true);

    }


    /**
     * 改写物理按键——返回的逻辑
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
                exitBy2Click(); // 调用双击退出函数
        }
        return false;
    }


    /**
     * 双击退出函数
     */
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次和我说拜拜~", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onListFragmentInteraction(ColumnBean.ColumnsBean columnsBean) {
        Intent intent=new Intent();
        intent.setClass(HomeActivity.this,ColumnDetailsActivity.class);
        intent.putExtra("remark",columnsBean.getRemark());
        intent.putExtra("url",columnsBean.getUrl());
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(ArticleBean.ArticlesBean articlesBean) {
        Intent intent=new Intent();
        intent.setClass(HomeActivity.this,ColumnDetailsActivity.class);
        intent.putExtra("url",articlesBean.getUrl());
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        vp_ha.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
