/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PGobject;
import static utils.StringUtil.PASS;
import static utils.StringUtil.URL;
import static utils.StringUtil.USER;

/**
 *
 * @author Marco
 */
public class PostgreSQL {

    public static void main(String[] args) {
        //Sustituir estas constantes con tus datos de conexión
       try(Connection con = DriverManager.getConnection(URL, USER, PASS);
               Statement stmnt = con.createStatement();){
           
           
           //Lectura de tabla con PGObject
           String sql = "SELECT id, info FROM vehiculos";
           ResultSet res = stmnt.executeQuery(sql);
           while(res.next()){
               int id = res.getInt("id");
               
               // Obtenemos el objeto de la tabla padre
               PGobject objetoPG = (PGobject) res.getObject("info");
               
               System.out.println("ID: "+id);
               
               //Método getValue nos devuelve el String con información del objeto
               System.out.println("Info. vehículo: "+objetoPG.getValue());
               
           }
           
           //Lectura implementando la clase PGOVehiculo
           res = stmnt.executeQuery(sql);
           while(res.next()){
               // Cogemos el PGobject del resultado
               PGobject objetoPG = (PGobject) res.getObject("info");
               
               //Creamos un objeto de la clase que extiende el PGobject
               PGOVehiculo vehiculoPG = new PGOVehiculo();
               
               /**Del PGObject el método .getValue() devuelve un String 
                * que utilizamos para inicializar la clase y crear una instancia
                * de Vehiculo a su vez
                */ 
               vehiculoPG.setValue(objetoPG.getValue());
               
               
               /**Obtenemos la instancia del Vehículo creado
                * a través del método getVehiculo();
                */
               Vehiculo vehiculo = vehiculoPG.getVehiculo();
               System.out.println("");
               System.out.println("------------ Vehiculo: ------");
               System.out.println(vehiculo);
               
               
           }
           
       } catch (SQLException ex) { 
            Logger.getLogger(PostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algo falló en la conexión.");
            System.out.println("Causas más frecuentes:");
            System.out.println("1.- Asegúrate que la BBDD exista y esté accesible");
            System.out.println("2.- Comprueba que el usuario exista");
            System.out.println("3.- Comprueba que la contraseña sea correcta");
            System.out.println("4.- Asegúrate de estar utilizando el driver de conexión adecuado");
        } 
    }
}
