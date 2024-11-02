/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class Etapa {
    
    //Atributos
    private int numero;
    private List<Ciclista> podio;
    
    //Constructor
    public Etapa(int numero){
        this.numero=numero;
        podio = new ArrayList<Ciclista>();
    }
    
    //Método para añadir ciclistas al podio
    public void addCiclista(Ciclista ciclista){
        podio.add(ciclista);
    }
    
    //Método para eliminar ciclistas del podio
    public void removeCiclista(Ciclista ciclista){
        if (podio.size()>0){
            podio.remove(ciclista);
        } else{
            System.out.println("No hay ciclistas en la lista");
        }
    }
    
    //Método para mostrar el podio
    public void mostrarPodio(){
        
        System.out.println("Podio de la etapa: "+ numero);
        for (Ciclista ciclista : podio) {
            System.out.println(ciclista);           
        }
    }
    
    //Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Ciclista> getPodio() {
        return podio;
    }

    public void setPodio(List<Ciclista> podio) {
        this.podio = podio;
    }
    
    
    
    
    
}
