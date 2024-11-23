package com.example.spinner

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdaptadorUsuarios(var list: ArrayList<Usuario>, var context: Context) :
    RecyclerView.Adapter<AdaptadorUsuarios.MyHolder>() {
    //Aquí se define cómo va a ser el Holder
    class MyHolder(itemView: View) : ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.nombreUsuario)
        val password = itemView.findViewById<TextView>(R.id.contraseñaUsuario)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        //Definimos cómo se crea el holder
        //lo inflamos y lo metemos dentro de una constante de tipo Holder que devolvemos
        val vista = LayoutInflater.from(context).inflate(R.layout.item_usuario,parent,false)
        val holder: MyHolder = MyHolder(vista)
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // Acciones que pasan cuando se van creando las filas
        //Cogemos al usuario de la lista
        var usuario = list[position]
        Log.v("listaAdapter",list.size.toString())
        Log.v("listaNombre", usuario.nombre)
        //Colocamos nombre en apartado nombre
        holder.nombre.text = usuario.nombre

        //Colocamos la cntraseña en apartado contraseña
        holder.password.text = usuario.password
    }

    fun actualizarLista(list: ArrayList<Usuario>){
        this.list = list
        notifyDataSetChanged()
    }
}