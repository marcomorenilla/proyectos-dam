/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Marco
 */
public class XMLCreator {
    
    public Document XMLDoc() throws ParserConfigurationException{
       
        //Métodos para generar document XML
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        
        //Creamos el nodo principal del XML
        Element element = doc.createElement("Bicicletas");
        doc.appendChild(element);
        
        //Añadimos nodo bicicleta al documento
        //Método setAttribute para añadir atributo al nodo
        //Añadimos nodos texto a la bicicleta
        Element bicicleta = doc.createElement("Bicicleta");
        bicicleta.setAttribute("categoría", "carretera");
        
        Element marca = doc.createElement("marca");
        marca.appendChild(doc.createTextNode("Giant"));
        
        Element modelo = doc.createElement("modelo");
        modelo.appendChild(doc.createTextNode("TCR Advanced SL"));
        
        Element anio = doc.createElement("anio");
        anio.setAttribute("value", "2022");
        
        bicicleta.appendChild(marca);
        bicicleta.appendChild(modelo);
        bicicleta.appendChild(anio);
        element.appendChild(bicicleta);
        
        
        System.out.println("Documento preparado para escritura");
        
        return doc;
        
        
         
    }
    
    public void XMLWriter(Document source, String path) throws TransformerConfigurationException, TransformerException{
       
        //Se crea fábrica de escritura para implementar clase abstracta transformer
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        //Se definen fuente - destino, doc a escribir y documento en el que se quiere escribir
        DOMSource fuente = new DOMSource(source);
        StreamResult destino = new StreamResult(new File(path));
        
        //Con la ejecución del método transform se escribe
        transformer.transform(fuente, destino);
        System.out.println("Documento escrito correctamente");
    }
    
}
