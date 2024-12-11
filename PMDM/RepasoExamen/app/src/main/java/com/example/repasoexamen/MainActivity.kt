package com.example.repasoexamen

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repasoexamen.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener {
    /**
     * App con varias pantallas y paso de datos entre ellas
     * MainActivity -> Loggin + botón isEnabled()
     * SecondActivity -> Snackbar bienvenido + nombre
     *                   Operaciones en base a un spinner simple sin escuchar (a posteriori) - Si no registrado
     *                   Operaciones en base a un spinner simple escuchando la opción y calculando el resultado dependiendo de esta - Si registrado
     *
     * */

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaUsuario: ArrayList<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.radioGroupHabilitar.setOnCheckedChangeListener(this)


    }

    override fun onClick(v: View?) {
        listaUsuario = arrayListOf(Usuario("Marco","1234"),
            Usuario("Noelia","123"), Usuario("Mario","abcd")
        )
        var user: Usuario =
            Usuario(binding.editNombre.text.toString(), binding.editPassword.text.toString())

        var listaFiltrada = listaUsuario.filter { it.nombre.equals(user.nombre) }



        if (listaFiltrada.size>0 && listaFiltrada.get(0).password.equals(user.password)) {

            val intent: Intent = Intent(this, SecondActivity::class.java)
            val bundle: Bundle = Bundle()

            bundle.putSerializable("usuario", user)
            intent.putExtra("bundle", bundle)

            startActivity(intent)
        } else  {
            var texto:String
            if (listaFiltrada.size == 0){
                texto = "usuario"
            } else {
                texto = "contraseña"
            }

            Snackbar.make(binding.root,"Problemas con: $texto",Snackbar.LENGTH_SHORT).show()
        }


    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

        when (checkedId) {
            binding.deshabilitado.id -> {
                binding.btnLogin.isEnabled = false
                binding.editNombre.isEnabled = false
                binding.editPassword.isEnabled = false
            }

            binding.habilitado.id -> {
                binding.btnLogin.isEnabled = true
                binding.editNombre.isEnabled = true
                binding.editPassword.isEnabled = true
            }
        }
    }
}