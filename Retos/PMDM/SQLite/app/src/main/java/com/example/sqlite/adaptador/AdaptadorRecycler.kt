package com.example.sqlite.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.R
import com.example.sqlite.model.Elemento

class AdaptadorRecycler(var lista: ArrayList<Elemento>, var context: Context):
RecyclerView.Adapter<AdaptadorRecycler.MyHolder>(){
    class MyHolder(var view: View): RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.textId)
        val nombre = view.findViewById<TextView>(R.id.textNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
       return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val elemento: Elemento = lista.get(position)
        holder.id.text = elemento.id.toString()
        holder.nombre.text = elemento.nombre
    }

    fun actualizarLista(lista: ArrayList<Elemento>){
        this.lista = lista
        notifyDataSetChanged()
    }
}