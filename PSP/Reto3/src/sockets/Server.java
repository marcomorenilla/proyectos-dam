/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author marco
 */
public class Server {

    public static void main(String[] args){

        try (ServerSocket server = new ServerSocket(6000); // Creamos ServerSocket
                 Socket cliente = server.accept(); //Aceptamos comunicación de clientes
                 DataInputStream entrada = new DataInputStream(cliente.getInputStream()); //Creamos flujo de entrada
                 DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());//Creamos flujo de salida
                ) {
            
            // Recibimoa figura del cliente
            int mensaje = entrada.readInt();
            String[] figuras = {"triángulo", "rectángulo"};
            String[] params = new String[2];
            double[] datos = new double[3];

            // Adecuamos vocabulario a la figura recibida
            if (mensaje==1) {
                params[0] = "base";
                params[1] = "altura";
            } else{
                params[0] = "lado 1";
                params[1] = " lado 2";
            }
            
            mensaje--;
            
            // Pedimos al cliente los datos para realizar los cálculos
            System.out.println("Server: Eres un " + figuras[mensaje]);
            salida.writeUTF("Mándame " +params[0]+" y " + params[1]);
            System.out.println(entrada.readUTF());
            datos[0] = entrada.readDouble();
            datos[1] = entrada.readDouble();
            
            // Mandamos mensaje de confirmación que hemos recibido datos
            salida.writeUTF("Server: Te devuelvo el área y perímetro");
            
            // Utilizamos la fórmula que se adecue al cliente y respondemos con área y perímetro
            if(figuras[mensaje].equals("triangle")){
                datos[2] = datos[0] * datos[1] /2;
                salida.writeDouble(datos[2]);
                datos[2] = datos[0] * 3;
                salida.writeDouble(datos[2]);
            } else {
                datos[2] = datos[0] * datos[1];
                salida.writeDouble(datos[2]);
                datos[2] = datos[0] * 2 + datos[1] * 2;
                salida.writeDouble(datos[2]);
            }
            
            // Nos despedidmos en la consola del servidor
            System.out.println("Server: Espero que le saques partido y nos veamos pronto");
            

        } catch(IOException e){
            System.out.println("Se produce un error de entrada salida de datos, sigue el mensaje para obtener más información");
            System.err.println(e);
        }
    }
}
