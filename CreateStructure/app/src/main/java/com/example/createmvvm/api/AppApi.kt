package com.example.createmvvm.api

import com.example.createmvvm.di.Resource
import com.example.createmvvm.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApi {

    @GET("products")
    suspend fun getProduct(): Response<ArrayList<ProductResponse>>

    @POST("v2/payment/purchase_plan/in_app/")
    suspend fun purchase(@Body param: PurchasePlanParam): Response<PurchasePlanResponse>



}