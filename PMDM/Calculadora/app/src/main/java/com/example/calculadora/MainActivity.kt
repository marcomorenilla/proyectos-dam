package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {


    //Zona declarativa
    private lateinit var binding: ActivityMainBinding
    private var bufferEntrada:String = ""
    private var operando:String?=null
    private var numero1:String?=null
    private var numero2:String?=null
    private var resultado:String?=null
    private val formatoDecimal = Regex("""\.0$""")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listeners
        binding.botonPorcentaje.setOnClickListener(this)
        binding.botonBorrar.setOnClickListener(this)
        binding.botonBorrarUltimo.setOnClickListener(this)
        binding.botonDividir.setOnClickListener(this)
        binding.botonSiete.setOnClickListener(this)
        binding.botonOcho.setOnClickListener(this)
        binding.botonNueve.setOnClickListener(this)
        binding.botonMultiplicar.setOnClickListener(this)
        binding.botonCuatro.setOnClickListener(this)
        binding.botonCinco.setOnClickListener(this)
        binding.botonSeis.setOnClickListener(this)
        binding.botonResta.setOnClickListener(this)
        binding.botonUno.setOnClickListener(this)
        binding.botonDos.setOnClickListener(this)
        binding.botonTres.setOnClickListener(this)
        binding.botonSuma.setOnClickListener(this)
        binding.botonCambio.setOnClickListener(this)
        binding.botonCero.setOnClickListener(this)
        binding.botonComa.setOnClickListener(this)
        binding.botonIgual.setOnClickListener(this)


    }


    override fun onClick(v: View?) {
        when(v?.id){
            binding.botonComa.id->{
                if (!bufferEntrada.contains(",")){
                    bufferEntrada += ","
                } else{
                    Snackbar.make(binding.root,"Ya hay posición decimal en el número, pulsa otro botón",Snackbar.LENGTH_SHORT)
                        .show()
                }
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonCero.id->{
                bufferEntrada += "0"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonUno.id->{
                bufferEntrada += "1"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonDos.id->{
                bufferEntrada += "2"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonTres.id->{
                bufferEntrada += "3"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonCuatro.id->{
                bufferEntrada += "4"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonCinco.id->{
                bufferEntrada += "5"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonSeis.id->{
                bufferEntrada += "6"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonSiete.id->{
                bufferEntrada += "7"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonOcho.id->{
                bufferEntrada += "8"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonNueve.id->{
                bufferEntrada += "9"
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonSuma.id->{
                procesarOperando("+")
            }
            binding.botonResta.id->{
                procesarOperando("-")
            }
            binding.botonMultiplicar.id->{
                procesarOperando("x")

            }
            binding.botonDividir.id->{
                procesarOperando("/")

            }
            binding.botonPorcentaje.id->{
                procesarOperando("%")
            }
            binding.botonBorrar.id->{
                bufferEntrada = ""
                binding.textoOperaciones.text = bufferEntrada
                binding.textoResultado.text = "0"
                numero1=null
                numero2=null
                operando=null
            }
            binding.botonBorrarUltimo.id->{
                var borrarUltimo:String =""
                for (  i in (0..<bufferEntrada.length -1)){
                    borrarUltimo += bufferEntrada[i].toString()
                }
                bufferEntrada = borrarUltimo
                binding.textoResultado.text = bufferEntrada
            }
            binding.botonCambio.id->{
                if(binding.textoResultado.text.contains("-")){
                    bufferEntrada = binding.textoResultado.text.toString()
                    bufferEntrada= bufferEntrada.replace("-","")
                    binding.textoResultado.text = bufferEntrada
                } else{
                    bufferEntrada=binding.textoResultado.text.toString()
                    bufferEntrada = "-"+bufferEntrada
                    binding.textoResultado.text = bufferEntrada
                }
                numero1=bufferEntrada

            }
            binding.botonIgual.id->{
                if (numero1!=null){
                    numero1 = numero1!!.replace(",",".")
                    numero2 = bufferEntrada.replace(",",".")
                    when(operando){
                        "+"->{
                            resultado = (numero1!!.toDouble() + numero2!!.toDouble()).toString()
                        }
                        "-"->{
                            resultado=(numero1!!.toDouble()-numero2!!.toDouble()).toString()
                        }
                        "x"->{
                            resultado=(numero1!!.toDouble()*numero2!!.toDouble()).toString()
                        }
                        "/"->{
                            if(numero2!!.toDouble()!=0.0){
                                resultado=(numero1!!.toDouble()/numero2!!.toDouble()).toString()
                            }
                        }
                        "%"->{
                            resultado=(numero1!!.toDouble()/100.0).toString()
                            bufferEntrada=""
                        }
                    }

                    if(resultado!!.contains(formatoDecimal)){
                        resultado =  resultado!!.replace(".0","")
                    }else{
                        resultado = resultado!!.replace(".",",")
                    }
                    binding.textoResultado.text = resultado
                    binding.textoOperaciones.text = numero1 + " "+ operando+" "+numero2+" = " + resultado
                    numero1=resultado
                    bufferEntrada=""
                    operando=null
                }

            }

        }
    }


    fun procesarOperando(operando:String){
        if (this.operando==null){
            this.operando=operando
            if (numero1==null){
                numero1 = bufferEntrada
            }

        }
        binding.textoOperaciones.text = bufferEntrada + " " + operando
        bufferEntrada = ""
    }
}