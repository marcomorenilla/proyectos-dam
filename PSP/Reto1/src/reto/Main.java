package reto;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
       //Creamos objeto Scanner y declaramos variables
        Scanner scn = new Scanner(System.in);
        String path;
        int seconds;

        // Pedimos datos por consola y se los asignamos a las variables
        System.out.println("Introduce la ruta de un ejecutable .exe: ");
        path = scn.nextLine();
        System.out.println("Introduce el número de segundos para que se inicie el proceso: ");
        seconds = scn.nextInt();

        //Instanciamos un objeto de tipo ProcessBuilder con la ruta al ejecutable
        ProcessBuilder pb = new ProcessBuilder(path);

        //Esperamos el tiempo introducido por pantalla y lanzamos el ejecutable
        Thread.sleep(seconds * 1000);
        Process p = pb.start();
    }
}
