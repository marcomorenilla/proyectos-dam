/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.marco.ficheros;


import com.marco.ficheros.texto.Escritor;
import com.marco.ficheros.texto.Reader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xml.XMLCreator;
import xml.XMLParser;

/**
 *
 * @author Marco
 */
public class Ficheros {

    public static void main(String[] args) {
        
        /* Parte de trabajo con txt
       Escritor escritor = new Escritor();
       Reader reader = new Reader();
       String nuevoFichero;
       
       escritor.writeFile("texto\\texto.txt");
       nuevoFichero = reader.readFile("texto\\texto.txt");
       escritor.writeNewFile(nuevoFichero);
       */
        
        /* Trabaho con XML */
        /*XMLParser lectorXML = new XMLParser();
        try {
            lectorXML.XMLReader("xml\\curso.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        XMLCreator escritorXML = new XMLCreator();
        
        try {
            escritorXML.XMLWriter(escritorXML.XMLDoc(), "xml\\bicicletas.xml");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Ficheros.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        
          
        
    }
}
