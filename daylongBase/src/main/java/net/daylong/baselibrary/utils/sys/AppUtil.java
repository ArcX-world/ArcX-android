package net.daylong.baselibrary.utils.sys;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.content.FileProvider;

import net.daylong.baselibrary.app.AppManager;
import net.daylong.baselibrary.app.BaseApplication;
import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.ui.act.BaseActivity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public class AppUtil {

    /**
     * 获取全局handler
     *
     * @return
     */
    public static Handler getMainHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * 获取UI主线程id
     *
     * @return
     */
    public static int getMainThreadId() {

        return BaseApplication.getMainThreadId();
    }


    public static BaseActivity getCurrentActivity() {
        return AppManager.getInstance().getCurrentActivity();

    }

    /**
     * 是否运行在UI主线程
     *
     * @return
     */
    public static boolean isRunOnUIThread() {
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 运行在UI主线程
     *
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            runnable.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getMainHandler().post(runnable);
        }
    }

    /**
     * 获取上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getAppContext();
    }

    /**
     * 判断App是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isAppRunningForegroud(Context context) {
        if (context != null) {
            try {
                ActivityManager activityManager = (ActivityManager) context
                        .getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager
                        .getRunningAppProcesses();
                if (runningAppProcesses == null) {
                    return false;
                }
                for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
                    if (runningAppProcess.processName.equals(context.getPackageName()) &&
                            runningAppProcess.importance == ActivityManager
                                    .RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager
                    .getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            MyLogUtil.d("Exception:---" + e);
        }
        return versionName;
    }  /**
     * 获取版本名称
     *
     * @return
     */
    public static String getAppVersionName() {

        return getAppVersionName(AppUtil.getContext());
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager
                    .getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            MyLogUtil.d("Exception:---" + e);
        }
        return versionCode;
    }


    /**
     * 判断手机是否安装SDCard
     *
     * @return
     */
    public static boolean isHasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机SDCard路径
     *
     * @return
     */
    public static String getSDCardPath() {
        File sdDir = null;
        if (isHasSDCard()) {
            sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        }
        if (sdDir != null) {
            return sdDir.getPath();
        }
        return "";
    }

    /**
     * 显示软键盘
     *
     * @param editText
     */
    public static void showSoftInput(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) AppManager.getInstance().getCurrentActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param editText
     */
    public static void hideSoftInput(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);


        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken()
                , InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @param context
     * @param phone
     * @param immediate true:直接拨打  false:拨号界面
     */
    public static void callPhone(Context context, String phone, boolean immediate) {
        Intent intent;
        if (immediate) {
            intent = new Intent(Intent.ACTION_CALL);
        } else {
            intent = new Intent(Intent.ACTION_DIAL);
        }
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }


    public static boolean isServiceRunning(Context mContext, String className) {

        long l = System.currentTimeMillis();

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(30);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            String className1 = serviceList.get(i).service.getClassName();

            if (className1.equals(className)) {
                isRunning = true;
                break;
            }
        }

        long l2 = System.currentTimeMillis();
        return isRunning;
    }


    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    private static String getDeviceUUID() {
        try {
            String dev = "3883756" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.HARDWARE.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.SERIAL.length() % 10;
            return new UUID(dev.hashCode(),
                    Build.SERIAL.hashCode()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


    /**
     * 获取尺寸
     *
     * @param size
     * @return
     */
    public static int getSize(float size) {
        return SystemUtil.getViewHeight(size);
    }

    /**
     * 判断横竖屏
     *
     * @param activity
     * @return 1：竖 | 0：横
     */
    public static int getScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        return orient;
    }


    /**
     * 是否横屏
     *
     * @return
     */
    public static boolean isHorizontal() {
        return getScreenOrient(AppUtil.getCurrentActivity()) == 0;
    }


    /**
     * 隐藏软键盘
     */
    public static void hintInput(Activity activity) {
        try {

            InputMethodManager mInputMethodManager = (InputMethodManager) activity.getSystemService
                    (Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void installApk( String newApkPath) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        File apkFile = new File(newApkPath);

        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".fileprovider", apkFile);

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        BaseApplication.getInstance().startActivity(intent);
        //android.os.Process.killProcess(android.os.Process.myPid());

    }

    public static String getSignatureMD5(Context context) {
        try {
            // 获取包管理器
            PackageManager packageManager = context.getPackageManager();

            // 获取包信息
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

            // 获取签名信息
            Signature[] signatures = packageInfo.signatures;

            // 计算MD5值
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signatures[0].toByteArray());
            byte[] md5Bytes = md.digest();

            // 转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                sb.append(Integer.toString((md5Byte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

}
