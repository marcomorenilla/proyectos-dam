package com.example.estados

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estados.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton.OnCheckedChangeListener

class MainActivity : AppCompatActivity(), OnClickListener,CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivityMainBinding
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recogemos el bundle si no es null
        contador = savedInstanceState?.getInt("contador") ?: 0
        binding.contador.text = contador.toString()


        //escuchamos diferentes eventos
        binding.add.setOnClickListener(this)
        binding.quit.setOnClickListener(this)
        binding.checkbox.setOnCheckedChangeListener(this)
        binding.clear?.setOnClickListener(this)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        //Metemos el estado de la app justo antes de romperse
        outState.putInt("contador", contador)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.add.id->{
                if (binding.editText.text.isNotEmpty()){
                    contador++
                    binding.contador.text = contador.toString()
                    binding.editText.text.clear()
                }


            }
            binding.quit.id->{
                if (binding.editText.text.isNotEmpty()){
                    contador--
                    binding.contador.text = contador.toString()
                    binding.editText.text.clear()
                }
            }
            binding.clear?.id->{
                contador=0
                binding.contador.text = contador.toString()
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView?.id){
            binding.checkbox.id->{
                binding.editText.isEnabled = isChecked
            }
        }
    }
}