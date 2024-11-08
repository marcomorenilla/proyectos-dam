/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.marco.xmlbiblioteca;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Marco
 */
public class XMLBiblioteca {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    /**
     * Método para parsear el documento XML
     * @return Document
     */
    private Document XML() throws ParserConfigurationException, IOException, SAXException{
        
        //Creamos factory para poder crear instancia de clase abstracta.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("Biblioteca.xml"));
        
        return doc;
    }
    
    
}
