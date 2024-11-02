/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JSONG2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author damiansualdea
 */
public class EscrituraJSON {
    public static void main(String[] args) throws IOException {
        Jugador j1 = new Jugador("Karim Benzema",32);
        Jugador j2= new Jugador("Edson Arantes",87);
        
        Titulo t = new Titulo("Titulos 24/25");
        t.add(j1);
        t.add(j2);
        //Examen
        //Objeto Título... root... principal
        JSONObject jsonTitulo = new JSONObject();
        jsonTitulo.put("nombre", t.getNombre());
        //Vamos a recorrer el array de títulos y los 
        //jugadores los metemos en el JSONArray
        JSONArray jsonJugadores = new JSONArray();
        for(Jugador j : t.getJugadores()){
            //Creamos objeto jugador
            JSONObject jsonJugador = new JSONObject();
            //Recogemos del arrayList y lso metemos en el JsonObjecto
            jsonJugador.put("nombre", j.getNombre());
            jsonJugador.put("edad", j.getEdad());
            //Lo meto en el array de jsonJugadrores
            jsonJugadores.put(jsonJugador);
        }
        //Añado el array al Json
        jsonTitulo.put("participantes",jsonJugadores);
        
        
        //Escritura NO EXAMEN
        Files.write(Paths.get("jugones.json"),
                jsonTitulo.toString().getBytes());
        
    }
   
}
