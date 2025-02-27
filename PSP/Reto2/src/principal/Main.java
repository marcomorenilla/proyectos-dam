package principal;

import threads.Sumatorio;

import java.util.Timer;

public class Main {

    public static void main(String[] args) {

        // Utilizamos una variable tiempo  para medir el tiempo de ejecución del programa.
        Long time = System.currentTimeMillis()/1000 ;
        System.out.println("Hora de empezar con el time: " + (System.currentTimeMillis()/1000 - time)  + " segundos");

        // Creamos tres instancias de la clase Sumatorio que extiende de la clase Thread
        Sumatorio s1 = new Sumatorio(23);
        Sumatorio s2 = new Sumatorio(13);
        Sumatorio s3 = new Sumatorio(15);

        // Damos nombre a cada instancia para poder imprimir por consola en lugar del nombre genérico
        s1.setName("s1");
        s2.setName("s2");
        s3.setName("s3");

        // Lanzamos los hilos con .start()
        s1.start();
        s2.start();
        s3.start();

        try {

            // Utilizamos .join()  para detener la  ejecución del main hasta que los hilos finalicen su cometido
            s1.join();
            s2.join();
            s3.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Realizamos la fórmula obteniendo el resultado almacenado en cada hilo
        double suma =  s1.getResultado() + s2.getResultado() + s3.getResultado();
        double formula = suma/8;

        // Imprimimos el resultado junto con el tiempo que ha tardado en ejecutarse el programa
        System.out.println("El resultado de la fórmula es: " + formula + " y he tardado : " + (System.currentTimeMillis()/1000  - time) + " segundos");
    }
}
