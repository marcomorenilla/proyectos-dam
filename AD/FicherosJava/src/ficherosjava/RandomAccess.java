/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficherosjava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Marco
 */
public class RandomAccess {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile("coches,data", "rw");

        //Pensar en la estructura del fichero
        raf.writeInt(1); // 4 bytes
        raf.writeBoolean(true); // 1 byte
        raf.writeDouble(15600); // 8 bytes
        
        String marcaEntrada =  "RENAULT";
        int tam = marcaEntrada.length();
        for (int i= 1; i<= 50- tam; i++){
            marcaEntrada=marcaEntrada+" ";
        }
       
        raf.writeChars(marcaEntrada);
        
        

        //Gancho
        raf.seek(4);
        boolean esta = raf.readBoolean();
        System.out.println("Está el coche? " + esta);
        
        raf.seek(5);
        double precio = raf.readDouble();
        System.out.println("El coche cuesta: " + precio);
        
        raf.seek(13);
        
        String marcaFin = "";
        for (int i = 0; i < 50; i++) {
            marcaFin+= raf.readChar();
            
        }
        System.out.println("Marca: "+marcaFin);
        
        raf.close();

    }

}
