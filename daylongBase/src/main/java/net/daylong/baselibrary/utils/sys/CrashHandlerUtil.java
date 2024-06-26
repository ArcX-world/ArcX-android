package net.daylong.baselibrary.utils.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.orhanobut.logger.Logger;

import net.daylong.baselibrary.utils.MyLogUtil;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CrashHandlerUtil implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandlerUtil";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandlerUtil INSTANCE = new CrashHandlerUtil();
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
    private String crashTip = "应用开小差了，稍后重启下，亲！";

    public String getCrashTip() {
        return crashTip;
    }

    public void setCrashTip(String crashTip) {
        this.crashTip = crashTip;
    }


    private CrashHandlerUtil() {
    }


    public static CrashHandlerUtil getInstance() {
        return INSTANCE;
    }


    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     *
     * @param thread 线程
     * @param ex     异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Logger.e("error : ", e);
                e.printStackTrace();
            }
            //退出程序
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param throwable 异常
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        }

        isEnd = false;
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                throwable.printStackTrace();

                Looper.loop();
            }
        }.start();


        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2FileJson(throwable);


        while (!isEnd) {
        }

        return true;
    }

    public static boolean isEnd = false;

    /**
     * 收集设备参数信息
     *
     * @param ctx 上下文
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }

            MyLogUtil.e("日志收集->");
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e("an error occured when collect package info", e);
            MyLogUtil.e("日志收集->失败");
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
//                Logger.e(field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Logger.e("an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfo2File(Throwable ex) {
        MyLogUtil.e("日志收集->保存信息");

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        MyLogUtil.e("日志收集->保存信息" + sb.toString());
        MyLogUtil.writeLog("crash", sb.toString());
        Logger.e(sb.toString());

        /*
        这个 crashInfo 就是我们收集到的所有信息，可以做一个异常上报的接口用来提交用户的crash信息
         */
        String crashInfo = sb.toString();
        Log.d("tag", "------crashInfo----->" + crashInfo);
        //1.创建OkHttpClient对象


//        return null;
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex 异常
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfo2FileJson(Throwable ex) {
        MyLogUtil.e("日志收集->保存信息");

        JSONObject jsonObject = new JSONObject();
        try {

            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                jsonObject.put(key.toLowerCase(), value);

            }
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String result = writer.toString();
            jsonObject.put("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        AppManager.getInstance().exitApp();
//        CrashHandlerUtil.post(jsonObject.toString());

//        return null;
    }

    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            //"\\u只是代号，请根据具体所需添加相应的符号"
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static void post(String crashInfo) {


        if (isLogin()) {
            //FileUploadServiceImpl.upload(crashInfo);
        }
    }


    private static boolean isLogin() {
        return false;
    }


}
