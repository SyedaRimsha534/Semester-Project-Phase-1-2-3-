package com.example.roomdatabasewithnavbar.repository.networkRepository

import com.example.roomdatabasewithnavbar.fragments.retrofitFruit.FruitModel
import com.example.roomdatabasewithnavbar.repository.Fruit
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface  APIService{

    @GET("products/")
    fun getFruitsList(
    ): Flow<FruitModel>

    @GET("products/")
    fun getFruitsListFromModel(
    ): Call<FruitModel>
}
