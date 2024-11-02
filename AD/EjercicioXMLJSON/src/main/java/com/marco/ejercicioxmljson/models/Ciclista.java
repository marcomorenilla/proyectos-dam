/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxmljson.models;

/**
 *
 * @author Marco
 */
public class Ciclista {
    //Atributos
    private int puesto;
    private String nombre;
    private String equipo;
    private String tiempo;
    
    //Constructores
    public Ciclista(int puesto, String nombre, String equipo, String tiempo){
        this.puesto=puesto;
        this.nombre=nombre;
        this.equipo=equipo;
        this.tiempo=tiempo;
    }
    
    //toString
    @Override
    public String toString(){
        return "Nombre: " + nombre +"\n"+
               "Equipo: " + equipo +"\n"+
               "Puesto: " + puesto +"\n";
    }
    
    //Getters y Setters

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    
    
    
}
