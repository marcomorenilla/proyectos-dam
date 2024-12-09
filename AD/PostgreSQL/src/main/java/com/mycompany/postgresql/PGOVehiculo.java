/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.postgresql;

import java.sql.SQLException;
import org.postgresql.util.PGobject;

/**
 *
 * @author Marco
 */
public class PGOVehiculo extends PGobject{
    
    private Vehiculo vehiculo;

    public PGOVehiculo() {
    }
    
    @Override
    public void setValue(String value) throws SQLException{
        super.setValue(value);
        if(value!=null){
            String [] parte = value.replace("(", "").replace(")", "").split(",");
            vehiculo = new Vehiculo(parte[0], parte[1]);
        }        
    }
    
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
}
