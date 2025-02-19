

package SocketsTiposPrimitivos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket servidor = 
                new Socket("localhost", 4567);
        System.out.println("SOY EL CLIENTE");
        DataOutputStream flujoDatosEscritura =
                new DataOutputStream(
                        servidor.getOutputStream());
        
        flujoDatosEscritura.writeUTF("Holaaaaaaaaaaa");
        
        
        DataInputStream flujoDatosLectura =
                new DataInputStream(
                        servidor.getInputStream());
        
        System.out.println("El Servidor responde: "
        + flujoDatosLectura.readUTF());
        
        
        
        
        
        servidor.close();
    }
}

