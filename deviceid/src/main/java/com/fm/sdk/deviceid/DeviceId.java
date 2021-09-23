//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.Method;

public class DeviceId {
    private static final String TAG = "DeviceId";
    private static final String DEFAULT_OAID = "00000000000000000000000000000000";
    private static String maid_sp = null;
    private static String imei = null;
    private static String sn = null;
    private static String oaid = null;

    public DeviceId() {
    }

    public static void init(Context context) {
        MiitHelper.getInstance().init(context);
    }

    public static String getOaid(Context context) {
        if (TextUtils.isEmpty(oaid)) {
            oaid = MiitHelper.getInstance().getOaid();
            if (TextUtils.isEmpty(oaid) && "sony".equalsIgnoreCase(Build.BRAND)) {
                oaid = ProviderReader.getOAID(context);
            }
        }

        return TextUtils.equals("00000000000000000000000000000000", oaid) ? null : oaid;
    }

    public static String getVaid(Context context) {
        return MiitHelper.getInstance().getVaid();
    }

    public static String getAaid(Context context) {
        return MiitHelper.getInstance().getAaid();
    }

    @SuppressLint({"NewApi"})
    public static String getMaid(Context context) {
        if (TextUtils.isEmpty(maid_sp)) {
            maid_sp = RandomDeviceId.getPseudoID(context);
        }

        return maid_sp;
    }

    public static String getImei(Context context) {
        if (TextUtils.isEmpty(imei)) {
            imei = FmDeviceHelper.imei(context);
            if (TextUtils.isEmpty(imei)) {
                try {
                    TelephonyManager tm = (TelephonyManager)context.getSystemService("phone");
                    if (VERSION.SDK_INT >= 26) {
                        return tm.getImei();
                    }

                    return tm.getDeviceId();
                } catch (Exception var2) {
                }
            }
        }

        return imei;
    }

    public static String getSn() {
        if (!TextUtils.isEmpty(sn)) {
            return sn;
        } else {
            try {
                if (VERSION.SDK_INT >= 26) {
                    sn = Build.getSerial();
                } else {
                    sn = Build.SERIAL;
                }

                if (TextUtils.isEmpty(sn)) {
                    try {
                        Class<?> c = Class.forName("android.os.SystemProperties");
                        Method get = c.getMethod("get", String.class);
                        sn = (String)get.invoke(c, "ro.serialno");
                    } catch (Exception var2) {
                    }
                }
            } catch (Exception var3) {
            }

            return sn;
        }
    }

    public static String getAndroidId(Context context) {
        return System.getString(context.getContentResolver(), "android_id");
    }
}
