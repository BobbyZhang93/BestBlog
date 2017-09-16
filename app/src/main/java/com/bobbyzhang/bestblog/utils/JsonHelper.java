package com.bobbyzhang.bestblog.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by BobbyZhang on 2017/9/16.
 */

public class JsonHelper {
    public String ReadJson(Context context,String fileName){
        Context mContext;
        String test_package = "com.bobbyzhang.bestblog";
        String jsonResult = null;
        try
        {
            mContext = context.createPackageContext(
                    test_package, Context.CONTEXT_IGNORE_SECURITY);
            AssetManager s =  mContext.getAssets();
            try{
                InputStream is = s.open(fileName);
                byte [] buffer = new byte[is.available()] ;
                is.read(buffer);
                String json = new String(buffer,"utf-8");
                is.close();
                jsonResult=json;
            }catch(IOException e){
                e.printStackTrace();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
