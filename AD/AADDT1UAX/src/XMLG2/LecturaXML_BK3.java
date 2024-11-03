/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XMLG2;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marco
 */
public class LecturaXML_BK3 {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("BurguerKing3.xml"));
        
        //Empezamos a trabajar
         System.out.println("________________________________");
        doc.getDocumentElement().normalize();
        
        //Recogemos el elemento raíz y sacamos el atributo
        Element root = doc.getDocumentElement();
        String atrRoot = root.getAttribute("title");
        System.out.println(atrRoot);
        System.out.println("BurgerKing en casa");
        System.out.println("________________________________");
        
        
        //Metemos los menús en nodelist y recorremos
        NodeList nlMenu = doc.getElementsByTagName("menu");
        System.out.println("Número de menús en el programa: "+ nlMenu.getLength());
        
        for (int i = 0; i < nlMenu.getLength(); i++) {
            //Casting de nodo a elemento
            
            Element menu = (Element) nlMenu.item(i);
            //Empezamos a sacar el pedido
            System.out.println("--------------------");
            String tipoMenu =  menu.getAttribute("desc");
            System.out.println("desc: "+tipoMenu);
            
            //Element salsas para acceder al atributo.
            Element salsas = (Element) menu.getElementsByTagName("salsa").item(0);
                    
            
            String nombreMenu = menu.getElementsByTagName("nombre").item(0).getTextContent();
            System.out.println("\tNombre: "+nombreMenu);
            String carneMenu = menu.getElementsByTagName("carnes").item(0).getTextContent();
            System.out.println("\tCarnes: "+carneMenu);
            String panMenu = menu.getElementsByTagName("pan").item(0).getTextContent();
            System.out.println("\tPan: "+panMenu);
            String salsaMenu = menu.getElementsByTagName("salsa").item(0).getTextContent();
            System.out.println("\tSalsa: "+salsaMenu);
            System.out.println("\t  - Cantidad salsa: "+salsas.getAttribute("cantidad"));
            String quesoMenu = menu.getElementsByTagName("queso").item(0).getTextContent();
            System.out.println("\tQueso: "+quesoMenu);          
            
          
            
        }
        System.out.println("--------------------");
        
    }
    
}
