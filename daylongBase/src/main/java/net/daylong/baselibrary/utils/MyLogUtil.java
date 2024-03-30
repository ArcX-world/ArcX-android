package net.daylong.baselibrary.utils;

import android.util.Base64;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import net.daylong.baselibrary.app.Constant;
import net.daylong.baselibrary.utils.date.DateUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MyLogUtil {
    private static boolean sDebug = true;

    /**
     * 初始化
     *
     * @param debug
     */
    public static void init(boolean debug) {


        sDebug = debug;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 打印日志(Verbose)
     *
     * @param msg 内容
     */
    public static void v(String msg) {
        if (sDebug) {
            Logger.v(msg);
        }
    }

    /**
     * 打印日志(Debug)
     *
     * @param msg 内容
     */
    public static void d(String msg) {
        if (sDebug) {
            Logger.e(msg);
        }
    }


    /**
     * 打印日志(Info)
     *
     * @param msg 内容
     */
    public static void i(String msg) {
        if (sDebug) {
            Logger.i(msg);
        }
    }

    /**
     * 打印日志(Warm)
     *
     * @param msg 内容
     */
    public static void w(String msg) {
        if (sDebug) {
            Logger.w(msg);
        }
    }

    /**
     * 打印日志(wtf)
     *
     * @param msg 内容
     */
    public static void wtf(String msg) {
        if (sDebug) {
            Logger.wtf(msg);
        }
    }


    /**
     * 打印日志(Error)
     *
     * @param msg 内容
     */
    public static void e(String msg) {
        if (sDebug) {
            Logger.e(msg);
        }
    }

    /**
     * 打印日志(Error)
     *
     * @param msg 内容
     */
    public static void e(String msg, String text) {
        if (sDebug) {
            Logger.e(msg + text);
        }
    }

    /**
     * 打印日志(Error)
     *
     * @param msg 内容
     */
    public static void i(String msg, String text) {
        if (sDebug) {
            Logger.i(msg + text);
        }
    }

    /**
     * 打印日志(Error)
     *
     * @param throwable
     */
    public static void e(Throwable throwable) {
        if (sDebug) {
            Logger.e(throwable, "");
        }
    }


    /**
     * 打印日志(Erro)
     *
     * @param msg       内容
     * @param throwable
     */
    public static void e(String msg, Throwable throwable) {
        if (sDebug) {
            Logger.e(throwable, msg);
        }
    }

    /**
     * 打印日志(json)
     *
     * @param msg 内容
     */
    public static void json(String msg) {
        if (sDebug) {
            Logger.json(msg);
        }
    }


    /**
     * 追加文件：使用FileWriter
     *
     * @param content
     */
    public static void writeLog(String content) {
        if (sDebug) {
            content = DateUtil.currentDatetimeLong() + ":" + content;
//            String baseContent = encodeToString();
            String path = Constant.LOG_PATH;
            FileWriter writer = null;
            try {
                File file = new File(path);
                if (!file.exists()) {  //没有创建文件夹则创建
                    file.mkdirs();
                }
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(path + DateUtil.currentDatetimeDay() + ".log", true);
                writer.write(content + "\r\n");
                writer.flush();
                //关闭流
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void writeLogAndName(String fileName, String content) {
        if (sDebug) {
            content = DateUtil.currentDatetimeLong() + ":" + content;
//            String baseContent = encodeToString();
            String path = Constant.LOG_PATH;
            FileWriter writer = null;
            try {
                File file = new File(path);
                if (!file.exists()) {  //没有创建文件夹则创建
                    file.mkdirs();
                }
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(path + DateUtil.currentDatetimeDay() + fileName + ".log", true);
                writer.write(content + "\r\n");
                writer.flush();
                //关闭流
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeLog(String name, String content) {
        if (sDebug) {
            content = DateUtil.currentDatetimeLong() + ":" + content;
//            String baseContent = encodeToString();
            String path = Constant.LOG_PATH;
            FileWriter writer = null;
            try {
                File file = new File(path);
                if (!file.exists()) {  //没有创建文件夹则创建
                    file.mkdirs();
                }
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(path + DateUtil.currentDatetimeDay() + name + ".log", true);
                writer.write(content + "\r\n");
                writer.flush();
                //关闭流
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String encodeToString(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
