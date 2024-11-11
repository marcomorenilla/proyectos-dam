package com.example.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivityMainBinding
import com.example.imc.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle: Bundle = intent.getBundleExtra("bundle")!!
        var peso:Double = bundle.getDouble("peso")
        var altura:Double= bundle.getDouble("altura")
        var sexo:Char = bundle.getChar("sexo")

        binding.textPeso.hint = peso.toString()
        binding.textAltura.hint = altura.toString()

        var IMC: Double = peso/(altura*altura)

        when(sexo){
            'M'->{
                binding.radioGroupResult.check(binding.radioMasculinoResult.id)
                when(IMC){
                    in 0.0 .. 18.4->{
                        binding.textoCalculado.text = "Bajo peso"
                        binding.imagenCalculada.setImageResource(R.drawable.estado5)
                    }
                }
            }
            'F'->{
                binding.radioGroupResult.check(binding.radioFemeninoResult.id)
            }
        }








    }
}