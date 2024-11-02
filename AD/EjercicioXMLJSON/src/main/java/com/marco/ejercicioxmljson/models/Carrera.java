/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxmljson.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class Carrera {
    //Atributos
    private String nombre;
    private List<Etapa>etapas;
    
    //Constructor
    public Carrera(String nombre){
        this.nombre = nombre;
        etapas = new ArrayList<Etapa>();
    }
    
    
    //Gestión de la lista de etapas
    public void addEtapa(Etapa etapa){
        etapas.add(etapa);
    }
    
    public void removeEtapa(Etapa etapa){
        if (etapas.size()>0){
            etapas.remove(etapa);
        } else{
            System.out.println("No hay etapas que eliminar");
        }
    }
    
    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }
    
}
