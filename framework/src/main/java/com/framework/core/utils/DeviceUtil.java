package com.framework.core.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

/**
 * Created by laulee on 2018/4/26.
 */

public class DeviceUtil {

    private static String deviceId;

    /**
     * 获取设备唯一ID
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceOnlyId( Context context ) {
        /**
         * 解决android7.0拿不到
         */
        if( deviceId != null ) {
            return deviceId;
        } else {
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED){
                //android.telephony.TelephonyManager
                TelephonyManager mTelephony = (TelephonyManager) context
                        .getSystemService( Context.TELEPHONY_SERVICE );
                deviceId = mTelephony.getDeviceId( );
                if(deviceId==null){
                    deviceId = Settings.Secure.getString( context.getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID );
                }
                return deviceId;
            }else{
                return "";
            }
        }
    }
}
