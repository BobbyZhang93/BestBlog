package com.bobbyzhang.bestblog.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by BobbyZhang on 2017/10/24.
 */

public class NetUtil {
    private static final String TAG="@xun";

    public void NetHelper(){

    }

    public boolean checkNetWorkStatus(Context context){
        boolean result;
        ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if ( netinfo !=null && netinfo.isConnected() ) {
            result=true;
            Log.i(TAG, "The net was connected" );
        }else{
            Log.i(TAG, "The net was bad" );
            result=false;
        }
        return result;
    }

    public boolean checkURL(String url){
        boolean value=false;
        try {
            HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
            int code=conn.getResponseCode();
            Log.e(TAG,"code:"+code);
            if(code!=200){
                value=false;
                Log.e(TAG,"链接无效" );
            }else{
                value=true;
                Log.e(TAG,"链接有效" );
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
