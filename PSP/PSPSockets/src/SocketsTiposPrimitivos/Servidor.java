

package SocketsTiposPrimitivos;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = 
                new ServerSocket(4567);
        System.out.println("Soy el SERVIDOR");
        Socket cliente = servidor.accept();
        //parado hasta recibir un cliente      
        //Trabajo con datos primitivos.. lectura
        DataInputStream flujoDatosLectura = 
                new DataInputStream(
                        cliente.getInputStream());
        
        System.out.println("El cliente escribe: "
        + flujoDatosLectura.readUTF());
        
        DataOutputStream flujoDatosEscritura =
                new DataOutputStream(
                        cliente.getOutputStream());
        
        flujoDatosEscritura.writeUTF("Soy el server: "
                + ", encantando cliente");
        

        cliente.close();
    }
}
