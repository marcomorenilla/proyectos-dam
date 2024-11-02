/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONG1;

/**
 *
 * @author damiansualdea
 */
public class Participante {
    private String nombre;
    private int edad;
    
    //Constructor
    public Participante(String nombre, int edad) {    
        this.nombre = nombre;
        this.edad = edad;
    }

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    //toString

    @Override
    public String toString() {
        return "Participante{" + "nombre=" + nombre + ", edad=" + edad + '}';
    }
     
    
}
