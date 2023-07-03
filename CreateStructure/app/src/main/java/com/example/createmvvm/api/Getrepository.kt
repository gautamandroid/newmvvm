package com.example.createmvvm.api

import com.example.createmvvm.di.Resource
import com.example.createmvvm.di.ResponseHandler
import com.example.createmvvm.di.Status
import com.example.createmvvm.model.CommonError
import com.example.createmvvm.model.ProductResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Getrepository @Inject constructor(
    private val api: AppApi,
    private val responseHandler: ResponseHandler,

    ) {
    suspend fun getProduct(): Flow<Resource<ArrayList<ProductResponse>>> = flow {
        emit(Resource.loading())

        try {
            val response = responseHandler.handleResponse(api.getProduct())
            val responseBody = response.data
            when (response.status) {
                Status.SUCCESS -> {
                    val responseBody = response.data
                    emit(Resource.success(responseBody))
                    print(Gson().toJson(responseBody))
                }
                Status.ERROR -> {
                    emit(Resource.error(Gson().fromJson(response.error.toString(), CommonError::class.java)))
                }
                Status.EXCEPTION -> {
                    emit(Resource.exception(response.error as Throwable))
                }

                else -> {}
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}