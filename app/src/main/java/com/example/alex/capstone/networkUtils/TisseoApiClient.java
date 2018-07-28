package com.example.alex.capstone.networkUtils;

import retrofit2.Retrofit;

public class TisseoApiClient {

    private static final String BASE_URL ="https://api.tisseo.fr/v1/";

    static Retrofit getClient() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
