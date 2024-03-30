package net.daylong.baselibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.view.View;

import net.daylong.baselibrary.app.BaseApplication;
import net.daylong.baselibrary.utils.sys.AppUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Utils {

    /**
     * dp转px
     *
     * @param value dp值
     * @return 转换后的px
     */
    public static int dp2px(float value) {

        final float scale = BaseApplication.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context 上下文环境
     * @param value   px值
     * @return 转换后的dp
     */
    public static int px2dp(Context context, int value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value / scale + 0.5f);
    }

    /**
     * 通过 fraction 计算值
     *
     * @param fraction
     * @param startValue 开始坐标
     * @param endValue   结束坐标
     * @return
     */
    public static Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (startInt + fraction * (endValue - startInt));
    }

    /**
     * float 转成String 保留2位小数
     *
     * @param f
     */
    public static String floatToString2(float f) {
        DecimalFormat fnum = new DecimalFormat("##0.000");
        String format = fnum.format(f);
        return format.substring(0, format.length() - 1);

    }


    /**
     * float 转成String 保留1位小数
     *
     * @param f
     */
    public static String floatToString1(float f) {
        DecimalFormat fnum = new DecimalFormat("##0.0");
        return fnum.format(f);

    }

    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return true 验证通过 false 验证失败
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    /**
     * 处于1千保留2位小数
     *
     * @param price
     * @return
     */
    public static String for2Pro(double price) {

        DecimalFormat df = new DecimalFormat("0.00");
        double newPrice = price / 10000;
        return df.format(newPrice);

    }


    /**
     * 区分数字
     *
     * @param number
     * @return
     */
    public static String getDistinguishNumber(int number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }
    /**
     * 区分数字
     *
     * @param number
     * @return
     */
    public static String getDistinguishNumberDot(int number) {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(number);
    }

    /**
     * 区分数字
     *
     * @param number
     * @return
     */
    public static String getDistinguishNumber(double number) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(number);
    }

    /**
     * 区分数字
     *
     * @param number
     * @return
     */
    public static String getDistinguishNumbers(double number) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(number);
    }

    /**
     * 区分数字
     *
     * @param number
     * @return
     */
    public static String getDistinguishNumber(String number) {
//        DecimalFormat df = new DecimalFormat("#,###");
        return number;
    }


    /**
     * 返回当前的进程名
     */

    public static String getCurrentProcessName() {
        FileInputStream in = null;

        try {
            String fn = "/proc/self/cmdline";

            in = new FileInputStream(fn);

            byte[] buffer = new byte[256];

            int len = 0;

            int b;

            while ((b = in.read()) > 0 && len < buffer.length) {
                buffer[len++] = (byte) b;

            }

            if (len > 0) {
                String s = new String(buffer, 0, len, "UTF-8");

                return s;

            }

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            if (in != null) {
                try {
                    in.close();

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

        }

        return null;

    }


    /**
     * 获取位置
     *
     * @param view
     * @return
     */
    public static float[] setRect(View view) {

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        float left = location[0];
        float right = left + view.getRight();
        float top = location[1];
        float bottom = top + view.getBottom();
        return new float[]{left, top, right, bottom};


    }

    //读取方法
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    public static int getDrawable(String drawableName) {
        return AppUtil.getContext().getResources().getIdentifier(drawableName, "drawable", BaseApplication.getAppContext().getPackageName());

    }
    public static boolean copyMsg(String msg) {
        ClipboardManager clipboard = (ClipboardManager) AppUtil.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null || msg == null) {
            return false;
        }
        if (TextUtils.isEmpty(msg)) {
            return false;
        }

        ClipData clip = ClipData.newPlainText("message", msg);
        clipboard.setPrimaryClip(clip);

        return true;
    }
}
