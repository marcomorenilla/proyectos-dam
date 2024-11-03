/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.marco.ejercicioxmljson;

import com.marco.ejercicioxmljson.controllers.JSONController;
import com.marco.ejercicioxmljson.controllers.XMLController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import com.marco.ejercicioxmljson.models.Carrera;
import com.marco.ejercicioxmljson.models.Ciclista;
import com.marco.ejercicioxmljson.models.Etapa;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Marco
 */
public class EjercicioXMLJSON {

    public static void main(String[] args) {

        XMLController xml = new XMLController();
        /*Datos creados para escribir el documento:
        
        Ciclista ciclista = new Ciclista(1, "Marco", "Kern Pharma", "04:15:20");
        Ciclista ciclista2 = new Ciclista(2, "Tadej Pogacar", "UAE", "04:16:30");
        Ciclista ciclista3 = new Ciclista(3, "Jonas Vingegard", "Jumbo Visma", "04:17:30");

        Etapa etapa = new Etapa(1);
        Etapa etapa2 = new Etapa(2);
        Carrera carrera = new Carrera("vueltaEspana");

        etapa.addCiclista(ciclista);
        etapa.addCiclista(ciclista2);
        etapa.addCiclista(ciclista3);

        etapa2.addCiclista(ciclista);
        etapa2.addCiclista(ciclista3);
        etapa2.addCiclista(ciclista2);

        carrera.addEtapa(etapa);
        carrera.addEtapa(etapa2);

        System.out.println("Podio de la primera etapa de: "+ carrera.getNombre());
        carrera.getEtapas().get(0).mostrarPodio();
        
        // Escritura del documento
        try {
            xml.XMLWriteDoc(carrera, "LaVuelta.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         */

        //Recuperación de objetos del documento y mostrar por pantalla
        Carrera carreraRec = xml.XMLReadDoc("LaVuelta.xml");
        for (Etapa etapaRec : carreraRec.getEtapas()) {
            System.out.println("Podio de la etapa " + etapaRec.getNumero() + ":");
            for (Ciclista ciclistaRec : etapaRec.getPodio()) {
                System.out.println(ciclistaRec);
            }
        }
        
        //Escritura de JSON de carreraRec
        JSONController json = new JSONController();
        try {
            json.JSONwriter(carreraRec);
        } catch (IOException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
