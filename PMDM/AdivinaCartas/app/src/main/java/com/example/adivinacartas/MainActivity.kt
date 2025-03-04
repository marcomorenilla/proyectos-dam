package com.example.adivinacartas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BundleCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adivinacartas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    //Creamos binding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Añadir escuchas a botones
        binding.logButton.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.logButton.id->{

                if(binding.userLogEdit.text.isNotEmpty()) {
                    val intent: Intent = Intent(applicationContext, GameActivity::class.java)
                    val bundle: Bundle = Bundle()
                    bundle.putString("nombre",binding.userLogEdit.text.toString())
                    intent.putExtra("bundle",bundle)
                    Snackbar.make(
                        binding.root,
                        "Bienvenido al juego ${binding.userLogEdit.text}, pulsa jugar para empezar",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Jugar") {
                            startActivity(intent)
                        }
                        .show()
                } else{
                    Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }
}