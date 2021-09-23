//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Method;

public class FmDeviceHelper {
    public FmDeviceHelper() {
    }

    public static String imei(Context context) {
        String imei = null;
        String var2 = "getDeviceId";

        String MZ_T_M;
        try {
            MZ_T_M = "android.telephony.MzTelephonyManager";
            imei = (String)invokeStatic("android.telephony.MzTelephonyManager", "getDeviceId", (Class[])null, (Object[])null);
        } catch (Exception var5) {
        }

        if (TextUtils.isEmpty(imei)) {
            try {
                MZ_T_M = "com.meizu.telephony.MzTelephonymanager";
                imei = (String)invokeStatic("com.meizu.telephony.MzTelephonymanager", "getDeviceId", new Class[]{Context.class, Integer.TYPE}, new Object[]{context, 0});
            } catch (Exception var4) {
            }
        }

        return imei;
    }

    private static Object invokeStatic(String className, String methodName, Class<?>[] paramsTypes, Object[] params) throws Exception {
        Class<?> objClass = Class.forName(className);
        Method method;
        if (params != null && params.length != 0) {
            method = objClass.getMethod(methodName, paramsTypes);
            method.setAccessible(true);
            return method.invoke(objClass, params);
        } else {
            method = objClass.getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(objClass);
        }
    }
}
