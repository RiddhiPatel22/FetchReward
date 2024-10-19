package com.example.fetchreward.viewModel.retrofitApi

import com.example.fetchreward.room.ItemEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("hiring.json")
    suspend fun getItems(): List<ItemEntity>

    companion object{
        private var apiService : ApiService? = null

        fun getInstance() : ApiService{
            if(apiService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
        return apiService!!
        }
    }
}