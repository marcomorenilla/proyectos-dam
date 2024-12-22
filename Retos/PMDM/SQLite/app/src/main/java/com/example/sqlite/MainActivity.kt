package com.example.sqlite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.adaptador.AdaptadorRecycler
import com.example.sqlite.dao.ElementoDAO
import com.example.sqlite.databinding.ActivityMainBinding
import com.example.sqlite.model.Elemento

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaElemento: ArrayList<Elemento>
    private lateinit var recyclerAdaptador: AdaptadorRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAgregar.setOnClickListener(this)
        ElementoDAO(this).limpiarRegistros()
        ElementoDAO(this).updateId()
        listaElemento =ElementoDAO(this).getElementos()
        Log.v("elemento", "tamaño ${listaElemento.size}")

        recyclerAdaptador = AdaptadorRecycler(listaElemento,this)
        binding.recyclerElementos.adapter = recyclerAdaptador
        binding.recyclerElementos.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


    }

    override fun onClick(v: View?) {
        val texto = binding.editElemento.text.toString()
        ElementoDAO(this).insertarElemento(texto)
        listaElemento = ElementoDAO(this).getElementos()
        recyclerAdaptador.actualizarLista(listaElemento)


    }
}