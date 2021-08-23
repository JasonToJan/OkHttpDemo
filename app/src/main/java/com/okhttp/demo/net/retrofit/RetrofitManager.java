package com.okhttp.demo.net.retrofit;

import com.okhttp.demo.MyApplication;
import com.okhttp.demo.models.BaseData;
import com.okhttp.demo.models.HomeTabBean;
import com.okhttp.demo.net.RequestApi;
import com.okhttp.demo.utils.Constants;
import com.okhttp.demo.utils.DeviceUtil;
import com.okhttp.demo.utils.ParamSignUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc:使用Retrofit请求
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/8/23 17:12
 * contact: jason1211241203@gmail.com
 **/
public class RetrofitManager {

   public static class SingletonHolder {
       private static final RetrofitManager instance = new RetrofitManager();
   }

   public static final RetrofitManager getInstance() {
       return SingletonHolder.instance;
   }

   //-----------------------------------------------------------------------------------------------

   private Api mApi;

   RetrofitManager () {
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(RequestApi.BASE_SAAS_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       mApi = retrofit.create(Api.class);
   }

    public Api getApi() {
        return mApi;
    }

    public Map<String, String> getParams() {
        HashMap<String, String> sourceMap = new HashMap<>();
        sourceMap.put(Constants.PARA_APPID, "yPViQAfC");
        sourceMap.put(Constants.PARA_SHOW_USERINFO, "1");
        Map<String, String> requestMap = getBaseSaasParameters(sourceMap);
        return requestMap;
   }

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

}
