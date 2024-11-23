package com.example.spinner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spinner.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    //Traemos objeto recycler en el que meteremos la lista
    private lateinit var adaptadorUsuarios: AdaptadorUsuarios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.getBundleExtra("bundle")
        val listaUsuario:ArrayList<Usuario> =bundle?.getSerializable("usuarios") as ArrayList<Usuario>

        if (listaUsuario==null){
            Snackbar.make(binding.root, "No ha llegado ningún usuario",Snackbar.LENGTH_INDEFINITE).show()
        } else {
            //Metemos usuarios dentro del recycler
            Log.v("listasecond",listaUsuario.size.toString())


            adaptadorUsuarios = AdaptadorUsuarios(listaUsuario,this)
            binding.recyclerLista.adapter = adaptadorUsuarios
            for (i in listaUsuario){
                Log.v("listaAdapter",i.nombre + " " +i.password)
            }
            binding.recyclerLista.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        }



    }
}