package XMLG1;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LecturaXML {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String nombreFichero = "curso.xml";
        File fichero = new File(nombreFichero);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fichero); //normalizar
        //empiezo a trabajar con él
        doc.getDocumentElement().normalize();
        System.out.println("Documento normalizado");

        //EXAMEN - Trabajar con la lectura de un XML
        //ELEMENTO: Mostrar el nodo el raíz
        Element nodoRaiz = doc.getDocumentElement();
        String atributoElementoUAX = nodoRaiz.getAttribute("lugar");
        String atributo2ElementoUAX = nodoRaiz.getAttribute("pais");
        System.out.println(atributoElementoUAX);
        System.out.println(atributo2ElementoUAX);
        //Nodos de alumnos
        NodeList nList = doc.getElementsByTagName("modulo");
        System.out.println(nList.getLength());
        for (int i = 0; i < nList.getLength(); i++) {
            Element moduloUAX = (Element) nList.item(i);
            //Solo podemos sacar el valor del atributo a través del Element
            String atributoCiclo = moduloUAX.getAttribute("ciclo");
            System.out.println("\t" + atributoCiclo);
            NodeList nListAlumnos = moduloUAX.getElementsByTagName("alumno");
            System.out.println(nListAlumnos.getLength());
            for (int j = 0; j < nListAlumnos.getLength(); j++) {
                Element alumnosUAX = (Element) nListAlumnos.item(j);
                // Obtener el nombre del alumno (contenido de texto dentro del nodo "alumno")
                String nombreAlumno = alumnosUAX.getTextContent();
                System.out.println("\t\tAlumno: " + nombreAlumno);
            }
        }

    }
}
