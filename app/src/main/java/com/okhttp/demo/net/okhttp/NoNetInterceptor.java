package com.okhttp.demo.net.okhttp;

import com.okhttp.demo.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NoNetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

            boolean isConnected = NetWorkUtil.isNetworkAvailable();
            Request request = chain.request();
            if (!isConnected) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                //无网络缓存30天
                int maxStale = 60 * 60 * 24 * 30;
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            //有网络时直接返回
            return chain.proceed(request);

    }
}