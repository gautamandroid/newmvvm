package com.example.createmvvm.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createmvvm.di.Resource
import com.example.createmvvm.model.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class GetViewModel @Inject constructor(
    private val repository: Getrepository
) : ViewModel() {

    private val graphdata: MutableLiveData<Resource<ArrayList<ProductResponse>>> = MutableLiveData()

    fun getList(): LiveData<Resource<ArrayList<ProductResponse>>> {
        return graphdata
    }

    fun getProducrList() {
        viewModelScope.launch {
            repository.getProduct().onEach { state ->
                graphdata.value = state

            }.launchIn(viewModelScope)
        }
    }

}