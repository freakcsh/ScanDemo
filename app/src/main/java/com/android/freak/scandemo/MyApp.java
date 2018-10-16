package com.android.freak.scandemo;

import android.app.Application;
import android.util.DisplayMetrics;

import com.android.freak.scandemo.zxing.DisplayUtil;

/**
 * Created by Administrator on 2018/10/16.
 */

public class MyApp extends Application {
    public static MyApp getMyApp() {
        return mMyApp;
    }

    private static MyApp mMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApp = this;
        /**
         * 初始化尺寸工具类
         */
        initDisplayOpinion();
    }


    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
}
