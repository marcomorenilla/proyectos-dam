/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ficherosjava;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Marco
 */
public class FicherosUTF8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader ficheroEntrada = new FileReader("libro.txt");
        BufferedReader bf = new BufferedReader(ficheroEntrada);
        PrintWriter ficheroSalida = new PrintWriter("libro-copia.txt");

        int cont = 0;
        String linea;
        while ((linea = bf.readLine()) != null) {
            cont++;
            System.out.println(cont + " : " + linea);
            ficheroSalida.println(linea.toUpperCase().replace('A', '@'));
        }

        FileInputStream fileIn = new FileInputStream("images.jpg");
        FileOutputStream fileOut = new FileOutputStream("random.jpg");
        
        int tam = 0;

        while ((tam = fileIn.read())!=-1) {
           fileOut.write(tam);
        }
          fileOut.close();
          ficheroSalida.close();
    }

}
