package com.stratone.bmotion.utils;

import com.stratone.bmotion.BuildConfig;
import com.stratone.bmotion.config.ServerConfig;
import com.stratone.bmotion.model.OrderDetails;
import com.stratone.bmotion.model.User;
import com.stratone.bmotion.rest.ApiInterface;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wim on 10/7/16.
 */
public class UploadService {

    private ApiInterface uploadInterface;

    public UploadService() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(180, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(180, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(180, TimeUnit.SECONDS);
        okhttpBuilder.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerConfig.API_ENDPOINT)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        uploadInterface = retrofit.create(ApiInterface.class);
    }

    public void SignUpMultipart(User user, MultipartBody.Part imageKTP, MultipartBody.Part pdfFile, Callback callback) {
        uploadInterface.registerMultipart(user.getNIP(),user.getName(),user.getProfession(),user.getEmail(),user.getPassword(),user.getPhone(), user.getKTP(), user.getExpdate(),user.getQuota(),user.getDocumentNo(),imageKTP, pdfFile).enqueue(callback);
    }

    public void SignUpBase64(User user, MultipartBody.Part ktp, Callback callback) {
        uploadInterface.registerBase64(user.getNIP(),user.getName(),user.getProfession(),user.getEmail(),user.getPassword(),user.getPhone(),user.getKTP(), ktp).enqueue(callback);
    }

    /*public void Order(String nip, List<OrderDetails> orderDetails, Callback callback)
    {
         uploadInterface.order(nip,orderDetails).enqueue(callback);
    }*/
}
