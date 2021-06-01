package com.example.loginpage;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApiAdapter {
    private static Authentication AUTH;

    public static Authentication getApiService(){

        //interceptor to inspect response body
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        //connect intercept to requests
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = "http://10.0.2.2:8080/myapp/auth/";

        if(AUTH == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            AUTH = retrofit.create(Authentication.class);
        }


        return AUTH;

    }
}
