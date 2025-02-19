
package ExplicacionTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5233);
        System.out.println("Esperando conexiones...");
        Socket cualquierCliente = servidor.accept();
        
        System.out.println("Servidor cerrado");
        
    }
}
