/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONG2;

import java.util.ArrayList;

/**
 *
 * @author damiansualdea
 */
public class Titulo {
    private String nombre;
    private ArrayList<Jugador> jugadores;
    
    public Titulo(String nombre){ //No poner el constructor con ArrayList()
        this.nombre = nombre;
        jugadores = new ArrayList<Jugador>();
    }
    
    //add, remove, size...

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean add(Jugador e) {
        return jugadores.add(e);
    }

    @Override
    public String toString() {
        return "Titulo{" + "nombre=" + nombre + ", jugadores=" + jugadores + '}';
    }
    
    
    
    
    
    
}
