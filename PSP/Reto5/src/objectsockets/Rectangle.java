/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectsockets;

import java.io.Serializable;

/**
 *
 * @author marco
 */
public class Rectangle implements Serializable {
    private double lado1;
    private double lado2;
    private double area;
    private double perimeter;

    public Rectangle(double lado1, double lado2){
        this.lado1=lado1;
        this.lado2=lado2;
    }
    
    public void calculate(){
        area = lado1 * lado2;
        perimeter = lado1 * 2 + lado2 * 2;
    }

    @Override
    public String toString() {
        return "El área del rectángulo es: "+area+" y el perímetro es:"+perimeter;
    }
    
}
