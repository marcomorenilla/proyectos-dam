/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retojson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marco
 */

public class RetoJSON {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String opcion;
        List <Hero> heroes = new ArrayList<Hero>();

        System.out.println("Bienvenido al creador de héroes");
        
        //do-while para escribir Héroes hasta que el usuario presione *
        do {
            
            System.out.println("Selecciona entre las siguientes opciones");
            System.out.println("1.- Crear Héroe");
            System.out.println("*.- Salir del programa");
            opcion = scn.next();

            switch (opcion) {
                case "1":
                    String apodo;
                    String nombre;
                    String link;
                    String img;
                    int size;
                    System.out.println("Introduce el nombre en clave del héroe: ");
                    scn.nextLine();
                    apodo = scn.nextLine();
                    System.out.println("Introduce el nombre de la persona: ");
                    nombre = scn.nextLine();
                    System.out.println("Introduce el link de la web del héroe: ");
                    System.out.println("Pulsa enter si no se conoce: ");
                    link = scn.nextLine();
                    System.out.println("Introduce la dirección de la imagen: ");
                    img = scn.next();
                    System.out.println("Introduce el tamaño de la imagen en MB: ");
                    size = scn.nextInt();
                    System.out.println("apodo: " + apodo);
                    System.out.println("nombre: " + nombre);
                    System.out.println("link: " + link);
                    System.out.println("img: " + img);
                    System.out.println("size: " + size);

                    Hero heroe = new Hero(apodo, nombre, link, img, size);
                    heroes.add(heroe);
                    
                    System.out.println("Longitud de lista: " + heroes.size());
                    break;
                case "*":
                    System.out.println("Saliendo del programa...");
                    opcion = "*";
                    break;
                default:
                    System.out.println("__________________________________________________________________");
                    System.out.println("Opción incorrecta vuelve a introducir una opción");
                    System.out.println("Acciones permitidas: \n1 (Crea un héroe)\n* (Sale del programa)");
                    System.out.println("__________________________________________________________________");
                    break;
            }

        } while (!opcion.equals("*"));
        
        // Escritura de la lista a través de método JSONWriter
        JSONWriter(heroes);
    }
    /**
    * Método  que utilizamos para procesar una lista, ir metiendo los elementos en JSONObjects y escribir el documento JSON
    * 
    * @param heroes List de héroes que creamos en do-while
    */
    public static void JSONWriter(List<Hero>heroes){
        JSONObject rootObject = new  JSONObject();
        
        rootObject.put("Hero", "heroes");
      
        JSONArray arrayHeroes = new JSONArray();
        
        for(Hero hero :heroes){
            JSONObject jsonHero = new JSONObject();
            
            jsonHero.put("hero", hero.getApodo());
            jsonHero.put("name", hero.getNombre());
            jsonHero.put("link", hero.getLink());
            jsonHero.put("img", hero.getImg());
            jsonHero.put("size", hero.getSize());
            
            arrayHeroes.put(jsonHero);
            
        }
        
        rootObject.put("heroes",arrayHeroes);
        
        
        try {
            Files.write(Paths.get("heroes.json"), rootObject.toString(4).getBytes());
            System.out.println("Archivo escrito correctamente");
        } catch (IOException ex) {
            Logger.getLogger(RetoJSON.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algo falló escribiendo el archivo");
        }
  

    }

}
