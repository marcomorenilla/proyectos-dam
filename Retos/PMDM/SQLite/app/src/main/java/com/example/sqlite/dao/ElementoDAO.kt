package com.example.sqlite.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sqlite.db.DBHelper
import com.example.sqlite.model.Elemento

class ElementoDAO(var context: Context) {
    //Insertar elementos en db
    fun insertarElemento(nombre:String){
        val db:SQLiteDatabase = DBHelper(context,"elementos_db",null,1).writableDatabase
        val contenido :ContentValues = ContentValues()
        contenido.put("nombre",nombre)
        Log.v("elemento",contenido.get("nombre").toString())
        db.insert("elemento",null,contenido)
    }

    //Reiniciar BBDD
    fun limpiarRegistros(){
        val db:SQLiteDatabase = DBHelper(context,"elementos_db",null,1).writableDatabase
        db.delete("elemento","id != 0",null)
    }

    //Reiniciar id
    fun updateId(){
        val db:SQLiteDatabase = DBHelper(context,"elementos_db",null,1).writableDatabase
        val contenido:ContentValues = ContentValues()
        contenido.put("seq",0)
        //"update sqlite_sequence set seq=0 where name='elemento'"
        db.update("sqlite_sequence",contenido,"name=?", arrayOf("elemento"))
    }

    //Recoger elementos en db
    fun getElementos():ArrayList<Elemento>{
        val db: SQLiteDatabase = DBHelper(context,"elementos_db",null,1).readableDatabase
        val cursor:Cursor=
            db.query("elemento", arrayOf("id","nombre"),null,null,null,null,null)
        val listaElementos:ArrayList<Elemento> = ArrayList()
        while (cursor.moveToNext()){

            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)

            val elemento:Elemento = Elemento(id,nombre)
            listaElementos.add(elemento)
        }

        return listaElementos
    }
}