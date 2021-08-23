package com.okhttp.demo.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ParamSignUtil {
    private final static String TAG = "ParamSignUtil";
    /**
     * 获取HmacSHA256加密内容
     *
     * @param param
     * @param accessKeySecret
     * @return
     */
    public static String getHmacSHA256ParamSign(Map<String, String> param, String accessKeySecret) {
        String stringToSign = sortParam(param);
        String result = null;
        try {
            result = sign(stringToSign, accessKeySecret);
        } catch (Exception e) {
            Log.e(TAG, "ParamSignUtil getParamSign failed!", e);
        }
        return result;
    }

    /**
     * 获取HmacSHA256加密内容
     *
     * @param param
     * @param accessKeySecret
     * @return
     */
    public static String getHmacSHA256ParamSignObject(Map<String, Object> param, String accessKeySecret) {
        String stringToSign = sortParamObject(param);
        String result = null;
        try {
            result = sign(stringToSign, accessKeySecret);
        } catch (Exception e) {
            Log.e(TAG, "ParamSignUtil getParamSign failed!", e);
        }
        return result;
    }

    /**
     * 排列字符串并且排序
     *
     * @param param
     * @return
     */
    private static String sortParam(Map<String, String> param) {
        List<String> keysList = new ArrayList<String>(param.keySet());
        Collections.sort(keysList);
        StringBuilder builder = new StringBuilder();
        Iterator<String> itor = keysList.iterator();
        boolean first = true;
        while (itor.hasNext()) {
            String key = itor.next();
            Object object = param.get(key);

            if (first && object != null) {
                first = false;
            } else if (!first && object != null) {
                builder.append("&");
            }

            if (object != null) {
                String value = object.toString();
                builder.append(key).append("=").append(value);
            }
        }
        return builder.toString();
    }

    /**
     * 排列字符串并且排序
     *
     * @param param
     * @return
     */
    private static String sortParamObject(Map<String, Object> param) {
        List<String> keysList = new ArrayList<String>(param.keySet());
        Collections.sort(keysList);
        StringBuilder builder = new StringBuilder();
        Iterator<String> itor = keysList.iterator();
        boolean first = true;
        while (itor.hasNext()) {
            String key = itor.next();
            Object object = param.get(key);

            if (first && object != null) {
                first = false;
            } else if (!first && object != null) {
                builder.append("&");
            }

            if (object != null) {
                String value = object.toString();
                builder.append(key).append("=").append(value);
            }
        }
        return builder.toString();
    }

    /**
     * 用hmac sha256签名
     */
    private static String sign(String stringToSign, String accessKeySecret) {
        String ENCODING = "UTF-8";
        String ALGORITHM = "HmacSHA256";
        Mac mac = null;
        byte[] signData = null;
        try {
            mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(accessKeySecret.getBytes(ENCODING), ALGORITHM));
            signData = mac.doFinal(stringToSign.getBytes(ENCODING));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        if(signData == null) {
            return null;
        }
        String signature = byteArr2HexStr(signData);
        return signature;
    }

    /**
     * 每一位转成16进制
     */
    private static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }
}