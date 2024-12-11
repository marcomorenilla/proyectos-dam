package com.example.repasoexamen

import java.io.Serializable

class Usuario (var nombre:String, var password:String):Serializable {

    var correo:String? =null

    constructor(nombre: String, password: String, correo:String): this(nombre, password){
        this.correo = correo
    }

}