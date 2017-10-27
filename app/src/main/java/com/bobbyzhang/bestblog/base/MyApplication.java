package com.bobbyzhang.bestblog.base;

import android.app.Application;

import com.xsj.crasheye.Crasheye;

/**
 * Created by BobbyZhang on 2017/10/26.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Crasheye.init(this, "0b24e040");
    }
}
