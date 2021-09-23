//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fm.sdk.deviceid;

import android.util.AndroidRuntimeException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Util() {
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i], true));
        }

        return resultSb.toString();
    }

    public static String byteArrayToHexString(byte[] b, int beginPos, int length) {
        StringBuilder resultSb = new StringBuilder();

        for(int i = beginPos; i < length + beginPos; ++i) {
            resultSb.append(byteToHexString(b[i], true));
        }

        return resultSb.toString();
    }

    public static String byteArrayToHexStringLittleEnding(byte[] b) {
        StringBuilder resultSb = new StringBuilder();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i], false));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b, boolean bigEnding) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return bigEnding ? hexDigits[d1] + hexDigits[d2] : hexDigits[d2] + hexDigits[d1];
    }

    public static String MD5Encode(String origin) {
        return MD5Encode(origin, (String)null);
    }

    public static byte[] hexStringToByteArray(String s) {
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException("Error Hex String length");
        } else {
            byte[] result = new byte[s.length() / 2];

            int bytepos;
            char c;
            char c2;
            for(int i = 0; i < s.length(); result[bytepos] = Integer.decode("0x" + c + c2).byteValue()) {
                bytepos = i / 2;
                c = s.charAt(i++);
                c2 = s.charAt(i++);
            }

            return result;
        }
    }

    public static String MD5Encode(String origin, String encoding) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (encoding == null) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(encoding)));
            }

            return resultString;
        } catch (Exception var4) {
            throw new AndroidRuntimeException(var4);
        }
    }

    public static byte[] MD5Encode(byte[] origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(origin);
        } catch (Exception var2) {
            throw new AndroidRuntimeException(var2);
        }
    }

    public static String toMd5(byte[] bytes, boolean upperCase) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(bytes);
            return toHexString(algorithm.digest(), "", upperCase);
        } catch (NoSuchAlgorithmException var3) {
            throw new AndroidRuntimeException(var3);
        }
    }

    private static String toHexString(byte[] bytes, String separator, boolean upperCase) {
        StringBuilder hexString = new StringBuilder();
        byte[] var4 = bytes;
        int var5 = bytes.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte b = var4[var6];
            String str = Integer.toHexString(255 & b);
            if (upperCase) {
                str = str.toUpperCase();
            }

            if (str.length() == 1) {
                hexString.append("0");
            }

            hexString.append(str).append(separator);
        }

        return hexString.toString();
    }
}
