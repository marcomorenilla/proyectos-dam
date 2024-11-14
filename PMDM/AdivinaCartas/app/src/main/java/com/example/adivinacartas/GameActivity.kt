package com.example.adivinacartas

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adivinacartas.databinding.ActivityGameBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var nombre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recogemos bundle del intent que inicia la actividad
        var bundle: Bundle? = intent.getBundleExtra("bundle")
        nombre = bundle?.getString("nombre")

        //Creamos Snackbar para empezar el juego
        Snackbar.make(
            binding.root,
            "Bienvenido $nombre, pulsa empezar para jugar",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Empezar") {

            }.show()

        var valorActual: Int = (0..12).random()
        var valorAdivinar: Int = (0..12).random()
        inicializarLayout(valorAdivinar)
        var resultado: Int = 0
        binding.botonSubir.setOnClickListener {
            if (valorActual > valorAdivinar) {
                resultado++
                valorAdivinar = (0..12).random()
                valorActual = (0..12).random()
                inicializarLayout(valorAdivinar)
            } else if (valorActual<valorAdivinar){
                Snackbar.make(binding.root,"Has fallado, para volver a empezar pulsa aceptar resultado: $resultado",Snackbar.LENGTH_SHORT)
                    .setAction("Aceptar"){}.show()
                resultado=0

            }else{
                Snackbar.make(binding.root,"Empate, sacando nueva carta",Snackbar.LENGTH_SHORT).show()
                valorAdivinar = (0..12).random()
                valorActual = (0..12).random()
                inicializarLayout(valorAdivinar)
            }
        }
        binding.botonBajar.setOnClickListener {
            if (valorActual < valorAdivinar) {
                resultado++
                valorAdivinar = (0..12).random()
                valorActual = (0..12).random()
                inicializarLayout(valorAdivinar)
            }else if (valorActual>valorAdivinar){
                Snackbar.make(binding.root,"Has fallado para volver a empezar pulsa aceptar, resultado: $resultado",Snackbar.LENGTH_SHORT)
                    .setAction("Aceptar"){}.show()
                resultado=0
            }else{
                Snackbar.make(binding.root,"Empate, sacando nueva carta",Snackbar.LENGTH_SHORT).show()
                valorAdivinar = (0..12).random()
                valorActual = (0..12).random()
                inicializarLayout(valorAdivinar)
            }
        }




    }

    private fun inicializarLayout(valorAdivinar: Int) {

        when (valorAdivinar) {

            0 -> binding.fondoJuego.setImageResource(R.drawable.c1)
            1 -> binding.fondoJuego.setImageResource(R.drawable.c2)
            2 -> binding.fondoJuego.setImageResource(R.drawable.c3)
            3 -> binding.fondoJuego.setImageResource(R.drawable.c4)
            4 -> binding.fondoJuego.setImageResource(R.drawable.c5)
            5 -> binding.fondoJuego.setImageResource(R.drawable.c6)
            6 -> binding.fondoJuego.setImageResource(R.drawable.c7)
            7 -> binding.fondoJuego.setImageResource(R.drawable.c8)
            8 -> binding.fondoJuego.setImageResource(R.drawable.c9)
            9 -> binding.fondoJuego.setImageResource(R.drawable.c10)
            10 -> binding.fondoJuego.setImageResource(R.drawable.c11)
            11 -> binding.fondoJuego.setImageResource(R.drawable.c12)
            12 -> binding.fondoJuego.setImageResource(R.drawable.c13)
        }

    }


}