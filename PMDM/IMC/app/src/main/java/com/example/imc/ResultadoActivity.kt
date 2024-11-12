package com.example.imc

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.databinding.ActivityMainBinding
import com.example.imc.databinding.ActivityResultadoBinding


class ResultadoActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityResultadoBinding
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.volverBtn.setOnClickListener(this)

        acciones()


    }

    private fun acciones() {

        val bundle: Bundle = intent.getBundleExtra("bundle")!!
        var peso: Double = bundle.getDouble("peso")
        var altura: Double = bundle.getDouble("altura")
        var sexo: Int = bundle.getInt("sexo")

        var IMC: Double = peso / ((altura / 100) * (altura / 100))
        var roundedIMC: String = String.format("%.1f", IMC)
        IMC = roundedIMC.toDouble()

        binding.textPeso.hint = peso.toString()
        binding.textAltura.hint = altura.toString()

        when (sexo) {
            mainBinding.radioMasculino.id -> {
                binding.radioGroupResult.check(binding.radioMasculinoResult.id)
                when (IMC) {
                    in 0.0..18.4 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Peso bajo"
                        binding.imagenCalculada.setImageResource(R.drawable.estado1)
                    }

                    in 18.5..24.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Peso normal"
                        binding.imagenCalculada.setImageResource(R.drawable.estado2)
                    }

                    in 25.0..29.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Sobrepeso, a montar en bici"
                        binding.imagenCalculada.setImageResource(R.drawable.estado4)
                    }

                    in 30.0..34.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase I"
                        binding.imagenCalculada.setImageResource(R.drawable.estado6)
                    }

                    in 35.0..39.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase II"
                        binding.imagenCalculada.setImageResource(R.drawable.estado5)
                    }

                    else -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase III"
                        binding.imagenCalculada.setImageResource(R.drawable.estado3)
                    }
                }
            }

            mainBinding.radioFemenino.id -> {
                binding.radioGroupResult.check(binding.radioFemeninoResult.id)
                when (IMC) {
                    in 0.0..16.4 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Peso bajo"
                        binding.imagenCalculada.setImageResource(R.drawable.estado1)
                    }

                    in 16.5..22.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Peso normal"
                        binding.imagenCalculada.setImageResource(R.drawable.estado2)
                    }

                    in 23.0..25.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Sobrepeso"
                        binding.imagenCalculada.setImageResource(R.drawable.estado4)
                    }

                    in 26.0..30.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase I"
                        binding.imagenCalculada.setImageResource(R.drawable.estado6)
                    }

                    in 31.0..33.9 -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase II"
                        binding.imagenCalculada.setImageResource(R.drawable.estado5)
                    }

                    else -> {
                        binding.textoCalculado.text = "$IMC \n\n\n Obesidad clase III"
                        binding.imagenCalculada.setImageResource(R.drawable.estado3)
                    }
                }
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.volverBtn.id -> {
                finish()
            }
        }
    }
}