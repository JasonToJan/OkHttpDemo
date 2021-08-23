package com.okhttp.demo.net.retrofit;

import com.okhttp.demo.models.BaseData;
import com.okhttp.demo.models.HomeTabBean;
import com.okhttp.demo.net.RequestApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * desc: retrofit请求网络数据
 * *
 * update record:
 * *
 * created: Jason Jan
 * time:    2021/8/23 17:17
 * contact: jason1211241203@gmail.com
 **/
public interface Api {

    @GET(RequestApi.REQUEST_GAME_CENTER_GET)
    Call<BaseData<HomeTabBean>> getHomeData(@QueryMap Map<String, String> map);
}
