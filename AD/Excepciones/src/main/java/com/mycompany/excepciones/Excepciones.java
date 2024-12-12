/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.excepciones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class Excepciones {

    /**
     * Excepciones son errores en tiempo de ejecución, hay que adelantarse a
     * ellos Hay que programar sabiendo que puede pasar, es más van a pasar
     *
     *
     */

    public static void main(String[] args) throws ExcepcionTelefono {

       String telefono = "+34666555444";
       String prefijo = telefono.substring(0,3);
        
        if(!prefijo.equals("+32")){
            throw new ExcepcionTelefono("Solo está permitido el +34");
        }
        
        
        /* try (FileReader f = new FileReader("hola.txt");) {
            int[] vector = new int[5];
            
            System.out.println(vector[3]);

        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el fichero");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Eres un motivado");
        } catch (IOException ex) {
            System.err.println(ex);
        }finally{
            System.out.println("Fin del programa");
        }
        */
       
       
    }
}
