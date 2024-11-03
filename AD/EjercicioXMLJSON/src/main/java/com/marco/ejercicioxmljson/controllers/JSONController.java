/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxmljson.controllers;

import com.marco.ejercicioxmljson.models.Carrera;
import com.marco.ejercicioxmljson.models.Ciclista;
import com.marco.ejercicioxmljson.models.Etapa;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marco
 */
public class JSONController {

    public void JSONwriter(Carrera carrera) throws IOException {
        //Creamos el objeto json para ir añadiendo elementos
        JSONObject jsonCarrera = new JSONObject();

        jsonCarrera.put("Nombre", carrera.getNombre());

        //Array para ir recorriendo etapas
        JSONArray jsonArrayEtapas = new JSONArray();
        for (Etapa etapa : carrera.getEtapas()) {
            
            //Creamos objeto para ir añadiendo datos mientra recorremos
            JSONObject jsonEtapa = new JSONObject();
            jsonEtapa.put("Número", etapa.getNumero());
            jsonEtapa.put("Fecha", etapa.getFecha());
   
            //Array para ir recorriendo ciclistas
            JSONArray jsonArrayCiclistas = new JSONArray();
            for (Ciclista ciclista : etapa.getPodio()) {
                
                //Creamos objeto para ir añadiendo datos mientras recorremos
                JSONObject jsonCiclista = new JSONObject();
                jsonCiclista.put("Nombre", ciclista.getNombre());
                
                jsonArrayCiclistas.put(jsonCiclista);
                
            }
            
            //Añadimos a objeto información antes de añadirlo al array
            jsonEtapa.put("Ciclistas: ",jsonArrayCiclistas);
            
            //Añadimos objeto con datos al array en cada vuelta
            jsonArrayEtapas.put(jsonEtapa);             
        }
        
        //Añadimos  el array con la información de las etapas a la carrera
        jsonCarrera.put("Etapas", jsonArrayEtapas);
       
        //Imprimimos resultado que se va a escribir en el archivo
        System.out.println(jsonCarrera.toString(4));
       
        //Escritura del JSON
        FileWriter fw = new FileWriter(new File("vuelta.JSON"));
        fw.write(jsonCarrera.toString(4));
        fw.close();
        //Files.write(Paths.get("vuelta.JSON"), jsonCarrera.toString().getBytes());
    }

}
