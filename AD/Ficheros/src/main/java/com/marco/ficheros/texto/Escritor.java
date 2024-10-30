/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ficheros.texto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class Escritor {
    
    public void writeFile(String filepath){
        File file = new File(filepath);
    
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("Hola, caracola");
            bw.newLine();
            bw.write("Me llamo Marco");
            bw.newLine();
            bw.write("Probando Netbeans");
        } catch (IOException ex) {
            System.out.println("Exception input output");
        }
    }
    
    public void writeNewFile(String content){
        File f = new File("texto\\fichero2.txt");
        FileWriter fw = null;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter (f))){
            bw.write(content);
        } catch (IOException ex) {
            System.out.println("No sé por qué no saca printstacktrace");
        }
        
    }
}
