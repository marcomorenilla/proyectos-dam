/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONG1;

import java.util.ArrayList;

/**
 *
 * @author damiansualdea
 */
public class Titulo {

    private String nombre;
    private ArrayList<Participante> participantes;

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    //Constructor con instanciación del ArrayList()
    public Titulo(String nombre) {
        this.nombre = nombre;
        participantes = new ArrayList<Participante>();
    }
    //Add, Remove

    public boolean add(Participante e) {
        return participantes.add(e);
    }

    public Participante remove(int index) {
        return participantes.remove(index);
    }

    public int size() {
        return participantes.size();
    }
    

}
