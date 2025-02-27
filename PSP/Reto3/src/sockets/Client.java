/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class Client {

    public static void main(String[] args){

        try (Socket server = new Socket("localhost", 6000); // Creamos socket del lado del cliente
                 DataOutputStream mensaje = new DataOutputStream(server.getOutputStream()); // Creamos flujo de salida
                 DataInputStream respuesta = new DataInputStream(server.getInputStream()); // Creamos flujo de entrada
                 Scanner scn = new Scanner(System.in);) {

            int teclado;
            String[] figuras = {"triángulo", "rectángulo"};
            String[] params = new String[2];
            double datos;

            // Limitamos las entradas para que no le llegue una figura al servidor que no sepa manejar
            do {
                System.out.println("Introduce el número correspondiente a la figura que quieres mandar: ");
                System.out.println("1. triángulo");
                System.out.println("2. rectángulo");
                teclado = scn.nextInt();

                if (teclado==1) {
                    params[0] = "base";
                    params[1] = "altura";
                } else if (teclado==2) {
                    params[0] = "lado 1";
                    params[1] = "lado 2";
                } else {
                    System.out.println("figura no válida");
                    System.out.println("Las únicas figuras válidas son: ");
                    System.out.println("1. triángulo");
                    System.out.println("2. triángulo");
                }

            } while (teclado < 1 || teclado > 2);
            
            // Le mandamos la figura al server y procesamos primera respuesta
            mensaje.writeInt(teclado);
            System.out.println("Server: " + respuesta.readUTF());
            teclado --;
            
            // Mandamos mensaje al server de confirmación que ha llegado su mensaje
            // Pedimos al usuario los datos por consola
            // Le mandamos los datos al server
            mensaje.writeUTF("Client: Te mando " + params[0] + " y " + params[1]);
            System.out.println("Introduce " + params[0]);
            datos = scn.nextDouble();
            mensaje.writeDouble(datos);
            System.out.println("Introduce " + params[1]);
            datos = scn.nextDouble();
            mensaje.writeDouble(datos);

            // Procesamos la respuesta final del servidor con los resultados de la operación
            System.out.println(respuesta.readUTF());
            System.out.println("Server: El área del " + figuras[teclado] + " es " + respuesta.readDouble());
            System.out.println("Server: El perímetro del " + figuras[teclado] + " es " + respuesta.readDouble());

        } catch(IOException e){
            System.out.println("Se produce un error de entrada salida de datos, sigue el mensaje para obtener más información");
            System.err.println(e);
        }

    }

}
