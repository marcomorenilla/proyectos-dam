/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectsockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author marco
 */
public class Server {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(6000); 
                Socket cliente = server.accept(); 
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream()); 
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                ) {

            // Leemos el objeto enviado por el cliente en una instancia de Rectangle en el servidor
            Rectangle r = (Rectangle) entrada.readObject();

            // Calculamos el área y perímetro del rectángulo.
            r.calculate();

            // Mandamos objeto al cliente con los datos calculados.
            salida.writeObject(r);

        } catch (IOException e) {
            System.out.println("Error en la entrada y salida de datos");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase Rectangle");
            e.printStackTrace();
        }
    }

}
