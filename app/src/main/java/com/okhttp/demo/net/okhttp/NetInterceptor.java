package com.okhttp.demo.net.okhttp;

import com.okhttp.demo.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        boolean isConnected = NetWorkUtil.isNetworkAvailable();

        Request request = chain.request();
        if (isConnected) {
            //如果有网络,缓存90s
            Response response = chain.proceed(request);
            int maxTime = 120;
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxTime)
                    .build();
        }
        //没有网络不做处理直接返回
        return chain.proceed(request);
    }
}