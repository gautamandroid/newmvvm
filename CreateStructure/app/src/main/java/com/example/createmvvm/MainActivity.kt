package com.example.createmvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createmvvm.adapter.ListAdepter
import com.example.createmvvm.api.GetViewModel
import com.example.createmvvm.databinding.ActivityMainBinding
import com.example.createmvvm.di.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ListAdepter
   // private var viewmodel:GetViewModel by viewModels()
    private val viewmodel: GetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewmodel.getProducrList()
        initviwe()
        apicalling()

    }

    private fun apicalling() {
       viewmodel.getList().observe(this){state ->

           when(state.status){
                Status.SUCCESS->{
                  var lisiAll=state.data

                    if (lisiAll!=null){
                        adapter.setItems(lisiAll)
                    }

                }
               Status.ERROR->{

               }
               Status.EXCEPTION->{

               }
               Status.LOADING->{

               }
           }



       }


    }

    private fun initviwe() {

        binding.rcRecycler.adapter = ListAdepter(this)
        binding.rcRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcRecycler.adapter = adapter

    }
}