/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.retoxml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marco
 */
public class RetoXML {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File("mercados.xml"));

        doc.getDocumentElement().normalize();

        //Creamos variables 
        String tituloCanal;
        String copyright;
        String tituloNoticia;
        String atributoNoticia;
        String creadorNoticia;
        
        //Sacamos título de canal y copyright directamente a través de la clase Node
        tituloCanal = doc.getElementsByTagName("title").item(0).getTextContent();
        copyright = doc.getElementsByTagName("copyright").item(0).getTextContent();

        System.out.println("Título del canal: " + tituloCanal);
        System.out.println("Copyright: " + copyright);

        //NodeList para leer el documento completo si tiene más de un item repetido.
        NodeList nlItem = doc.getElementsByTagName("item");
        System.out.println("Número de items del documento: "+nlItem.getLength());
        
        for(int i = 0; i<nlItem.getLength();i++){
            
            Element item = (Element) nlItem.item(i);
            
            // Según recorremos vamos cogiendo la info, guardando en variables e imprimiendo
            tituloNoticia = item.getElementsByTagName("title").item(0).getTextContent();
            Element atributo = (Element) item.getElementsByTagName("media:title").item(0);
            atributoNoticia = atributo.getAttribute("type");
            creadorNoticia = item.getElementsByTagName("dc:creator").item(0).getTextContent();
            
            System.out.println("_____________________________________");
            System.out.println("Noticia: "+(i+1));
            System.out.println("Título noticia: "+tituloNoticia);
            System.out.println("Tipo de medio: "+atributoNoticia);
            System.out.println("Creador de noticia: "+creadorNoticia);
            System.out.println("_____________________________________");
            
        }   
         
    }

}
