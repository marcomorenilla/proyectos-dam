/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ficheros.texto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class Reader {
    
    public String readFile(String path){
        File f = new File(path);
        String fichero ="";
        try(BufferedReader br = 
                new BufferedReader(
                new FileReader(f))){
            String row  = null;
            
            while((row = br.readLine())!= null){
                System.out.println(row);
                if (row.indexOf("Hola")==-1){
                    System.out.println("Primera línea pasada");
                    fichero += row +"\n";
                } else {
                    System.out.println("Leyendo la primera línea");
                }
            }
        }   
        catch (IOException ex) {
            System.out.println("Input Output exception");
            
        }
        return fichero;
    }
}
