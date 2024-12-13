package com.example.repasoexamenfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repasoexamenfinal.databinding.ActivityMainBinding
import kotlinx.coroutines.newSingleThreadContext
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<String>
    private lateinit var adaptador: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = arrayListOf("DAM","DAW","ASIR")
        adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1, list)
        adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.spinnerCiclos.adapter = adaptador

        binding.btnAcceso.setOnClickListener(){
            var intent:Intent = Intent(applicationContext,SecondActivity::class.java)
            var bundle: Bundle = Bundle()

            bundle.putString("usuario", binding.editUsuario.text.toString())
            bundle.putString("password", binding.editPassword.text.toString())
            bundle.putString("ciclo",binding.spinnerCiclos.selectedItem.toString())

            Log.v("info", "Usuario: ${binding.editUsuario.text.toString()}, Password: ${binding.editPassword.text.toString()}, ciclo: " +
                    "${binding.spinnerCiclos.selectedItem.toString()}")

            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }
    }
}