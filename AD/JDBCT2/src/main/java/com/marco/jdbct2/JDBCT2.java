/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.marco.jdbct2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class JDBCT2 {

    public static void main(String[] args) {
        String url = System.getenv("DOCKER_AD_URL");
        String user = System.getenv("DOCKER_AD_USER");
        String password = System.getenv("DOCKER_AD_PASS");
        System.out.println(url+" "+user+" "+password);
        try (Connection con = DriverManager.getConnection(url, user, password)){
            System.out.println("Conectado en docker mysql");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCT2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
