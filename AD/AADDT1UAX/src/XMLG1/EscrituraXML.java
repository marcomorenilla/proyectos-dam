package XMLG1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscrituraXML {

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String nombreFichero = "curso.xml";
        File fichero = new File(nombreFichero);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();//Creamos un documento
        //TRABAJO DEL EXAMEN. Trabajamos con doc
        Element root = doc.createElement("UAX");
        root.setAttribute("lugar", "Brunete");
        doc.appendChild(root);

        Element modulo = doc.createElement("modulo");
        modulo.setAttribute("ciclo", "DAM");
        root.appendChild(modulo);

        Element alumno = doc.createElement("alumno");
        alumno.appendChild(doc.createTextNode("Luis"));
        modulo.appendChild(alumno);

        alumno = doc.createElement("alumno");
        alumno.appendChild(doc.createTextNode("Jose"));
        modulo.appendChild(alumno);

        modulo = doc.createElement("modulo");
        modulo.setAttribute("ciclo", "DAW");
        root.appendChild(modulo);

        alumno = doc.createElement("alumno");
        alumno.appendChild(doc.createTextNode("María"));
        modulo.appendChild(alumno);

        alumno = doc.createElement("alumno");
        alumno.appendChild(doc.createTextNode("Pedro"));
        modulo.appendChild(alumno);
        //Transformer para la escritura
        TransformerFactory transformerFactory
                = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //Hacemos referencia al documento doc. (Mirar la creación del fichero)
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(fichero));
        transformer.transform(source, result);
        System.out.println("");

    }

}
