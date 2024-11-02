/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONG1;

import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.lines;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author damiansualdea
 */
public class EscrituraJSON {
    public static void main(String[] args) throws IOException {
       //Crear los objetos para poder meterlos
       Participante jugador1 = 
               new Participante("Dioni Villalba",39);
       Participante jugador2 = 
               new Participante("Juninho Pernambucano",49);
       
       Titulo titulo = new Titulo("Títulos 24/25");
       titulo.add(jugador1);
       titulo.add(jugador2);
       //EXAMEN manejar esta parte, pero entender la anterior
       //por si Damián pregunta algo conceptual
       JSONObject jsonTitulos = new JSONObject();
       jsonTitulos.put("nombre", titulo.getNombre());
       jsonTitulos.put("pichichi","Carvajal" );
       
       //vamos a meter un array de jugadores... array en el json
       JSONArray jsonJugadores = new JSONArray();
       for(Participante p : titulo.getParticipantes()){
           //Creamos objeto
           JSONObject jsonParticipante = new JSONObject();
           //Metemos valores del arrayList
           jsonParticipante.put("nombre", p.getNombre());
           jsonParticipante.put("edad", p.getEdad());
           //añadirlo como elemento en el array
           jsonJugadores.put(jsonParticipante);
       }
       //Ya lo introduzco en el jsonTitulos. Meter el array
       jsonTitulos.put("participantes", jsonJugadores);
       
       //ESCRITURA DEL ARCHIVO-- no examen
       Files.write(Paths.get("futbol.json"), 
               jsonTitulos.toString().getBytes());
       
    }
 
}
