/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extraprogramacion.ahorcado;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Marco
 */
public class Main {
    public static void main(String[] args) {
        
        /*
        Entrada:
            Usuario introduce letras
        
        Algoritmo:
            Adivinar la palabra en un número de intentos
        
        Todo esto se repite...
            Usuario introduce datos
            Compara la letra introducida con las letras contenidas en resultado
                Si está -> representarla para que el user la vea
                Si no está -> restamos intentos y pintar un brazo
        
         Hasta
            (Un número de intentos -> gana la máquina) o (Ganas el juego -> adivina la palabra)
        
        Salida:
            Has ganado
            Has perdido
                
        */
        
        //Creamos palabra
        Scanner teclado = new Scanner(System.in);
        String adivina = "murcielago";
        int intentos = 5;
        
        
        String[]usuario = new String[adivina.length()];
        String[]adivinaArray = new String[adivina.length()];
        
        for (int i = 0; i < adivina.length(); i++) {
            String letraString = String.valueOf(adivina.charAt(i));
            adivinaArray[i]=letraString;
        }
        
        do{
            System.out.println("Introduce una letra");
            String letra = teclado.next();
            
            //Cada cierto tiempo hacer un sout
            //Cuidado si el usuario introduce varias letras
            
            if(adivina.contains(letra)){
                System.out.println("La letra está en la palabra");
                //meter la letraen la palabra usuario
                for (int i = 0; i < adivina.length(); i++) {
                    char letraChar = adivina.charAt(i);
                    String letraString = String.valueOf(letraChar);
                    if (letraString.equalsIgnoreCase(letra)) {
                        usuario[i]=letraString;
                    }
                    System.out.println(Arrays.toString(usuario));
                }
            }else{
                System.out.println("No está la letra en la palabra");
                intentos--;
               
            }
        }while(intentos > 0 && !(Arrays.equals(adivinaArray,usuario)));//pensar el falso -> intentos<=0 || adivina == usuario
        
        if(intentos>0){
            System.out.println("Ganaste");
        } else{
            System.out.println("Has perdido");
        }
    }
}
