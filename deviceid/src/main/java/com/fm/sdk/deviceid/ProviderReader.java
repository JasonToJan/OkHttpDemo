//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ProviderReader {
    private static final String AUTHORITIES = "com.fm.openservice";
    private static final String PROPERTY_PATH = "property";
    private static final Uri BASE_URI = Uri.parse("content://com.fm.openservice");
    private static final Uri PROPERTY_URI;
    private static final String COLUMN_OAID = "oaid";
    private static boolean invoked;

    public ProviderReader() {
    }

    public static String getOAID(Context context) {
        if (invoked) {
            return "";
        } else {
            String result = "";

            try {
                Cursor cursor = context.getContentResolver().query(PROPERTY_URI, (String[])null, (String)null, (String[])null, (String)null);
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex("oaid"));
                    String var3 = result;
                    return var3;
                }
            } catch (Exception var7) {
            } finally {
                invoked = true;
            }

            return result;
        }
    }

    static {
        PROPERTY_URI = Uri.withAppendedPath(BASE_URI, "property");
        invoked = false;
    }
}
