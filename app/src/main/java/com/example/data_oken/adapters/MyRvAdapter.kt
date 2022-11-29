package com.example.data_oken.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data_oken.databinding.ItemRvBinding
import com.example.data_oken.models.MyContact

class MyRvAdapter( var list: List<MyContact> = emptyList()):RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){

        fun onBind(myContact: MyContact, position: Int){
            itemRvBinding.tvId.text = myContact.id.toString()
            itemRvBinding.tvName.text = myContact.name
            itemRvBinding.tvNumber.text = myContact.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
       return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
      holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size



}