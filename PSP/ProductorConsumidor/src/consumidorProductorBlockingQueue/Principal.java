/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consumidorProductorBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author FP
 */
public class Principal {

    public static void main(String[] args) {
        BlockingQueue<Integer> colaCompartida = new ArrayBlockingQueue<Integer>(3);
        Thread p = new Thread(new Productor(colaCompartida));
        Thread c = new Thread(new Consumidor(colaCompartida));
        p.start();
        c.start();

    }

}
