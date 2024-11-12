package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.imc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener, RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroup.setOnCheckedChangeListener(this)
        binding.calcularBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        val intent: Intent = Intent(applicationContext, ResultadoActivity::class.java)
        val bundle: Bundle = Bundle()
        val peso: Double = binding.editPeso.text.toString().toDouble()
        val altura: Double = binding.editAltura.text.toString().toDouble()
        val sexo: Int = binding.radioGroup.checkedRadioButtonId
        when (v?.id) {
            binding.calcularBtn.id -> {
                if (binding.editPeso.text.isNotEmpty() && binding.editAltura.text.isNotEmpty()) {

                    var resultado = "Peso: $peso, Altura: $altura, Sexo: $sexo"
                    Snackbar.make(binding.root, resultado, Snackbar.LENGTH_SHORT).show()
                    bundle.putDouble("peso", peso)
                    bundle.putDouble("altura", altura)
                    bundle.putInt("sexo", sexo)
                    intent.putExtra("bundle", bundle)
                    startActivity(intent)

                } else {
                    Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT).show()
                }

            }
        }

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

    }

    override fun onRestart() {
        super.onRestart()
        binding.editAltura.text.clear()
        binding.editPeso.text.clear()

    }


}