/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

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
public class XMLParser {
    
    //Creamos estructura de lectura XML
    public void XMLReader(String path) throws ParserConfigurationException, 
            SAXException,
            IOException{
        //DocumentBuilderFactory instancia
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DocumentBuilder a partir de factory
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Document a partir de buider builder.parse para leer uno existente
        Document doc = builder.parse(new File(path));
        //.newDocument() creamos uno vacío no interesa en este método
        //Document newDoc = builder.newDocument();
        
        //elemento raíz del documento
        Element root = doc.getDocumentElement();
        System.out.println("Leyendo el documento con nodo raíz: "
        + root.getTagName());
        System.out.println("==================================");
        System.out.println("El curso que estamos trabajando es: "
        + root.getAttribute("name"));
        System.out.println("==================================");
        
        //metemos todos los nodos del documento en una lista de nodos
        NodeList nodeList = doc.getElementsByTagName("modulo");
        System.out.println("Longitud de la nodelist: " +
                nodeList.getLength());
        
        System.out.println("==================================");
        System.out.println("Asignaturas del curso:");
        System.out.println("==================================");
        
        //recorremos la lista  y realizamos las acciones sobre el XML
        for (int i = 0; i<nodeList.getLength();i++){
            
            //primero traemos el nodo  de la lista
            Node node = nodeList.item(i);
            
            //Hacemos comprobaciones sobre el nodo y leemos
            if (node.getNodeType()== Node.ELEMENT_NODE){
                //Necesitamos cambiar el tipo de node a Elemnt para trabajar
                Element nodeElement = (Element) node;
                String idAsignatura = 
                        nodeElement.getAttribute("id");
                String nombreAsignatura =  
                        nodeElement.getElementsByTagName("nombre")
                        .item(0).getTextContent();
                String profesor = 
                        nodeElement.getElementsByTagName("profesor")
                        .item(0).getTextContent();
                String anio =
                        nodeElement.getElementsByTagName("anio")
                        .item(0).getTextContent();
                String estudiantes =
                        nodeElement.getElementsByTagName("estudiantes")
                        .item(0).getTextContent();
                 
                 
                System.out.println("Asignatura " +(i+1));
                System.out.println("id: "+ idAsignatura);
                System.out.println("nombre: "+ nombreAsignatura);
                System.out.println("profesor: "+profesor);
                System.out.println("año: "+ anio);
                System.out.println("estudiantes: "+estudiantes);
                System.out.println("==========================");
            }
            
        }
        
    }
    
}
