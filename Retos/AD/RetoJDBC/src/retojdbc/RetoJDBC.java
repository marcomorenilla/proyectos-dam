/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Marco
 */
public class RetoJDBC {

    public static final String URL = "jdbc:mysql://localhost/biblioteca";
    public static final String USER = "user-biblioteca";
    public static final String PASS = "ChuckNorris-2024";

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String libro;
        String ISBN;
        String numeroEjemplares;
        String autor;
        String editorial;
        String tema;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);) {
            System.out.println("Conexión exitosa");
            System.out.println("");
            System.out.println("Bienvenido a la base de datos de la biblioteca");
            System.out.println("Introduce el nombre del libro para la consulta: ");
            libro = scn.nextLine();
            System.out.println("");

            System.out.println("");
            if (existeLibro(con, libro)) {
                visualizarLibro(con, libro);
            } else {
                System.out.println("Ups! Parece que el libro que buscas no se encuentra en la base de dados.");
                System.out.println("Por favor rellena los datos para insertarlo:");
                System.out.println("");
                System.out.println("Introduce ISBN:");
                ISBN = scn.nextLine();
                System.out.println("");
                System.out.println("Introduce Autor:");
                autor = scn.nextLine();
                System.out.println("");
                System.out.println("Introduce editorial:");
                editorial = scn.nextLine();
                System.out.println("");
                System.out.println("Introduce tema:");
                tema = scn.nextLine();
                System.out.println("");
                System.out.println("Introduce número de ejemplares:");
                numeroEjemplares = scn.nextLine();
                System.out.println("");
                insertarLibro(con, ISBN, libro, numeroEjemplares, autor, editorial, tema);
                visualizarLibro(con, libro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RetoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void visualizarLibro(Connection con, String libro) throws SQLException {

        String sqlSearch = String.format("SELECT ISBN, Titulo, NombreAutor, NombreEditorial FROM Libro NATURAL JOIN (Autor, Tema, Editorial) WHERE Titulo = '%s'; ", libro);

        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sqlSearch);
        if (result.next()) {

            String ISBN = result.getString("ISBN");
            String titulo = result.getString("Titulo");
            String nombreAutor = result.getString("NombreAutor");
            String nombreEditorial = result.getString("NombreEditorial");

            System.out.println("Mostrando datos");
            System.out.println("_____________________________________");
            System.out.println("ISBN: " + ISBN);
            System.out.println("Titulo: " + titulo);
            System.out.println("nombreAutor: " + nombreAutor);
            System.out.println("nombreEditorial: " + nombreEditorial);
            System.out.println("_____________________________________");

        }

    }

    public static Boolean existeLibro(Connection con, String libro) throws SQLException {
        String selectLibro = String.format("SELECT * FROM Libro WHERE Titulo = '%s';", libro);
        ResultSet result = con.createStatement().executeQuery(selectLibro);
        return result.next();
    }

    public static void insertarLibro(Connection con, String ISBN, String titulo,
            String numeroEjemplares, String nombreAutor, String editorial, String tema) throws SQLException {

        String sql = "INSERT INTO Libro(ISBN,Titulo,NumeroEjemplares,idAutor,idEditorial,idTema) VALUES(?,?,?,?,?,?);";
        PreparedStatement insertStatement = con.prepareStatement(sql);

        int idAutor = existeAutor(con, nombreAutor);
        int idEditorial = existeEditorial(con, editorial);
        int idTema = existeTema(con, tema);

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

    public static int existeAutor(Connection con, String nombre) throws SQLException {
        int idAutor = 0;

        String sqlInsert = "INSERT INTO Autor (NombreAutor) VALUES (?);";
        String sqlAutor = String.format("SELECT * FROM Autor WHERE NombreAutor = '%s';", nombre);
        PreparedStatement prepStatement = con.prepareStatement(sqlInsert);

        Statement statement = con.createStatement();

        ResultSet result = statement.executeQuery(sqlAutor);

        if (result.next()) {
            idAutor = result.getInt("idAutor");
        }

        if (idAutor == 0) {
            System.out.println("idAutor no encontrada, insertando...");
            prepStatement.setString(1, nombre);
            prepStatement.executeUpdate();

            result = statement.executeQuery(sqlAutor);
            if (result.next()) {
                idAutor = result.getInt("idAutor");
            }
            System.out.println("Autor insertado correctamente");
            System.out.println("");

        }
        return idAutor;
    }
   

    public static int existeEditorial(Connection con, String editorial) throws SQLException {
        Scanner scn = new Scanner(System.in);
        int idEditorial = 0;
        
        String insercion = "{call insertar_editorial(?,?,?)}";
        String sqlBusqueda = String.format("SELECT * FROM Editorial WHERE NombreEditorial = '%s';", editorial);
        CallableStatement insert = con.prepareCall(insercion);
        
        Statement selectStatement = con.createStatement();

        ResultSet result = selectStatement.executeQuery(sqlBusqueda);
        if (result.next()) {
            idEditorial = result.getInt("idEditorial");
        }

        if (idEditorial == 0) {

            System.out.println("idEditorial no encontrado, insertando...");

            System.out.println("Introduce dirección de la editorial: ");
            String direccion = scn.nextLine();
            System.out.println("");
            System.out.println("Introduce el teléfono de la editorial sin espacios: ");
            String telefono = scn.next();
            System.out.println("");

            insert.setString(1, editorial);
            insert.setString(2, direccion);
            insert.setString(3, telefono);

            insert.execute();

            System.out.println("Inserción realizada correctamente");
            System.out.println("");

            result = selectStatement.executeQuery(sqlBusqueda);
            if (result.next()) {
                idEditorial = result.getInt("idEditorial");
            }

        }

        return idEditorial;
    }

    public static int existeTema(Connection con, String tema) throws SQLException {
        int idTema = 0;

        String sqlSelect = String.format("SELECT * FROM Tema WHERE NombreTema LIKE '%s%%';", tema);
        String sqlInsert = "INSERT INTO Tema(NombreTema) VALUES (?);";

        Statement selectStatement = con.createStatement();
        PreparedStatement insertStatement = con.prepareStatement(sqlInsert);

        ResultSet result = selectStatement.executeQuery(sqlSelect);

        if (result.next()) {
            idTema = result.getInt("idTema");
        }

        if (idTema == 0) {
            System.out.println("Tema no encontrado, insertando...");
            insertStatement.setString(1, tema);
            insertStatement.executeLargeUpdate();
            System.out.println("Tema insertado correctamente");
            System.out.println("");

            result = selectStatement.executeQuery(sqlSelect);
            if (result.next()) {
                idTema = result.getInt("idTema");
            }

        }

        return idTema;
    }

}
