/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.marco.jdbct2;

import com.google.protobuf.StringValue;
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
    public static final String URL = System.getenv("DOCKER_AD_URL");
    public static final String USER = System.getenv("DOCKER_AD_USER");
    public static final String PASS = System.getenv("DOCKER_AD_PASS");

    public static void main(String[] args) {

        System.out.println(URL + " " + USER + " " + PASS);
        //Conexión y lógica del programa en el main
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);) {
            System.out.println("Conectado en docker mysql");

            //Ver javadoc 
            //visualizarLibros(con);
            existeAutor(con, "Fer");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCT2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para buscar libros en la BBDD
     *
     * @param con
     */
    public static void visualizarLibros(Connection con) throws SQLException {

        String sqlSearch = "SELECT ISBN, Titulo, NombreAutor, NombreEditorial FROM `Libro` NATURAL JOIN (Autor, Tema, Editorial); ";
        int cont = 1;

        Statement statement = con.createStatement();
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
    }

    public static void insertarLibro(Connection con) throws SQLException {
        String sql = "INSERT INTO 'Libro'(ISBN,Titulo,NumeroEjemplares, idAutor,idEditorial,idTema)"
                + " VALUES(?,?,?,?,?,?);";
        PreparedStatement statement = con.prepareStatement(sql);

        //TODO Recuperar datos para insertar libro
        String ISBN = "";
        String Titulo = "";
        String NumeroEjemplares = "";
        int idAutor;
        String idEditorial = "";
        String idTema = "";

    }

    public static int existeAutor(Connection con, String nombre) throws SQLException {
        int idAutor=0;
        

        Statement statement = con.createStatement();
        String sqlAutor = String.format("SELECT * FROM Autor WHERE NombreAutor LIKE '%s%%';", nombre);

        ResultSet result = statement.executeQuery(sqlAutor);

            while (result.next()) {
                System.out.println("Autor: " + result.getNString("NombreAutor"));
                idAutor =result.getInt("idAutor");
                System.out.println("IdAutor: "+idAutor);
            }
            
            if(idAutor==0){
                insertarAutor(con,nombre);
            }
            
            
            
        return idAutor;
    }

    private static void insertarAutor(Connection con, String nombre) {
       
    }
}
