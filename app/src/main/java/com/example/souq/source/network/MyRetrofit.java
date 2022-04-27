package com.example.souq.source.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static  Retrofit myRetrofit = null;



public static Apis getApis(){
    if (myRetrofit == null){
        myRetrofit = new Retrofit.Builder()
                .baseUrl("https://student.valuxapps.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return  myRetrofit.create(Apis.class);
}
}
