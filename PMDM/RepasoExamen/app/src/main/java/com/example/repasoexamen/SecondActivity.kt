package com.example.repasoexamen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repasoexamen.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity(), OnClickListener, OnItemSelectedListener {


    private lateinit var binding: ActivitySecondBinding
    private lateinit var lista: Array<CharSequence>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras!!.getBundle("bundle")
        var user: Usuario = bundle!!.getSerializable("usuario") as Usuario

        Log.v("usuario", user.nombre)


        binding.btnCalcular.setOnClickListener(this)
        binding.spinnerOperaciones.onItemSelectedListener = this


    }

    override fun onClick(v: View?) {
        //lista = arrayOf("¿Qué operación quieres hacer?","Sumar","Restar","Multiplicar","Dividir")
        //binding.spinnerOperaciones.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, lista)


        when (binding.spinnerOperaciones.selectedItem) {
            "Sumar" -> {
                binding.textResultado.text =
                    (binding.editNum1.text.toString().toInt() + binding.editNum2.text.toString()
                        .toInt()).toString()
            }

            "Restar" -> {
                binding.textResultado.text =
                    (binding.editNum1.text.toString().toInt() - binding.editNum2.text.toString()
                        .toInt()).toString()
            }

            "Multiplicar" -> {
                binding.textResultado.text =
                    (binding.editNum1.text.toString().toInt() * binding.editNum2.text.toString()
                        .toInt()).toString()
            }

            "Dividir" -> {
                if (!binding.editNum2.text.toString().equals("0")) {
                    binding.textResultado.text =
                        (binding.editNum1.text.toString().toInt() / binding.editNum2.text.toString()
                            .toInt()).toString()
                } else {
                    Snackbar.make(binding.root, "Zero division exception", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.v("escucha", "Spinner escuchando acciones")
        var texto = position.toString()
        Log.v("texto", texto)
        if (binding.editNum1.text.isNotEmpty() && binding.editNum2.text.isNotEmpty()) {
            when (position) {
                0 -> {
                    binding.textResultado.text =
                        (binding.editNum1.text.toString().toInt() + binding.editNum2.text.toString()
                            .toInt()).toString()
                }

                1 -> {
                    binding.textResultado.text =
                        (binding.editNum1.text.toString().toInt() - binding.editNum2.text.toString()
                            .toInt()).toString()
                }

                2 -> {
                    binding.textResultado.text =
                        (binding.editNum1.text.toString().toInt() * binding.editNum2.text.toString()
                            .toInt()).toString()
                }

                3 -> {
                    if (!binding.editNum2.text.toString().equals("0")) {
                        binding.textResultado.text =
                            (binding.editNum1.text.toString()
                                .toInt() / binding.editNum2.text.toString()
                                .toInt()).toString()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Zero division exception",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}