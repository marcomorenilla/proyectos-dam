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
            String[] figuras = {"tri�ngulo", "rect�ngulo"};
            String[] params = new String[2];
            double datos;

            // Limitamos las entradas para que no le llegue una figura al servidor que no sepa manejar
            do {
                System.out.println("Introduce el n�mero correspondiente a la figura que quieres mandar: ");
                System.out.println("1. tri�ngulo");
                System.out.println("2. rect�ngulo");
                teclado = scn.nextInt();

                if (teclado==1) {
                    params[0] = "base";
                    params[1] = "altura";
                } else if (teclado==2) {
                    params[0] = "lado 1";
                    params[1] = "lado 2";
                } else {
                    System.out.println("figura no v�lida");
                    System.out.println("Las �nicas figuras v�lidas son: ");
                    System.out.println("1. tri�ngulo");
                    System.out.println("2. tri�ngulo");
                }

            } while (teclado < 1 || teclado > 2);
            
            // Le mandamos la figura al server y procesamos primera respuesta
            mensaje.writeInt(teclado);
            System.out.println("Server: " + respuesta.readUTF());
            teclado --;
            
            // Mandamos mensaje al server de confirmaci�n que ha llegado su mensaje
            // Pedimos al usuario los datos por consola
            // Le mandamos los datos al server
            mensaje.writeUTF("Client: Te mando " + params[0] + " y " + params[1]);
            System.out.println("Introduce " + params[0]);
            datos = scn.nextDouble();
            mensaje.writeDouble(datos);
            System.out.println("Introduce " + params[1]);
            datos = scn.nextDouble();
            mensaje.writeDouble(datos);

            // Procesamos la respuesta final del servidor con los resultados de la operaci�n
            System.out.println(respuesta.readUTF());
            System.out.println("Server: El �rea del " + figuras[teclado] + " es " + respuesta.readDouble());
            System.out.println("Server: El per�metro del " + figuras[teclado] + " es " + respuesta.readDouble());

        } catch(IOException e){
            System.out.println("Se produce un error de entrada salida de datos, sigue el mensaje para obtener m�s informaci�n");
            System.err.println(e);
        }

    }

}
