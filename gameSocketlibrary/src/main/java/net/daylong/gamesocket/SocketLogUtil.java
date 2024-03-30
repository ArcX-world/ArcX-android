package net.daylong.gamesocket;

import android.util.Log;


public class SocketLogUtil {
    private static boolean sDebug = true;
    private static final String  TAG = "webSocket->";

    /**
     * 打印日志(Verbose)
     *
     * @param msg 内容
     */
    public static void v(String msg) {
        if (sDebug) {
            Log.v(TAG,msg);
        }
    }

    /**
     * 打印日志(Debug)
     *
     * @param msg 内容
     */
    public static void d(String msg) {
        if (sDebug) {
            Log.d(TAG,msg);
        }
    }


    /**
     * 打印日志(Info)
     *
     * @param msg 内容
     */
    public static void i(String msg) {
        if (sDebug) {
            Log.i(TAG,msg);
        }
    }

    /**
     * 打印日志(Warm)
     *
     * @param msg 内容
     */
    public static void w(String msg) {
        if (sDebug) {
            Log.w(TAG,msg);
        }
    }



    /**
     * 打印日志(Error)
     *
     * @param msg 内容
     */
    public static void e(String msg) {
        if (sDebug) {
            Log.e(TAG,msg);

        }
    }
}
