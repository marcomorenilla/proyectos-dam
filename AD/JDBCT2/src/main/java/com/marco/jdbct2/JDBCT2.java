/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.marco.jdbct2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class JDBCT2 {
    
    //Constantes de conexión
    
    public static final String url = System.getenv("DOCKER_AD_URL");
    public static final String user = System.getenv("DOCKER_AD_USER");
    public static final String password = System.getenv("DOCKER_AD_PASS");
    
    public static void main(String[] args) {

        System.out.println(url + " " + user + " " + password);
        //Conexión y lógica del programa en el main
        try (Connection con = DriverManager.getConnection(url, user, password);) {
            System.out.println("Conectado en docker mysql");
            
            //Ver javadoc 
            visualizarLibros(con);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCT2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método para buscar libros en la BBDD
     * @param con 
     */
    public static void visualizarLibros(Connection con) {

        String sqlSearch = "SELECT ISBN, Titulo, NombreAutor, NombreEditorial FROM `Libro` NATURAL JOIN (Autor, Tema, Editorial); ";
        int cont = 1;
        
        try (Statement statement = con.createStatement();) {
            ResultSet result = statement.executeQuery(sqlSearch);
            while (result.next()) {

                System.out.println("Libro número: " + cont);
                String ISBN = result.getNString("ISBN");
                String titulo = result.getNString("Titulo");
                String nombreAutor = result.getNString("NombreAutor");

                System.out.println("ISBN: " + ISBN);
                System.out.println("Titulo: " + titulo);
                System.out.println("nombreAutor: " + nombreAutor);
                cont++;
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCT2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
