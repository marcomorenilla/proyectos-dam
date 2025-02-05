/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FilosofosConSemaforos;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FP
 */
public class Philosopher extends Thread {

    private Semaphore leftFork, rightFork;
    private int id;

    public Philosopher( int id, Semaphore leftFork, Semaphore rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }

    public void run() {

        try {
            while(true){
            //Filosofo piensa
            System.out.println("Filósofo " + id + " está pensando.");
            //Filosofo intenta adquirir el tenedor izquierdo
            leftFork.acquire();
            System.out.println("Filosofo " + id + " ha tomado el tenedor izquierdo.");
            //Filosofo intenta adquirir el tenedor derecho
            rightFork.acquire();
            System.out.println("Filosofo " + id + " ha tomado el tenedor derecho.");
            //filosofo come
            System.out.println("Filosofo " + id + " está comiendo.");
            leftFork.release();
            //filosofo suelta el tenedor izquierdo
            System.out.println("Filosofo " + id + " ha soltado el tenedor izquierdo");
            rightFork.release();
            //filosofo suelta el tenedor derecho
            System.out.println("Filosofo " + id + " ha soltado el tenedor derecho");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
