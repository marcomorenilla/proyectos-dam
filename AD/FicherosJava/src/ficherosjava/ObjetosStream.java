/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ficherosjava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Marco
 */
public class ObjetosStream {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        ObjectOutputStream empleadoSalida = new ObjectOutputStream(new FileOutputStream("empresa.data"));
        Empleado e = new Empleado("antonio", "morenilla", 33, 3100);
        Empleado e2 = new Empleado("roberto", "alonso", 25, 3100);
        
        empleadoSalida.writeObject(e);
        empleadoSalida.close();
        
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("empresa.data"));
     
        
        Empleado emp =  (Empleado) ois.readObject();
        System.out.println(emp);
    }
}
