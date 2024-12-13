package com.example.repasoexamenfinal

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repasoexamenfinal.databinding.ActivityMainBinding
import com.example.repasoexamenfinal.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle? = intent.extras?.getBundle("bundle") ?: null

        var usuario:String? = bundle?.getString("usuario")
        Log.v("usuario", usuario.toString())
        var password:String? = bundle?.getString("password")
        Log.v("password", password.toString())
        var ciclo: String? = bundle?.getString("ciclo")
        Log.v("ciclo",ciclo.toString())


        binding.textSecond.text = "Bienvenido $usuario, tu contraseña es $password y estás estudiando $ciclo"
    }
}