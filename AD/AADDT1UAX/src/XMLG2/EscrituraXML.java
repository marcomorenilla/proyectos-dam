package XMLG2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscrituraXML {

    public static void main(String[] args) throws FileNotFoundException, TransformerException, ParserConfigurationException {
        String nombreFichero = "curso5.xml";
        File fichero = new File(nombreFichero);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();//Creamos un documento
        //TRABAJO DEL EXAMEN. Trabajamos con doc
        
        Element item = doc.createElement("item");
        item.setAttribute("origen", "expansion.com");
        doc.appendChild(item);
        
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(" El Ibex choca con los 12.000 puntos"));
        item.appendChild(title);
        
        Element descripcion = doc.createElement("descripcion");
        descripcion.appendChild(doc.createTextNode("El Ibex se ha desmarcado"));
        item.appendChild(descripcion);
        
        
        //Transformer para la escritura
        TransformerFactory transformerFactory
                = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //Hacemos referencia al documento doc. (Mirar la creación del fichero)
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult
            (new FileOutputStream(fichero));
        transformer.transform(source, result);
        System.out.println("Fin del programa");
    }
}
