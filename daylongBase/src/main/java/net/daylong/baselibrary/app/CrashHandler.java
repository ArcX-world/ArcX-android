package net.daylong.baselibrary.app;

import android.content.Context;

import net.daylong.baselibrary.utils.MyLogUtil;
import net.daylong.baselibrary.utils.date.DateUtil;
import net.daylong.baselibrary.utils.sys.AppUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler sCrashHandler;
    /**
     * 系统默认UncaughtExceptionHandler
     */
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    /**
     * context
     */
    private Context mContext;
    /**
     * 存储异常和参数信息
     */
    private Map<String, String> exceptionMap = new HashMap<>();

    /**
     * 私有化
     */
    private CrashHandler(){

    }

    /**
     * 单例
     * @return
     */
    public static synchronized CrashHandler getInstance(){
        if(sCrashHandler == null){
            synchronized (CrashHandler.class){
                if(sCrashHandler == null){
                    sCrashHandler = new CrashHandler();
                }
            }
        }
        return sCrashHandler;
    }

    /**
     * 初始化
     * @param context
     */
    public void init(Context context){
        this.mContext = context;
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!handlerException(e) && mUncaughtExceptionHandler != null){
            //没有处理异常
            mUncaughtExceptionHandler.uncaughtException(t, e);
        }else{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                MyLogUtil.e(e1.toString());
            }
          AppManager.getInstance().exitApp(mContext,true);
        }
    }

    /**
     * 处理异常
     * @param throwable
     * @return 处理了该异常返回true,否则false
     */
    public boolean handlerException(Throwable throwable){
        if(throwable == null){
            return false;
        }
        collectDeviceInfo(mContext);
        saveCrashInfoToFile(throwable);
        AppManager.getInstance().exitApp(mContext,true);
        return true;
    }

    /**
     * 收集设备信息
     * @param context
     */
    private void collectDeviceInfo(Context context) {
        int versionCode = AppUtil.getVersionCode(context);
        String appVersionName = AppUtil.getAppVersionName(context);
        exceptionMap.put("versionCode", String.valueOf(versionCode));
        exceptionMap.put("versionName",appVersionName);
        //通过反射拿到错误信息

    }

    /**
     * 保存错误信息到文件中
     * @param throwable
     */
    private void saveCrashInfoToFile(Throwable throwable) {
        if (AppUtil.isHasSDCard()) {
            try{
                String path = AppUtil.getSDCardPath() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String currentTime = DateUtil.currentDatetime();
                String fileName = "crash-" + currentTime + ".log";
                File file = new File(path + "/" + fileName);
                if(!file.exists()){
                    file.createNewFile();
                }
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                printWriter.print(currentTime);
                printWriter.println();
                for(Map.Entry<String, String> entry : exceptionMap.entrySet()){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    printWriter.print(key + "=" + value + "\n");
                }
                printWriter.println();
                //将异常的跟踪栈信息输出到指定的输出流
                throwable.printStackTrace(printWriter);
                MyLogUtil.e("throwable************:"+throwable.toString());
                printWriter.close();
            }catch (Exception e){
                MyLogUtil.d("saveCrashInfoToFile fail...");
            }
        }else{
            MyLogUtil.d("SD卡不存在");
        }
    }

}
