/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectsockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class Client {

    public static void main(String[] args) {
        try (Socket cliente = new Socket("localhost", 6000); 
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream()); 
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream()); 
                Scanner scn = new Scanner(System.in);) {
            // Creamos array para almacenar datos
            double[] lados = new double[2];

            //Introducimos datos del rectángulo
            System.out.println("Introduce el lado 1: ");
            lados[0] = scn.nextDouble();
            System.out.println("Introduce el lado 2: ");
            lados[1] = scn.nextDouble();

            // Instanciams la clase rectángulo con los datos leídos.
            Rectangle r = new Rectangle(lados[0], lados[1]);

            // Enviamos y recibimos objeto a la clase Server
            salida.writeObject(r);
            r = (Rectangle) entrada.readObject();

            // Imprimimos datos por consola
            System.out.println(r);

        } catch (IOException e) {
            System.out.println("Error en la entrada y salida de datos");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase Rectangle");
            e.printStackTrace();
        }

    }

}
