package com.framework.core.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * 跟App相关的辅助类
 */
public class AppUtils {

    public static final String TAG = "AppUtils";

    private AppUtils() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    // 获取MetaValue
    public static String getMetaValue(Context ct, String metaKey) {
        Bundle metaData = null;
        String metaValue = null;
        if (ct == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = ct.getPackageManager()
                    .getApplicationInfo(ct.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                metaValue = metaData.getString(metaKey);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return metaValue;
    }

    /**
     * 获取targetsdkversion
     *
     * @param context
     * @return
     */
    public static int getTargetSDKVersion(Context context) {
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            int targetSdkVersion = info.applicationInfo.targetSdkVersion;
            return targetSdkVersion;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取Android系统版本
     *
     * @return
     */
    public static String getSysVersion() {
        return "android" + Build.VERSION.RELEASE;
    }
}