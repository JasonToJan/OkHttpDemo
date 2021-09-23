//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.System;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.UUID;

public class RandomDeviceId {
    private static final String DEVICE_ID = "maid";
    private static final String UUID_SP = "maid_sp";
    private static final String UUID_FILE_NAME = "__maid";
    private static final String UUID_FILE_PATH;

    public RandomDeviceId() {
    }

    public static String getUUID(Context context) {
        String uuid = readUUIDFromDisk(context);
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        } else {
            uuid = UUID.randomUUID().toString().toLowerCase();
            saveUUIDToDisk(context, uuid);
            return uuid;
        }
    }

    public static String getUUIDFromSp(Context context) {
        String uuid = readUUIDFromSp(context);
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        } else {
            uuid = UUID.randomUUID().toString();
            saveUUIDToSp(context, uuid);
            return uuid;
        }
    }

    private static String readUUIDFromSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences("maid_sp", 0);
        String uuid = sp.getString("maid", (String)null);
        return uuid;
    }

    private static void saveUUIDToSp(Context context, String uuid) {
        SharedPreferences sp = context.getSharedPreferences("maid_sp", 0);
        Editor editor = sp.edit();
        editor.putString("maid", uuid);
        editor.apply();
    }

    private static void saveUUIDToDisk(Context context, String uuid) {
        String[] versionSplit = VERSION.RELEASE.split("\\.");
        int deviceVersion = Integer.parseInt(versionSplit[0]);
        if (deviceVersion >= 10) {
            saveToMediaStore(context, uuid);
        } else {
            saveToFile(UUID_FILE_PATH, uuid);
        }

    }

    private static void mkdirs(String dir) {
        File dirF = new File(dir);
        if (!dirF.exists()) {
            dirF.mkdirs();
        }

    }

    private static void saveToFile(String path, String uuid) {
        File file = new File(path);
        FileOutputStream outputStream = null;

        try {
            mkdirs(file.getParent());
            outputStream = new FileOutputStream(file);
            outputStream.write(uuid.getBytes());
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

    }

    private static void saveToMediaStore(Context context, String uuid) {
        ContentValues values = new ContentValues();
        values.put("_display_name", "__maid");
        values.put("mime_type", "image/*");
        values.put("is_pending", 1);
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = contentResolver.insert(Media.EXTERNAL_CONTENT_URI, values);

        try {
            FileOutputStream outputStream = null;
            ParcelFileDescriptor fielDescriptor = contentResolver.openFileDescriptor(uri, "w", (CancellationSignal)null);
            outputStream = new FileOutputStream(fielDescriptor.getFileDescriptor());

            try {
                outputStream.write(uuid.getBytes());
            } catch (IOException var17) {
                var17.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException var16) {
                        var16.printStackTrace();
                    }
                }

            }

            values.clear();
            values.put("is_pending", 0);
            contentResolver.update(uri, values, (String)null, (String[])null);
        } catch (FileNotFoundException var19) {
            var19.printStackTrace();
        }

    }

    private static String readUUIDFromDisk(Context context) {
        String[] versionSplit = VERSION.RELEASE.split("\\.");
        int deviceVersion = Integer.parseInt(versionSplit[0]);
        return deviceVersion >= 10 ? readFromMediaStore(context) : readFromFile(UUID_FILE_PATH);
    }

    private static String readFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream inputStream = null;
            String result = null;

            try {
                mkdirs(file.getParent());
                inputStream = new FileInputStream(file);
                result = inputStreamToString(inputStream);
            } catch (Exception var13) {
                var13.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException var12) {
                        var12.printStackTrace();
                    }
                }

            }

            return result;
        }
    }

    private static String readFromMediaStore(Context context) {
        String[] projection = new String[]{"_display_name", "_id"};
        ContentResolver contentResolver = context.getContentResolver();
        String selection = "_display_name=?";
        Cursor mCursor = contentResolver.query(Media.EXTERNAL_CONTENT_URI, projection, selection, new String[]{"__maid"}, (String)null);
        String result = "";
        if (mCursor != null) {
            while(true) {
                if (mCursor.moveToNext()) {
                    int fileIdIndex = mCursor.getColumnIndex("_id");
                    String thumbPath = Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(String.valueOf(mCursor.getInt(fileIdIndex))).build().toString();
                    Uri fileUri = Uri.parse(thumbPath);
                    FileInputStream inputStream = null;

                    try {
                        ParcelFileDescriptor fielDescriptor = contentResolver.openFileDescriptor(fileUri, "r", (CancellationSignal)null);
                        inputStream = new FileInputStream(fielDescriptor.getFileDescriptor());
                        result = inputStreamToString(inputStream);
                    } catch (FileNotFoundException var11) {
                        var11.printStackTrace();
                    }

                    if (TextUtils.isEmpty(result)) {
                        continue;
                    }
                }

                mCursor.close();
                break;
            }
        }

        return result;
    }

    private static String inputStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");

        String line;
        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return sb.toString();
    }

    public static String getPseudoID(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(Build.BOARD).append("|");
        if (VERSION.SDK_INT >= 21) {
            sb.append(Arrays.deepToString(Build.SUPPORTED_ABIS)).append("|");
        } else {
            sb.append(Build.CPU_ABI).append("|");
        }

        sb.append(Build.DEVICE).append("|");
        sb.append(Build.DISPLAY).append("|");
        sb.append(Build.HOST).append("|");
        sb.append(Build.ID).append("|");
        sb.append(Build.MANUFACTURER).append("|");
        sb.append(VERSION.INCREMENTAL).append("|");
        sb.append(Build.BRAND).append("|");
        sb.append(Build.MODEL).append("|");
        sb.append(Build.PRODUCT).append("|");
        sb.append(Build.BOOTLOADER).append("|");
        sb.append(Build.HARDWARE).append("|");
        sb.append(Build.TAGS).append("|");
        sb.append(Build.TYPE).append("|");
        sb.append(Build.USER).append("|");
        sb.append(Build.FINGERPRINT).append("|");
        String androidId = System.getString(context.getContentResolver(), "android_id");
        sb.append(androidId);
        return MD5Util.MD5Encode(sb.toString(), "UTF-8");
    }

    static {
        UUID_FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + ".mmm" + File.separator + "." + "__maid";
    }
}
