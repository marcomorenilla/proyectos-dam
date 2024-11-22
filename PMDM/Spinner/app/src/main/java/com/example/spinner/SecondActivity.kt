package com.example.spinner

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle:Bundle? = intent.getBundleExtra("bundle")
        val listaUsuario:ArrayList<Usuario>? =bundle?.getSerializable("usuarios") as? ArrayList<Usuario>

        if (listaUsuario==null){
            Snackbar.make(binding.root, "No ha llegado ningún usuario",Snackbar.LENGTH_INDEFINITE).show()
        }

    }
}