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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author damiansualdea
 */
public class LecturaXML_BK2 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File("BurguerKing2.xml"));
        //System.out.println("Todo correcto");
        doc.getDocumentElement().normalize();
//TRABAJAMOS EN EL EXAMEN 
        Element nodoRaiz = doc.getDocumentElement();
        //Desde el elemento... podemos acceder a los atributos
        String atributoTitulo = nodoRaiz.getAttribute("title");
        String atributoPrecio = nodoRaiz.getAttribute("dinero");
        System.out.println(atributoTitulo + " " + atributoPrecio);

        //Elementos hijos DOM de un XML 
        //NodeList getElementsByTagName(String tagname) ; .getLengTh()
        //NodeList algo estático... no se puede cambiar
        NodeList nList = doc.getElementsByTagName("menu");
        System.out.println(nList.getLength());
        //Empezar a recorrer el NodeList
        for (int i = 0; i < nList.getLength(); i++) {
            Element menu = (Element) nList.item(i);
            //Descripción del menú y el pan que tienen
            String atributoMenu = menu.getAttribute("desc");
            String pan = menu.getElementsByTagName("pan")
                    .item(0).getTextContent();
            //Todos los panes del menú de parrilla
            if (atributoMenu.equals("parrilla")) {
                System.out.println(atributoMenu + " " + pan);
                System.out.println("------------------");
            }

        }

    }
}
