
package SocketsTipoPrimitivosDouble;

import SocketsObjetos.Persona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         Socket servidor = 
                new Socket("localhost", 4567);
        System.out.println("SOY EL CLIENTE");
        ObjectOutputStream flujoDatosEscritura =
                new ObjectOutputStream(
                        servidor.getOutputStream());
        
        Persona p = 
                new Persona("Antony Davis", 29);
        
        flujoDatosEscritura.writeObject(p);
        
        
        ObjectInputStream flujoDatosLectura =
                new ObjectInputStream(
                        servidor.getInputStream());
        
        Persona p2 = 
                (Persona) flujoDatosLectura.readObject();
        
        System.out.println("El servidor me entrega ");
        System.out.println(p2);
        
        servidor.close();
    }
}
