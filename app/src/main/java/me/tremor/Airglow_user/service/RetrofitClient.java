package me.tremor.Airglow_user.service;

import me.tremor.Airglow_user.UserClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private String CIELO="http://api.airglow.me:5000/v1/";
    private String TERRA="http://192.168.1.35:5000/v1/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(CIELO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInsance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public UserClient getApi(){
        return retrofit.create(UserClient.class);
    }
}
