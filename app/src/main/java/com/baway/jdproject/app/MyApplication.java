package com.baway.jdproject.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "bee54e6900", false);
        Fresco.initialize(this);
        Config.DEBUG=true;
        UMShareAPI.get(this);
    }
    {
        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("1106532008","m7rO9VdOCioZGTFj");
        PlatformConfig.setSinaWeibo("","","");
    }
}
