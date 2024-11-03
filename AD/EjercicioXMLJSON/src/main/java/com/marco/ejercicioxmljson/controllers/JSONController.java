/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxmljson.controllers;

import com.marco.ejercicioxmljson.models.Carrera;
import com.marco.ejercicioxmljson.models.Ciclista;
import com.marco.ejercicioxmljson.models.Etapa;
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
            
            JSONObject jsonEtapa = new JSONObject();
            jsonEtapa.put("Número", etapa.getNumero());
            jsonEtapa.put("Fecha", etapa.getFecha());
   
            //Array para ir recorriendo ciclistas
            JSONArray jsonArrayCiclistas = new JSONArray();
            for (Ciclista ciclista : etapa.getPodio()) {
                JSONObject jsonCiclista = new JSONObject();
                jsonCiclista.put("Nombre", ciclista.getNombre());
                
                jsonArrayCiclistas.put(jsonCiclista);
            }
            jsonArrayEtapas.put(jsonEtapa);             
        }
        
        jsonCarrera.put("Etapas", jsonArrayEtapas);
       
        //Escritura del JSON
        Files.write(Paths.get("vuelta.JSON"), jsonCarrera.toString().getBytes());
    }

}
