package com.example.createmvvm.di

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.createmvvm.app.AppConstant
import com.example.createmvvm.utils.UnauthorizedException
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

class ResponseHandler(
    val context: Context,
   // private val errorUtils: ErrorUtils,
) {


    fun <T : Any> handleResponse(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            val responseBody = response.body()
            return if (response.code() in (200..299)) {
                Resource.success(responseBody)
            } else {
                if (responseBody != null) {
                    Resource.success(responseBody)
                } else {
                    Resource.exception(HttpException(response))
                }
            }
        } else if (response.code() == 422 || response.code() == 500 || response.code() == 400) {
            val body = response.errorBody()
            val adapter = Gson().getAdapter(Any::class.java)
            val errorParser = adapter.fromJson(body?.string())
            val json = Gson().toJson(errorParser)
            return Resource.error(json)
        } else if (response.code() == 401) {
            val intent = Intent(AppConstant.SESSION_BROADCAST)
            intent.putExtra(AppConstant.DATA_TYPE, "yes")
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
            return Resource.exception(UnauthorizedException())
        } else if (response.code() == 404) {
            return Resource.exception(IllegalStateException())
        } else {
            return Resource.exception(IllegalStateException())
        }
    }


}