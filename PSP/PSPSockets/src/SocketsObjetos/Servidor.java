

package SocketsObjetos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = 
                new ServerSocket(4567);
        System.out.println("SOY EL SERVIDOR");
        Socket cliente = servidor.accept();
        //parado hasta recibir un cliente      
        //Trabajo con datos primitivos.. lectura
        ObjectInputStream flujoDatosLectura = 
                new ObjectInputStream(
                        cliente.getInputStream());
        
        Persona persona = 
                (Persona) flujoDatosLectura.readObject();
        
        System.out.println("El cliente me envía: ");
        System.out.println(persona);
        
        persona.setNombre("Luka Doncic");
        persona.setEdad(25);
        
        ObjectOutputStream flujoDatosEscritura =
                new ObjectOutputStream(
                        cliente.getOutputStream());
        
        flujoDatosEscritura.writeObject(persona);
        

        cliente.close();
    }
}
