
package SocketsTipoPrimitivosDouble;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = 
                new ServerSocket(4567);
        System.out.println("SOY EL SERVIDOR");
        Socket cliente = servidor.accept();
        //parado hasta recibir un cliente      
        //Trabajo con datos primitivos.. lectura
        DataInputStream flujoDatosLectura = 
                new DataInputStream(
                        cliente.getInputStream());
        
        Double numeroCliente = flujoDatosLectura.readDouble();
        
        System.out.println("He recogido el número, es: "+numeroCliente);
        
        Double operacion = Math.pow(numeroCliente, 2);
        
        DataOutputStream flujoDatosEscritura =
                new DataOutputStream(
                        cliente.getOutputStream());
        
        flujoDatosEscritura.writeDouble(operacion);
        

        cliente.close();
    }
        
}
