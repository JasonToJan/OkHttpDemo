package com.okhttp.demo.net.okhttp;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.okhttp.demo.models.BaseData;
import com.okhttp.demo.utils.JsonUtil;
import com.okhttp.demo.utils.LogUtility;
import com.okhttp.demo.utils.RomCacheHelper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CardRequest<T> {

    private static final int ERROR_RANGE = 10000;

    private static final String TAG = "CardRequest";

    private Call mCall;
    private TypeReference<BaseData<T>> mTypeReference;
    private String mCacheName;
    private RequestListener<T> mRequestListener;
    private Class<?> classType;

    public CardRequest(Call call, TypeReference<BaseData<T>> typeReference, String cacheName, Class<?> classType, RequestListener<T> listener) {
        mCall = call;
        mTypeReference = typeReference;
        mCacheName = cacheName;
        mRequestListener = listener;
        this.classType = classType;
    }

    public void enqueue() {
        if (mCall != null) {
            mCall.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (mRequestListener != null) {
                        mRequestListener.onFail(e.toString());
                    }
                    LogUtility.ee(TAG, "网络请求失败:" + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        if (response.isSuccessful()) {
                            T model = handlerNetSuccess(response, mTypeReference, classType, mCacheName);
                            if (mRequestListener != null) {
                                mRequestListener.onSuccess(model, response.toString()+" \n");
                            }
                        } else {
                            loadFromCache(mCacheName, classType, "网络请求异常:" + response.toString());
                        }
                    } catch (Exception e) {
                        loadFromCache(mCacheName, classType, "网络解析异常:" + e.toString());
                    }
                }
            });
        }
    }

    private T handlerNetSuccess(Response response, TypeReference<BaseData<T>> typeReference, Class<?> classType, String cacheName) throws Exception {
        if (response != null && response.body() != null) {
            String jsonResponse = response.body().string();
            BaseData<T> baseData = getBaseData(jsonResponse, typeReference);
            T data = baseData.getValue();
            if (data == null) {
                return null;
            }
            if (cacheName != null) {
                RomCacheHelper.saveObject(cacheName, data);
            }
            return data;
        } else {
            throw new Exception("response or response.body() can not be null");
        }
    }

    private void loadFromCache(String cacheName, Class<?> classType, String errorMsg) {
        T cacheData = null;
        if (!TextUtils.isEmpty(cacheName)) {
            cacheData = RomCacheHelper.readObject(cacheName);
        }

        if (cacheData != null) {
            if (mRequestListener != null) {
                mRequestListener.onSuccess(cacheData, "缓存数据");
            }
        } else {
            if (mRequestListener != null) {
                mRequestListener.onFail("cache data is null:" + errorMsg);
            }
        }
    }

    private BaseData<T> getBaseData(String jsonStr, TypeReference<BaseData<T>> typeReference) throws Exception {
        Log.i(TAG, "onSuccess:" + jsonStr);
        BaseData<T> result = JsonUtil.parseResultModel(jsonStr, typeReference);
        if (result.getCode() >= ERROR_RANGE) {
            throw new Exception(result.getCode() + "");
        }
        return result;
    }

    public interface RequestListener<T> {

        void onSuccess(T model, String result);

        void onFail(String msg);
    }

}
