package com.bobbyzhang.bestblog.base;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.xsj.crasheye.Crasheye;

/**
 * Created by BobbyZhang on 2017/10/26.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Crasheye.init(this, "0b24e040");//接入crasheye
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"S0QwaXok0D25AhW1PHz5POw6-gzGzoHsz","sJTJwppz8IOiPeVvKYNqIygU");
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true);
    }
}
