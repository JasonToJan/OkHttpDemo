package com.okhttp.demo.net.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.okhttp.demo.models.BaseData;
import com.okhttp.demo.net.RequestApi;
import com.okhttp.demo.utils.Constants;
import com.okhttp.demo.models.HomeTabBean;
import com.okhttp.demo.utils.DeviceUtil;
import com.okhttp.demo.utils.LogUtility;
import com.okhttp.demo.MyApplication;
import com.okhttp.demo.utils.ParamSignUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.TlsVersion;

/**
 * desc: 请求类
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/8/21 11:20
 * contact: jason1211241203@gmail.com
 **/
public class RequestManager {
    private static final String TAG = "Saas#RequestManager";
    private static volatile RequestManager sInstance;
    private OkHttpClient mOkHttpClient;//okHttpClient 实例
    public static final MediaType _JSON = MediaType.parse("application/json; charset=utf-8");


    private RequestManager() {
        //初始化OkHttpClient
        //fix测试环境 请求接口报javax.net.ssl.SSLHandshakeException
        ConnectionSpec spec1 = new ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT).build();
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();
        //fix Cache为null问题
        File cacheFile = new File(MyApplication.getInstance().getExternalCacheDir(), "cache");
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectionSpecs(Arrays.asList(spec, spec1))
                .connectTimeout(8, TimeUnit.SECONDS)//设置链接超时时间
                .readTimeout(8, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(8, TimeUnit.SECONDS)//设置写入超时时间
                .cache(new Cache(cacheFile, 1024 * 1024 * 10))
                .addInterceptor(new NoNetInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .build();

    }

    public static RequestManager getInstance() {
        if (sInstance == null) {
            synchronized (RequestManager.class) {
                if (sInstance == null) {
                    sInstance = new RequestManager();
                }
            }
        }
        return sInstance;
    }

    public void cancelCall() {
        mOkHttpClient.dispatcher().cancelAll();
    }

    private <T> void handleResult(Call call, String cacheName, Class<?> classType, TypeReference<BaseData<T>> typeReference, final INet<T> callBack) {
        CardRequest<T> cardRequest = new CardRequest<>(call, typeReference, cacheName, classType, new CardRequest.RequestListener<T>() {
            @Override
            public void onSuccess(T model, String result) {
                successCallback(model, result, callBack);
            }

            @Override
            public void onFail(String msg) {
                failureCallback(msg, callBack);
            }
        });
        cardRequest.enqueue();
    }

    private Request.Builder getPostRequest(String url, Map<String, String> params) {
        RequestBody formBody;
        if (params != null) {
            LogUtility.dd(TAG, "url=" + url + " start:" + params);

            FormBody.Builder builder = new FormBody.Builder();
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
            formBody = builder.build();

            return new Request.Builder()
                    .addHeader("Connection", "keep-alive")
                    .url(url)
                    .post(formBody);
        }
        return null;
    }

    private String getJsonParameters(Map map) {
        return JSON.toJSONString(map);
    }

    private <T> void prepareCallback(final INet<T> callBack) {
        ThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onPrepare();
            }
        });
    }

    private <T> void successCallback(final T result, final String message, final INet<T> callBack) {
        ThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(result, message);
            }
        });
    }

    private <T> void failureCallback(final String msg, final INet<T> callBack) {
        ThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onFailure(msg);
                LogUtility.e(TAG, msg);
            }
        });
    }

    //----------------------------------新增接口-----------------------------------------------------
    /**
     * 基础请求体
     * @param parameters
     * @return
     */
    private Map<String, String> getBaseSaasParameters(Map<String, String> parameters) {
        parameters.put(Constants.PARA_SDKVERSION, String.valueOf(1001011));
        parameters.put(Constants.PARA_ANDROID_VERSION, DeviceUtil.getAndroidVersion());
        parameters.put(Constants.PARA_BRAND, DeviceUtil.getBrand());
        parameters.put(Constants.PARA_PRODUCT_TYPE, DeviceUtil.getDeviceModel());
        parameters.put(Constants.PARA_DEVICE_ID, DeviceUtil.getDeviceId(MyApplication.getInstance()));
        parameters.put(Constants.PARA_SN, DeviceUtil.getSN(MyApplication.getInstance()));

        parameters.put(Constants.PARA_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        parameters.put(Constants.PARA_PLATEFORM_VERSION, String.valueOf(1080));
        parameters.put(Constants.PARA_SIGN, ParamSignUtil.getHmacSHA256ParamSign(parameters, Constants.quickAi));
        return parameters;
    }

    /**
     * 请求首页数据
     *
     * @return
     */
    public void requestHomeData(String appId, boolean isShowUserInfo, final INet<HomeTabBean> callBack) {
        try {
            prepareCallback(callBack);
            TypeReference<BaseData<HomeTabBean>> typeReference = new TypeReference<BaseData<HomeTabBean>>() {
            };
            HashMap<String, String> sourceMap = new HashMap<>();
            sourceMap.put(Constants.PARA_APPID, appId);
            sourceMap.put(Constants.PARA_SHOW_USERINFO, String.valueOf((isShowUserInfo ? 1 : 0)));
            Map<String, String> requestMap = getBaseSaasParameters(sourceMap);

            Request.Builder builder = getPostRequest(RequestApi.BASE_SAAS_URL + RequestApi.REQUEST_GAME_CENTER_GET, requestMap);


            Call call = mOkHttpClient.newCall(builder.build());

            handleResult(call, appId, HomeTabBean.class, typeReference, callBack);

        } catch (Exception e) {
            failureCallback(e.toString(), callBack);
        }

    }
}
