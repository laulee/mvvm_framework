package com.framework.core.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.text.ClipboardManager;
import android.util.Log;

import com.framework.core.app.BaseApplication;
import com.framework.core.manager.AppManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/16.
 * <p>
 * 异常处理
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/Crash/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    private BaseApplication mContext;
    private final boolean DEBUG = true;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        return CrashHandlerInstance.instance;
    }

    private static class CrashHandlerInstance {
        static CrashHandler instance = new CrashHandler();
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = (BaseApplication) context.getApplicationContext();
    }

    /**
     * 系统未被捕获的异常由默认的配置捕获
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        if (mContext.isDebug()) {
            //打印当前调用栈信息
            ex.printStackTrace();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final StringWriter stringWriter = new StringWriter();
                        Looper.prepare();
                        ClipboardManager clipboardManager = (ClipboardManager) mContext
                                .getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboardManager.setText(stringWriter.toString());
                        ex.printStackTrace(new PrintWriter(stringWriter));
                        AlertDialog dialog = new AlertDialog.Builder(AppManager.getAppManager().currentActivity()).setTitle("提示")
                                .setCancelable(false).setMessage("很抱歉，当前程序出现异常：\n" + stringWriter.toString())
                                .setNeutralButton("关闭", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                }).setPositiveButton("复制异常信息", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ClipboardManager clipboardManager = (ClipboardManager) mContext
                                                .getSystemService(Context.CLIPBOARD_SERVICE);
                                        clipboardManager.setText(stringWriter.toString());
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                }).create();
                        dialog.show();
                        Looper.loop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            try {
                dumpExceptionToSDCard(ex);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将异常上传
            uploadExceptionToServer();
            //防止华为等部分机型自动终止程序
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * 将异常保存到文件
     *
     * @param ex
     */
    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        // 如果没有读写SD卡的权限，则不写入文件
        if (ContextCompat.checkSelfPermission(AppManager.getAppManager().currentActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (DEBUG) {
                    Log.d(TAG, "sdcard unmounted,skip dump exception");
                    return;
                }
            }

            File dir = new File(PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            long currentTime = System.currentTimeMillis();
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTime));

            File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                printWriter.println(time);
                dumpPhoneInfo(printWriter);
                printWriter.println();
                ex.printStackTrace(printWriter);
                printWriter.close();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param pw
     */
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        // 应用版本
        pw.print("App Version Name: ");
        pw.println(packageInfo.versionName);
        // 应用版本号
        pw.print("App Version Code: ");
        pw.println(packageInfo.versionCode);

        // android版本
        pw.print("Android OS Version Name: ");
        pw.println(Build.VERSION.RELEASE);
        // android版本号
        pw.print("Android OS Version Code: ");
        pw.println(Build.VERSION.SDK_INT);

        // 手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        // 手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);
    }


    /**
     * 上传log到服务端
     */
    private void uploadExceptionToServer() {
        // TODO Upload Exception Message To Your Web Server
    }
}
