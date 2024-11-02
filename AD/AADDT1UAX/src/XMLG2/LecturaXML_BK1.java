package XMLG2;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LecturaXML_BK1 {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File("BurguerKing1.xml"));
        //System.out.println("Todo correcto");
        
        doc.getDocumentElement().normalize();
//TRABAJAMOS EN EL EXAMEN 
//Element getDocumentElement()
        Element nodoRaiz = doc.getDocumentElement();
        //Desde el elemento... podemos acceder a los atributos
        String atributoTitulo = nodoRaiz.getAttribute("title");
        String atributoPrecio = nodoRaiz.getAttribute("dinero");
        System.out.println(atributoTitulo+" "+atributoPrecio);
        
        //Elementos hijos DOM de un XML 
        //NodeList getElementsByTagName(String tagname) ; .getLength()
        //NodeList algo estático... no se puede cambiar
        NodeList nList = doc.getElementsByTagName("menu");
        System.out.println(nList.getLength());
        //Empezar a recorrer el NodeList
        for(int i=0;i<nList.getLength();i++){
            //Elemento de cada uno de los hijos //API recoger elemento de NodeList
            Element menu = (Element) nList.item(i);
            //Element... 
            //Contenido en String de un elemento 
            //getElementsByTagName(String ).item(0).getTextContent
            String nombre = menu.getElementsByTagName("nombre")
                    .item(0).getTextContent();
            String salsa = menu.getElementsByTagName("salsa")
                    .item(0).getTextContent();
            System.out.println(nombre+ " "+salsa);
                
        }
      }
    }

