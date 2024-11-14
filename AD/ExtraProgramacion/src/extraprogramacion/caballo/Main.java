/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extraprogramacion.caballo;

/**
 *
 * @author Marco
 */
public class Main {

    public static void main(String[] args) {
        /* El usuario mueve el cabello por el tablero
        
        tablero es una matriz, array dos dimensiones
        tablero 8*8
        
            A B C D E F G H     
         1  - - - - - - - -
         2  - - - 3 - 2 - -
         3  - - 4 - - - 1 -
         4  - - - - C - - -
         5  - - 5 - - - 8 -
         6  - - - 6 - 7 - -
         7  - - - - - - - -
         8  - - - - - - - -

          tablero[fila][columna]="C"
          tablero[3][4] = "C"

          revisar

          1: tablero[fila-1][columna+2]
          2: tablero[fila-2][columna+1]
          3: tablero[fila-2][columna-1]
          4: tablero[fila-1][columna-2]
          5: tablero[fila+1][columna-2]
          6: tablero[fila+2][columna-1]
          7: tablero[fila+2][columna+1]
          8: tablero[fila+1][columna+2]
        
         */

        String[][] tablero = new String[8][8];
        String caballo = "C";

        inicializarTablero(tablero);
        tablero[3][4] = caballo;
        //visualizarTablero(tablero);
        generarMovimiento(tablero, 3, 4);

    }

    private static void visualizarTablero(String[][] tablero) {

        System.out.println("===============");
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println("");

        }

    }

    private static void inicializarTablero(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "-";
            }

        }
    }

    private static void generarMovimiento(String[][] tablero, int fila, int columna) {
        tablero[fila - 1][columna + 2] = "1";
        tablero[fila - 2][columna + 1] = "2";
        tablero[fila - 2][columna - 1] = "3";
        tablero[fila - 1][columna - 2] = "4";
        tablero[fila + 1][columna - 2] = "5";
        tablero[fila + 2][columna - 1] = "6";
        tablero[fila + 2][columna + 1] = "7";
        tablero[fila + 1][columna + 2] = "8";
        visualizarTablero(tablero);

    }

}
