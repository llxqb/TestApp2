package com.llxqb.testapp.network;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * CreateDate   :   19/09/04:10:23
 * Author       :   llxqb
 * PackageName  :   com.xmzj.network
 * Description  :   http log
 */

public class NetworkInterceptor implements Interceptor {

    public NetworkInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
//        Charset UTF8 = Charset.forName("UTF-8");
//        Request originalRequest = chain.request();
//        // get new request, add request header
//        Request updateRequest = originalRequest.newBuilder()
//                .removeHeader("Accept-Encoding")
//                .header("Accept-Encoding", "identity")
//                .build();
//        Response response = chain.proceed(updateRequest);
//        LogUtils.e("respBody:" + new Gson().toJson(response.body().contentLength()));
//        return response;
        Request request = chain.request().newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .build();
        return chain.proceed(request);

    }
}
