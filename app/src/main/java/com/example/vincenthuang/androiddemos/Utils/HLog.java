package com.example.vincenthuang.androiddemos.Utils;

import android.util.Log;

import java.util.Formatter;
import java.util.Locale;

/**
 * log类的工具类
 * 仿照Android自带的Log.v(tag, String). 只是这里使用了Formatter进行格式化. 而且进行了判断是否需要执行
 * 便于以后提高效率
 * Created by Vincent Huang on 2017/2/2.
 */
public class HLog {
    private static final boolean isVerboseLoggable = true;
    private static final boolean isDebugLoggable = true;
    private static final boolean isInfoLoggable = true;
    private static final boolean isErrorLoggable = true;


    public static void v(String tag, String format, Object... args){
        if(!isVerboseLoggable) return;
        Log.v(tag, formatString(format, args));
    }

    public static void i(String tag, String format, Object... args){
        if(!isInfoLoggable) return;
        Log.i(tag, formatString(format, args));
    }

    public static void d(String tag, String format, Object... args){
        if(!isDebugLoggable) return;
        Log.d(tag, formatString(format, args));
    }

    public static void e(String tag, String format, Object... args){
        if(!isErrorLoggable) return;
        Log.e(tag, formatString(format, args));
    }

    public static void e(String tag, String errorMessage, Exception e){
        Log.e(tag, errorMessage, e);
    }

    private static String formatString(String format, Object... args){
        return new Formatter(Locale.getDefault()).format(format, args).toString();
    }
}
