package com.example.createmvvm.api.post

import com.app.drivingtest.api.GtuApi
import com.app.drivingtest.data.error.SubmitQueError
import com.app.drivingtest.data.param.PurchasePlanParam
import com.app.drivingtest.data.response.PurchasePlanResponse
import com.app.drivingtest.utils.PrefUtils
import com.app.drivingtest.utils.resource.Resource
import com.app.drivingtest.utils.resource.ResponseHandler
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ExperimentalCoroutinesApi
class postRepositoriy @Inject constructor(
    private val api: GtuApi,
    private val responseHandler: ResponseHandler,
    private val mPrefUtils: PrefUtils,
) {
    suspend fun purchase(param: PurchasePlanParam): kotlinx.coroutines.flow.Flow<Resource<PurchasePlanResponse>> = flow {
        emit(Resource.loading())
        try {
            val response = responseHandler.handleResponse(api.purchase(param))
            when (response.status) {
                Status.SUCCESS -> {
                    val responseBody = response.data
                    emit(Resource.success(responseBody))
                    print(Gson().toJson(responseBody))
                }
                Status.ERROR -> {
                    emit(Resource.error(Gson().fromJson(response.error.toString(), SubmitQueError::class.java)))
                }
                Status.EXCEPTION -> {
                    emit(Resource.exception(response.error as Throwable))
                }
                else -> {}
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.exception(e))
        }
    }


}