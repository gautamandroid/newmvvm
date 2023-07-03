package com.example.createmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.createmvvm.databinding.FragmentListrecyclerBinding
import com.example.createmvvm.model.ProductResponse

class ListAdepter(var context: Context) :
    RecyclerView.Adapter<ListAdepter.MyViewHolder>() {

    // var list: ArrayList<products>()
    var list = ArrayList<ProductResponse>()


    class MyViewHolder(items: FragmentListrecyclerBinding) : RecyclerView.ViewHolder(items.root) {
        var bind = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            FragmentListrecyclerBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        holder.bind.tvTitel.text = item.title
        holder.bind.rattingPoint.text = item.rating?.rate.toString()
        holder.bind.tvDescription.text = item.description
        holder.bind.tvPrice.text = item.price.toString()
        holder.bind.RtRattingbar.rating =
            if (item.rating?.rate != null) item.rating.rate.toFloat() else 0f

        Glide.with(context).load(item.image).into(holder.bind.ivTmage)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(iteamList: ArrayList<ProductResponse>) {
        this.list = iteamList
        notifyDataSetChanged()
    }

}