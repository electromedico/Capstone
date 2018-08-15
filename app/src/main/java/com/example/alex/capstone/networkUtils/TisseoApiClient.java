package com.example.alex.capstone.networkUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TisseoApiClient {

    private static final String BASE_URL ="https://api.tisseo.fr/v1/";

    public static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
