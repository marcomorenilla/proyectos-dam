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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class JDBCT2 {

    //Constantes de conexión

    /**
     * Url de conexión
     */
    public static final String URL = System.getenv("DOCKER_AD_URL");

    /**
     *Usuario de conexión
     */
    public static final String USER = System.getenv("DOCKER_AD_USER");

    /**
     *Password de conexión
     */
    public static final String PASS = System.getenv("DOCKER_AD_PASS");

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Bienvenido!");
        Scanner scn = new Scanner(System.in);
        String libro;
        String ISBN;
        String numeroEjemplares;
        String autor;
        String editorial;
        String tema;

        System.out.println("Introduce el nombre del libro para la consulta: ");
        libro = scn.nextLine();
        System.out.println("");

        //Conexión y lógica del programa en el main
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);) {

            System.out.println("Mostrando datos...");
            System.out.println("");
            if (existeLibro(con, libro)) {
                visualizarLibro(con, libro);
            } else {
                System.out.println("Introduce ISBN:");
                ISBN = scn.nextLine();
                System.out.println("Introduce Autor:");
                autor = scn.nextLine();
                System.out.println("Introduce editorial:");
                editorial = scn.nextLine();
                System.out.println("Introduce tema:");
                tema = scn.nextLine();
                System.out.println("Introduce número de ejemplares:");
                numeroEjemplares = scn.nextLine();
                insertarLibro(con, ISBN, libro, numeroEjemplares, autor, editorial, tema);
                visualizarLibro(con, libro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCT2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para buscar libros en la BBDD y mostrar los datos
     *
     * @param con
     * @param libro
     * @throws java.sql.SQLException
     */
    public static void visualizarLibro(Connection con, String libro) throws SQLException {

        String sqlSearch = String.format("SELECT ISBN, Titulo, NombreAutor, NombreEditorial FROM Libro NATURAL JOIN (Autor, Tema, Editorial) WHERE Titulo = '%s'; ", libro);

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sqlSearch);
        while (result.next()) {

            String ISBN = result.getString("ISBN");
            String titulo = result.getString("Titulo");
            String nombreAutor = result.getString("NombreAutor");
            String nombreEditorial = result.getString("NombreEditorial");
            System.out.println("ISBN: " + ISBN);
            System.out.println("Titulo: " + titulo);
            System.out.println("nombreAutor: " + nombreAutor);
            System.out.println("nombreEditorial: " + nombreEditorial);

        }

    }

    /**
     * Método que se encarga de la inserción del libro en caso que no exista,
     * depende de métodos:
     *
     * @see existeAutor
     * @see existeEditorial
     * @see existeTema
     * @param con
     * @param ISBN
     * @param titulo
     * @param numeroEjemplares
     * @param nombreAutor
     * @param editorial
     * @param tema
     * @throws SQLException
     */
    public static void insertarLibro(Connection con, String ISBN, String titulo,
            String numeroEjemplares, String nombreAutor, String editorial, String tema) throws SQLException {
        String sql = "INSERT INTO Libro(ISBN,Titulo,NumeroEjemplares,idAutor,idEditorial,idTema) VALUES(?,?,?,?,?,?);";
        PreparedStatement insertStatement = con.prepareStatement(sql);

        int idAutor = existeAutor(con, nombreAutor);
        int idEditorial = existeEditorial(con, editorial);
        int idTema = existeTema(con, tema);
        //TODO rellenar el statement
        insertStatement.setString(1, ISBN);
        insertStatement.setString(2, titulo);
        insertStatement.setString(3, numeroEjemplares);
        insertStatement.setInt(4, idAutor);
        insertStatement.setInt(5, idEditorial);
        insertStatement.setInt(6, idTema);
        insertStatement.executeUpdate();

        System.out.println("Libro insertado correctamente");
        System.out.println("");
    }

    /**
     * Método para comprobar si existe un libro y debemos mostrar resultados o
     * si no existe insertarlo.
     *
     * @param con
     * @param libro
     * @return Boolean
     * @throws SQLException
     */
    public static Boolean existeLibro(Connection con, String libro) throws SQLException {
        String selectLibro = String.format("SELECT * FROM Libro WHERE Titulo = '%s';", libro);
        ResultSet result = con.createStatement().executeQuery(selectLibro);
        return result.next();
    }

    /**
     * Método para comprobar si existe Autor, en caso que no exista lo inserta.
     *
     * @param con
     * @param nombre
     * @return idAutor: Integer
     * @throws SQLException
     */
    public static int existeAutor(Connection con, String nombre) throws SQLException {
        int idAutor = 0;

        String sqlInsert = "INSERT INTO Autor (NombreAutor) VALUES (?);";
        String sqlAutor = String.format("SELECT * FROM Autor WHERE NombreAutor = '%s';", nombre);
        PreparedStatement prepStatement = con.prepareStatement(sqlInsert);

        Statement statement = con.createStatement();

        ResultSet result = statement.executeQuery(sqlAutor);

        while (result.next()) {
            idAutor = result.getInt("idAutor");
        }

        if (idAutor == 0) {
            System.out.println("idAutor no encontrada, insertando...");
            prepStatement.setString(1, nombre);
            prepStatement.executeUpdate();

            result = statement.executeQuery(sqlAutor);
            while (result.next()) {
                idAutor = result.getInt("idAutor");
            }
            System.out.println("Autor insertado correctamente");

        }
        return idAutor;
    }

    /**
     * Método para comprobar si existe la editorial, en caso que no exista la
     * inserta.
     *
     *
     * @param con
     * @param editorial
     * @return idEditorial: Integer
     * @throws SQLException
     */
    public static int existeEditorial(Connection con, String editorial) throws SQLException {
        Scanner scn = new Scanner(System.in);
        int idEditorial = 0;
        String sqlInsert = "INSERT INTO Editorial(NombreEditorial,Direccion,Telefono)VALUES (?,?,?);";
        String sqlBusqueda = String.format("SELECT * FROM Editorial WHERE NombreEditorial = '%s';", editorial);

        PreparedStatement insertStatement = con.prepareStatement(sqlInsert);
        Statement selectStatement = con.createStatement();

        ResultSet result = selectStatement.executeQuery(sqlBusqueda);
        while (result.next()) {
            idEditorial = result.getInt("idEditorial");
        }

        if (idEditorial == 0) {
            System.out.println("idEditorial no encontrado, insertando...");

            //Preguntamos datos para insertar editorial.
            System.out.println("Introduce dirección de editorial...");
            String editorialDir = scn.nextLine();
            System.out.println("Introduce teléfono de la editorial");
            String editorialTlf = scn.nextLine();
            insertStatement.setString(1, editorial);
            insertStatement.setString(2, editorialDir);
            insertStatement.setString(3, editorialTlf);
            insertStatement.executeUpdate();

            System.out.println("Inserción realizada correctamente");

            result = selectStatement.executeQuery(sqlBusqueda);
            while (result.next()) {
                idEditorial = result.getInt("idEditorial");
            }

        }

        return idEditorial;
    }

    /**
     * Método para comprobar si existe tema, en caso que no exista lo inserta.
     *
     * @param con
     * @param tema
     * @return idTema: Integer
     * @throws SQLException
     */
    public static int existeTema(Connection con, String tema) throws SQLException {
        int idTema = 0;

        String sqlSelect = String.format("SELECT * FROM Tema WHERE NombreTema LIKE '%s%%';", tema);
        String sqlInsert = "INSERT INTO Tema(NombreTema) VALUES (?);";

        Statement selectStatement = con.createStatement();
        PreparedStatement insertStatement = con.prepareStatement(sqlInsert);

        ResultSet result = selectStatement.executeQuery(sqlSelect);

        while (result.next()) {
            idTema = result.getInt("idTema");
        }

        if (idTema == 0) {
            System.out.println("Tema no encontrado, insertando...");
            insertStatement.setString(1, tema);
            insertStatement.executeLargeUpdate();
            System.out.println("Tema insertado correctamente");

            result = selectStatement.executeQuery(sqlSelect);
            while (result.next()) {
                idTema = result.getInt("idTema");
            }

        }

        return idTema;
    }

}
