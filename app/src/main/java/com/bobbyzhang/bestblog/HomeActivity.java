package com.bobbyzhang.bestblog;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.bobbyzhang.bestblog.base.BaseActivity;
import com.bobbyzhang.bestblog.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements
        ColumnFragment.OnListFragmentInteractionListener,
        FavoriteFragment.OnListFragmentInteractionListener,
        AboutmeFragment.OnFragmentInteractionListener
{

    private static Boolean isExit = false;
    @BindView(R.id.vp_ha)
    ViewPager vp_ha;

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

        final ArrayList<Fragment> fgLists = new ArrayList<>(3);
        fgLists.add(new ColumnFragment());
        fgLists.add(new FavoriteFragment());
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
    }


    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
