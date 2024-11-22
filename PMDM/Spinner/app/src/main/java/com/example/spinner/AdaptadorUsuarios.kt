package com.example.spinner

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdaptadorUsuarios(var list: ArrayList<Usuario>,var context:Context):
    RecyclerView.Adapter<AdaptadorUsuarios.MyHolder>(){
        //Aquí se define cómo va a ser el Holder
    class MyHolder(itemView: View):ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }
}