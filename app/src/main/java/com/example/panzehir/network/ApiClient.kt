package com.example.panzehir.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

open class ApiClient {

    private var retrofit : Retrofit?= null

    fun getClient() : Retrofit? {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }
        return retrofit
    }

}