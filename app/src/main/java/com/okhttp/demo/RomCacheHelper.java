package com.okhttp.demo;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.okhttp.demo.utils.LogUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 序列化缓存bean
 */
public class RomCacheHelper {

    private static final String TAG = "RomCacheHelper";

    public static <T> boolean saveObject(String fileName, T ser) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = MyApplication.getInstance().openFileOutput(md5(fileName), Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T readObject(String fileName) {
        if (!isExistDataCache(md5(fileName))) {
            return null;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = MyApplication.getInstance().openFileInput(md5(fileName));
            ois = new ObjectInputStream(fis);
            return (T) ois.readObject();
        } catch (FileNotFoundException e) {
            LogUtility.d(TAG, e.toString());

        } catch (Exception e) {
            LogUtility.d(TAG, e.toString());
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = MyApplication.getInstance().getFileStreamPath(md5(fileName));
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    private static boolean isExistDataCache(String fileName) {
        String path = MyApplication.getInstance().getFilesDir().getPath() + "//";
        File file = new File(path + fileName);
        if (file.exists()) {
            return true;
        }
        LogUtility.d(TAG, "文件不存在！！！");

        return false;
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    private static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取文件存储路径
     *
     * @return
     */
    private static String getDiskCacheDir() {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = MyApplication.getInstance().getExternalCacheDir().getPath();
        } else {
            cachePath = MyApplication.getInstance().getCacheDir().getPath();
        }
        return cachePath;
    }


}
