package com.okhttp.demo.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class DeviceUtil {

    private static String SNID;
    private static String DEVICEID;
    private static String sHostPackageName;
    private static final String DEFAULT_IMEI = "1234567890ABCDEF";
    private static final String TAG = "DeviceUtil";

    //Note : Modfif the sdk version when version changed.
    public static String SDK_VERSION = "1.0.0";
    public static String phoneNum1 = "";
    public static String phoneNum2 = "";
    public static boolean isDoubleNumber = false;

    public static final String TYPE_NONE = "NONE";
    public static final String TYPE_UNKNOW = "UNKNOW";
    public static final String TYPE_2G = "2G";
    public static final String TYPE_3G = "3G";
    public static final String TYPE_4G = "4G";
    public static final String TYPE_WIFI = "WIFI";

    /**
     * 获取系统渠道,区分魅族和展锐
     *
     * @return
     */
    public static String getSystemBrand() {
        return (String) ReflectionUtility.reflectMethod(
                "android.os.SystemProperties",
                "get",
                new Class[]{String.class, String.class},
                new Object[]{"ro.product.other.brand", "unknown"}
        );
    }

    /**
     * 获取设备imei。
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        if (!TextUtils.isEmpty(DEVICEID)) {
            return DEVICEID;
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                DEVICEID = telephonyManager.getDeviceId();
            } catch (Throwable e) {
                LogUtility.ee(TAG, "can not get imei " + e.getMessage());
            }
        }

        try {
            if (TextUtils.isEmpty(DEVICEID)) {
                DEVICEID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        } catch (Throwable e) {
            LogUtility.ee(TAG, "getDeviceId error " + e.getMessage());
        }

        if (TextUtils.isEmpty(DEVICEID)) {
            DEVICEID = DEFAULT_IMEI;
        }

        return DEVICEID;
    }


    /**
     * 获取设备sn。
     *
     * @return
     */
    public static String getSN(Context context) {

        if (!TextUtils.isEmpty(SNID)) {
            return SNID;
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    return Build.getSerial();
                } catch (Exception e) {
                    LogUtility.ee(TAG, "getSN error " + e.toString());
                }
            } else {
                SNID = Build.SERIAL;
            }
        }

        try {
            if (TextUtils.isEmpty(SNID)) {
                SNID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        } catch (Throwable e) {
            LogUtility.ee(TAG, "getSn error " + e.getMessage());
        }

        if (TextUtils.isEmpty(SNID)) {
            SNID = Build.SERIAL;
        }

        return SNID;
    }


    /**
     * 获取设备model。
     *
     * @return
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }


    /**
     * 获取宿主包名。
     *
     * @param context
     * @return
     */
    public static String getHostPackageName(Context context) {
        if (!TextUtils.isEmpty(sHostPackageName)) {
            return sHostPackageName;
        }
        if (context == null) {
            return "";
        }
        ApplicationInfo info = context.getApplicationInfo();
        if (info != null) {
            return info.packageName;
        } else {
            return "";
        }
    }

    public static String getNetworkStatus(Context context) {
        String networkStatus;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            networkStatus = TYPE_NONE;
        } else {
            int networkType = networkInfo.getType();
            if (networkType == ConnectivityManager.TYPE_MOBILE) {
                networkStatus = getMobileType(networkInfo);
            } else if (networkType == ConnectivityManager.TYPE_WIFI) {
                networkStatus = TYPE_WIFI;
            } else {
                Log.e(TAG, "Unknown network type: " + networkType);
                networkStatus = TYPE_UNKNOW;
            }
        }
        return networkStatus;
    }

    private static String getMobileType(NetworkInfo networkInfo) {
        switch (networkInfo.getSubtype()) {
            //如果是2g类型
            case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
            case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
            case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return TYPE_2G;
            //如果是3g类型
            case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return TYPE_3G;
            //如果是4g类型
            case TelephonyManager.NETWORK_TYPE_LTE:
                return TYPE_4G;
            default:
                String subTypeName = networkInfo.getSubtypeName();
                //中国移动 联通 电信 三种3G制式
                if (subTypeName.equalsIgnoreCase("TD-SCDMA")
                        || subTypeName.equalsIgnoreCase("WCDMA")
                        || subTypeName.equalsIgnoreCase("CDMA2000")) {
                    return TYPE_3G;
                } else {
                    Log.e(TAG,
                            "Unknown network type" +
                                    ", subType: " + networkInfo.getSubtype() +
                                    ", subTypeName: " + subTypeName);
                    return TYPE_2G;
                }
        }
    }

    public static String getFlymeVersion() {
        return Build.DISPLAY;
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getBrand() {
        return Build.BRAND;
    }
}