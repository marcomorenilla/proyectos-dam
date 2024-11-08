/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.marco.xmlbiblioteca;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class XMLBiblioteca {

    public static void main(String[] args) {
        System.out.println("Trabajando lectura de XML...");

        //Esto lo he  metido porque me ha dado problemas el archivo y quería hacer comprobaciones
        //Nunca están de más
        File file = new File("Biblioteca.xml");
        if (file.exists() && file.canRead()) {
            System.out.println("XML encontrado\n");
        } else {
            System.out.println("Problemas con el archivo");
        }

        try {
            Document doc = XMLParse(file);
            lecturaLibros(doc);
            lecturaAutores(doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para parsear el documento XML
     *
     * @return Document
     */
    public static Document XMLParse(File xml) throws ParserConfigurationException, IOException, SAXException {

        //Creamos factory para poder crear instancia de clase abstracta.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xml);
        doc.getDocumentElement().normalize();

        return doc;
    }

    /**
     * Método para leer los nodos Libro del XML
     *
     * @return void
     */
    public static void lecturaLibros(Document doc) throws ParserConfigurationException, IOException, SAXException {

        //Cogemos el nodo Libros, no necesitamos recorrer porque solamente aparece una vez como si fuese raíz de otro documento
        //Este paso nos lo podemos saltar porque no vamos a trabajar con ese elemnto y hacer NodeList de Libros directamente
        //Usamos método getTagname para imprimir nombre de la etiqueta
        Element tagLibros = (Element) doc.getElementsByTagName("Libros").item(0);
        String librosRaiz = tagLibros.getTagName();

        System.out.println("Nombre de la etiqueta: " + librosRaiz);
        System.out.println("");

        //Creamos NodeList de Libro
        NodeList nlLibro = tagLibros.getElementsByTagName("Libro");
        System.out.println("Longitud de libros: " + nlLibro.getLength());
        System.out.println("");

        //Recorremos NodeList
        for (int i = 0; i < nlLibro.getLength(); i++) {
            Element libro = (Element) nlLibro.item(i);

            //Sacamos atributos de Libro
            String atrId = libro.getAttribute("id");
            String idioma = libro.getAttribute("idioma");

            System.out.println("id: " + atrId + " idioma: " + idioma);

            //Vamos a ir sacando los elementos de cada Libro
            String titulo = libro.getElementsByTagName("Titulo").item(0).getTextContent();
            String autor = libro.getElementsByTagName("Autor").item(0).getTextContent();
            String genero = libro.getElementsByTagName("Genero").item(0).getTextContent();

            System.out.println("titulo: " + titulo);
            System.out.println("autor: " + autor);
            System.out.println("genero: " + genero);

            //Publicación es elemento complejo, acuérdate de los xsd cuando era secuencia
            //Creamos nodelist para recorrerlo
            //En lugar de coger el documento o tagLibro cogemos libro que ya estamos en este nivel de profundidad
            //Bucle anidado
            System.out.println("Publicación");
            NodeList nlPublicacion = libro.getElementsByTagName("Publicacion");
            for (int j = 0; j < nlPublicacion.getLength(); j++) {
                Element publicacion = (Element) nlPublicacion.item(j);

                //Sacamos lo que ponga en publicación
                String fecha = publicacion.getElementsByTagName("Fecha").item(0).getTextContent();
                String editorial = publicacion.getElementsByTagName("Editorial").item(0).getTextContent();
                String edicion = publicacion.getElementsByTagName("Edicion").item(0).getTextContent();

                System.out.println("\tfecha: " + fecha);
                System.out.println("\teditorial: " + editorial);
                System.out.println("\tedicion: " + edicion);
            }

            //Cogemos el elemento Disponible que se nos ha quedado colgado para el final aunque podríamos meterlo junto con el titulo, autor y género
            //Volvemos a cogerlo de libro porque hemos vuelto a subir
            String disponible = libro.getElementsByTagName("Disponible").item(0).getTextContent();
            System.out.println("Disponible: " + disponible);
            System.out.println("");

        }

    }

    /**
     * Método que lee autores, igual que lecturaLibros pero con Autores
     *
     * @param doc
     */
    public static void lecturaAutores(Document doc) {
        //Volvemos a repetir lo mismo que arriba pero con Autores
        Element tagAutores = (Element) doc.getElementsByTagName("Autores").item(0);
        String raizAutores = tagAutores.getTagName();

        System.out.println("Nombre de la etiqueta: " + raizAutores);
        System.out.println("");

        //Autor es el elemento que se repite y necesita una lista
        //Observa que getElementsByTag devuelve una NodeList
        //Cuando le pones .item(0) o .item(i) te devuelve un Node que es el que casteas a Element
        NodeList nlAutor = tagAutores.getElementsByTagName("Autor");
        
        for (int i = 0; i < nlAutor.getLength(); i++) {
            
            //Esto es lo que te digo cuando haces .item(i) tienes un Node que cambias a Element
            Element autor = (Element) nlAutor.item(i);
            String idAutor = autor.getAttribute("id");

            System.out.println("id: " + idAutor);
            
            //Autor es fácil = corto
            String nombre = autor.getElementsByTagName("Nombre").item(0).getTextContent();
            String nacionalidad = autor.getElementsByTagName("Nacionalidad").item(0).getTextContent();
            
            System.out.println("Nombre: "+nombre);
            System.out.println("Nacionalidad: "+nacionalidad);
            System.out.println(" ");

        }
    }

}
