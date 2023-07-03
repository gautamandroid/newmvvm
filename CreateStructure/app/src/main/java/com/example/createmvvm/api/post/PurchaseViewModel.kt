package com.example.createmvvm.api.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createmvvm.di.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val repository: postRepositoriy
) : ViewModel() {

//    private val purchasePlan: MutableLiveData<Resource<PurchasePlanResponse>> = MutableLiveData()
//
//    fun submitData(): LiveData<Resource<PurchasePlanResponse>> {
//        return purchasePlan
//    }
//
//    fun purchaseSucess(params: PurchasePlanParam) {
//        viewModelScope.launch {
//                repository.purchase(params).onEach { state ->
//                purchasePlan.value = state
//
//            }.launchIn(viewModelScope)
//
//        }
//    }


}