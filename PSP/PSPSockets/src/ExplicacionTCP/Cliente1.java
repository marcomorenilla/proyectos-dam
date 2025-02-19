/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ExplicacionTCP;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author damiansualdea
 */
public class Cliente1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Vamos a conectarnos");
        Socket servidor = 
                new Socket("localhost",5233 );
        System.out.println("Cliente cerrado");
    }
}
