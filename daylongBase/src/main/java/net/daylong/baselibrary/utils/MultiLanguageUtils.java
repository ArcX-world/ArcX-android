package net.daylong.baselibrary.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;

import java.util.Locale;

public class MultiLanguageUtils {

    /**
     * 修改应用内语言设置
     *
     * @param language 语言
     * @param area     地区
     */
    public static void changeLanguage(Context context, String language, String area) {

        //不为空，修改app语言，持久化语言选项信息
        Locale newLocale = new Locale(language, area);
        setAppLanguage(context, newLocale);
    }


    /**
     * 更新应用语言（核心）
     *
     * @param context
     * @param locale
     */
    private static void setAppLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics metrics = resources.getDisplayMetrics();
            Configuration configuration = resources.getConfiguration();
            //Android 7.0以上的方法
            configuration.setLocale(locale);
            configuration.setLocales(new LocaleList(locale));
            context.createConfigurationContext(configuration);
            //实测，updateConfiguration这个方法虽然很多博主说是版本不适用
            //但是我的生产环境androidX+Android Q环境下，必须加上这一句，才可以通过重启App来切换语言
            resources.updateConfiguration(configuration, metrics);



        }
    }


    /**
     * 获取应用语言
     */
    public static Locale getAppLocale(Context context) {
        Locale local;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            local = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            local = context.getResources().getConfiguration().locale;
        }
        return local;
    }


    /**
     * 获取系统语言
     */
    public static LocaleListCompat getSystemLanguage() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        LocaleListCompat locales = ConfigurationCompat.getLocales(configuration);
        return locales;
    }

    /**
     * 设置语言信息
     * <p>
     * 说明：
     * 该方法建议在attachBaseContext和onConfigurationChange中调用，attachBaseContext可以保证页面加载时修改语言信息，
     * 而onConfigurationChange则是为了对应横竖屏切换时系统更新Resource的问题
     *
     * @param context application context
     */
    public static void setConfiguration(Context context) {
        if (context == null) {
            return;
        }
        /*
         * 为防止传入非ApplicationContext，这里做一次强制转化，目的是避免onConfigurationChange可能导致的问题，
         * 因为onConfigurationChange被触发时系统会更新ApplicationContext中的Resource，如果页面包含Runtime资源
         * （运行时动态加载的资源）时，有可能语言显示不一致。
         */
        Context appContext = context.getApplicationContext();
        Locale preferredLocale = getSysPreferredLocale();
        Configuration configuration = appContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(preferredLocale);
        } else {
            configuration.locale = preferredLocale;
        }
        // 更新context中的语言设置
        Resources resources = appContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }

    /**
     * 获取系统首选语言
     * <p>
     * 注意：该方法获取的是用户实际设置的不经API调整的系统首选语言
     *
     * @return
     */
    public static Locale getSysPreferredLocale() {
        Locale locale;
        //7.0以下直接获取系统默认语言
        if (Build.VERSION.SDK_INT < 24) {
            // 等同于context.getResources().getConfiguration().locale;
            locale = Locale.getDefault();
            // 7.0以上获取系统首选语言
        } else {
            /*
             * 以下两种方法等价，都是获取经API调整过的系统语言列表（可能与用户实际设置的不同）
             * 1.context.getResources().getConfiguration().getLocales()
             * 2.LocaleList.getAdjustedDefault()
             */
            // 获取用户实际设置的语言列表
            locale = LocaleList.getDefault().get(0);
        }
        return locale;
    }



}

