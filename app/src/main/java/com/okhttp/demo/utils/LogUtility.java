package com.okhttp.demo.utils;

import androidx.annotation.NonNull;
import android.util.Log;

import com.okhttp.demo.BuildConfig;

import java.util.HashMap;
import java.util.Locale;

public class LogUtility {

    private static String APP_TAG = "Saas##QuickSDK";
    /**
     * 埋点
     */
    public static boolean IS_SHOW_USAGE_STATISTICS = false;
    /**
     * 服务端接口下发
     */
    public static boolean IS_SHOW_SERVICE_RESULT = BuildConfig.DEBUG;
    public static boolean isDebug = BuildConfig.DEBUG;
    private static HashMap<String, String> sCachedTag = new HashMap<>();

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        } else if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        } else if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        } else if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        } else if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * 约定，只有error等级的log全部打出，请注意严谨使用
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /**
     * create by wja
     * 打印详细堆栈信息
     * @param tag
     * @param message
     */
    public static void dd(String tag, String message) {
        if (isDebug) {
            Log.d(buildTag(tag), buildMessage(message));
        }
    }

    /**
     * 打印埋点
     * @param tag
     * @param message
     */
    public static void uu(String tag, String message) {
        if (IS_SHOW_USAGE_STATISTICS) {
            Log.e(buildTag(tag), buildMessage(message));
        }
    }

    /**
     * 打印服务端接口
     * @param tag
     * @param message
     */
    public static void ss(String tag, String message) {
        if (IS_SHOW_SERVICE_RESULT) {
            if (isDebug) {
                Log.e(buildTag(tag), buildMessage(message));
            }
        }
    }

    public static void ee(String tag, String message) {
        if (isDebug) {
            Log.e(buildTag(tag), buildMessage(message));
        }
    }

    private static String buildTag(@NonNull String tag) {
        String key = String.format(Locale.US, "%s@%s", tag, Thread.currentThread().getName());

        if (!sCachedTag.containsKey(key)) {
            if (APP_TAG.equals(tag)) {
                sCachedTag.put(key, String.format(Locale.US, "|%s|%s|",
                        tag,
                        Thread.currentThread().getName()
                ));
            } else {
                sCachedTag.put(key, String.format(Locale.US, "|%s_%s|%s|",
                        APP_TAG,
                        tag,
                        Thread.currentThread().getName()
                ));
            }
        }

        return sCachedTag.get(key);
    }

    private static String buildMessage(String message) {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();

        if (traceElements == null || traceElements.length < 4) {
            return message;
        }
        StackTraceElement traceElement = traceElements[4];

        return String.format(Locale.US, "%s.%s(%s:%d) %s",
                traceElement.getClassName().substring(traceElement.getClassName().lastIndexOf(".") + 1),
                traceElement.getMethodName(),
                traceElement.getFileName(),
                traceElement.getLineNumber(),
                message
        );
    }

}
