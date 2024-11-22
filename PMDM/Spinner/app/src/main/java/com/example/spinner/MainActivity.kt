package com.example.spinner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.spinner.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),  OnClickListener, OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaUsuario: ArrayList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLog.setOnClickListener(this)
        binding.spinnerLog.onItemSelectedListener = this

        usuarios()

    }



    private fun usuarios() {
        listaUsuario= arrayListOf(
            Usuario("Marco","abcd"),
            Usuario("Noelia","abcd"),
            Usuario("Roberto","1234")
        )
        listaUsuario.add(Usuario("Juan","1234"))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.btnLog.id ->{
                val intent: Intent = Intent(applicationContext, SecondActivity::class.java)
                val bundle: Bundle = Bundle()
                if (binding.editUser.isEnabled){
                    if (binding.editUser.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
                        val usuario: Usuario? = listaUsuario.find {
                            it.nombre.equals(binding.editUser.text.toString()) &&
                        it.password.equals(binding.editPass.text.toString())}
                        if (usuario==null){
                            Snackbar.make(binding.root,"Usuario no encontrado",Snackbar.LENGTH_SHORT).show()
                        } else{
                            bundle.putSerializable("usuarios", listaUsuario)
                            intent.putExtra("bundle",bundle)
                            startActivity(intent)
                        }
                    }
                }else{
                    startActivity(intent)
                }
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       val perfilSeleccionado: String = binding.spinnerLog.adapter.getItem(position).toString()
        Log.v("perfil",perfilSeleccionado)
        if (perfilSeleccionado.equals("Invitado")){
            binding.editUser.isEnabled = false
            binding.editPass.isEnabled = false
        }else{
            binding.editUser.isEnabled = true
            binding.editPass.isEnabled = true
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}