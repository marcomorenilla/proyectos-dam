package com.example.adivinacartas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adivinacartas.databinding.ActivityGameBinding
import com.google.android.material.snackbar.Snackbar

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var nombre:String?=null
    private var arrayId: Array<String> =Array<String>(11){("c"+it+1+".png")}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recogemos bundle del intent que inicia la actividad
        var bundle:Bundle? = intent.getBundleExtra("bundle")
        nombre = bundle?.getString("nombre")

        //Creamos Snackbar para empezar el juego
        Snackbar.make(binding.root,"Bienvenido $nombre, pulsa empezar para jugar", Snackbar.LENGTH_INDEFINITE)
            .setAction("Empezar"){
                //Pendiente de dar funcionalidad
            }.show()
    }

    fun random():Int{

        return 0
    }
}