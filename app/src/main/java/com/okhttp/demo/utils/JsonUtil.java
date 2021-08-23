package com.okhttp.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.okhttp.demo.models.BaseData;

public class JsonUtil {
    public static BaseData<Object> parseResultModel(String jsonStr) {
        TypeReference<BaseData<Object>> typeRef = new TypeReference<BaseData<Object>>() {
        };
        try {
            return JSON.parseObject(jsonStr, typeRef);
        } catch (Exception e) {

            return null;
        }
    }

    public static <T> BaseData<T> parseResultModel(String jsonStr, TypeReference<BaseData<T>> typeReference) throws Exception {

        String parseJson = replaceJson(jsonStr);
        LogUtility.d("parseJson", parseJson);

        try {
            return JSONObject.parseObject(parseJson, typeReference);
        } catch (JSONException e) {
            LogUtility.e("jsonStr", e.toString());
            BaseData<Object> retryResult = parseResultModel(parseJson);
            if (retryResult != null) {
                BaseData<T> result = new BaseData<>();
                result.setCode(retryResult.getCode());
                result.setMessage(retryResult.getMessage());
                result.setRedirect(retryResult.getRedirect());
                result.setValue(null);
                return result;
            } else {
                throw new Exception("json parse error");
            }
        }
    }

    private static String replaceJson(String oldJson) {
        //return oldJson.replace("\\", "").replace("\"[", "[").replace("]\"", "]");
        return oldJson.replace("\\\"", "\"").replace("\"[", "[").replace("]\"", "]");
    }

}
