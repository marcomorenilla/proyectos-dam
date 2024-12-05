/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficherosjava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Marco
 */
public class FicherosStream {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DataOutputStream dos =  new DataOutputStream(new FileOutputStream("biblio.data"));
        
        dos.writeInt(1);
        dos.writeUTF("Harry Potter");
        dos.writeDouble((Double)67.80);
        
        dos.close();
        
        DataInputStream dis = new DataInputStream(new FileInputStream("biblio.data"));
        int numero = dis.readInt();
        String titulo =  dis.readUTF();
        double precio = dis.readDouble();
        
        System.out.println("numero: " + numero);
        System.out.println("titulo: " + titulo);
        System.out.println("precio: " + precio);
    }
}
