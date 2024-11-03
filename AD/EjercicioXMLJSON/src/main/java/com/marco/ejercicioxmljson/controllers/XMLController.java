/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxmljson.controllers;

import com.marco.ejercicioxmljson.EjercicioXMLJSON;
import com.marco.ejercicioxmljson.models.Carrera;
import com.marco.ejercicioxmljson.models.Ciclista;
import com.marco.ejercicioxmljson.models.Etapa;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marco
 */
public class XMLController {

    //Método para crear las clases necesarias para parsear el documento xml
    private Document XMLParseDoc(String path) throws SAXException, IOException, ParserConfigurationException {
        File f = new File(path);
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();
        Document doc = null;

        if (f.exists() && f.canRead()) {
            doc = db.parse(f);
        }

        return doc;
    }

    //Método para crear las clases necesarias de escritura de un nuevo doc
    private Document XMLNewDoc() throws ParserConfigurationException {

        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = df.newDocumentBuilder();
        Document doc = db.newDocument();

        return doc;
    }

    //Método con las clases necesarias para escribir documentos xml a través de transformer
    private void XMLWriter(Document doc, String path) throws TransformerConfigurationException, TransformerException {
        //Escribimos XML
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
    }

    //Recorremos la carrera y vamos añadiendo etapas al nodo principal
    public void XMLWriteDoc(Carrera carrera, String path)
            throws ParserConfigurationException, TransformerConfigurationException,
            TransformerException {

        //Documento configurado para escritura de carrera
        Document doc = XMLNewDoc();

        //Creamos elementos del documento.xml y añadimos el nodo raiz
        Element root = doc.createElement(carrera.getNombre());
        doc.appendChild(root);

        //Bucle para recorrer el número de etapas de la carrera
        for (int i = 0; i < carrera.getEtapas().size(); i++) {
            Element etapa = doc.createElement("etapas");
            Element podio = doc.createElement("podio");

            //Añadimos atributos a elementos
            etapa.setAttribute("numero", String.valueOf(carrera.getEtapas().get(i).getNumero()));

            //Vamos encadenando nodos
            root.appendChild(etapa);
            etapa.appendChild(podio);

            for (int j = 0; j < carrera.getEtapas().get(i).getPodio().size(); j++) {

                //Metemos los elementos que se repiten en el bucle
                Element ciclista = doc.createElement("ciclista");
                Element nombreCiclista = doc.createElement("nombre");
                Element equipoCiclista = doc.createElement("equipo");
                Element tiempoCiclista = doc.createElement("tiempo");

                //Añadimos texto a los nodos finales
                nombreCiclista.appendChild(doc.createTextNode(carrera.getEtapas().get(i).getPodio().get(j).getNombre()));
                equipoCiclista.appendChild(doc.createTextNode(carrera.getEtapas().get(i).getPodio().get(j).getEquipo()));
                tiempoCiclista.appendChild(doc.createTextNode(carrera.getEtapas().get(i).getPodio().get(j).getTiempo()));

                //Seguimos encadenando nodos en bucle
                ciclista.appendChild(nombreCiclista);
                ciclista.appendChild(equipoCiclista);
                ciclista.appendChild(tiempoCiclista);
                podio.appendChild(ciclista);

            }

        }

        //Escritura de archivo
        XMLWriter(doc, path);

    }

    //Método para leer el documento XML gestionamos excepciones para poder crear objeto en el main
    public Carrera XMLReadDoc(String path) {
        Carrera carrera = null;

        try {
            //Parseamos el archivo con el método parse creado en la clase
            Document doc = XMLParseDoc(path);

            //Primero normalizar
            doc.getDocumentElement().normalize();

            //Sacamos el nombre de la carrera del archivo
            Element root = doc.getDocumentElement();
            String nombreCarrera = root.getTagName();

            System.out.println("El nombre de la carrera es:  " + nombreCarrera);

            //Creamos Carrera que es el objeto que devolveremos para hacer CRUD
            carrera = new Carrera(nombreCarrera);

            //Creamos una nodeList de etapas
            NodeList nlEtapas = doc.getElementsByTagName("etapas");

            //Recorrer la lista convirtiendo de nodelist a nodo
            for (int i = 0; i < nlEtapas.getLength(); i++) {

                //Creamos etapa con constructor por defecto para ir añadiendo a la carrera
                Etapa etapa = new Etapa();
                System.out.println("");

                Element elementEtapas = (Element) nlEtapas.item(i);

                String numero = elementEtapas.getAttribute("numero");
                System.out.println("Número de etapa " + numero);
                System.out.println("_____________________________");

                //Añadimos número a la etapa
                etapa.setNumero(Integer.parseInt(numero));

                NodeList nlPodio = elementEtapas.getElementsByTagName("podio");

                Element elementPodio = (Element) nlPodio.item(0);
                String atrPodio = elementPodio.getAttribute("fecha");
                System.out.println("Fecha podio: " + atrPodio);
                System.out.println("_____________________________ \n");
                
                //Añadimos fecha a la etapa recuperando a través de atributo del podio
                etapa.setFecha(atrPodio);

                NodeList nlCiclistas = elementPodio.getElementsByTagName("ciclista");

                for (int j = 0; j < nlCiclistas.getLength(); j++) {
                    Element elementCiclista = (Element) nlCiclistas.item(j);

                    String nombreCiclista = elementCiclista.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("nombre ciclista:  " + nombreCiclista);
                    int atrCiclista = Integer.parseInt(elementCiclista.getAttribute("posicion"));
                    System.out.println("posición ciclista: " + atrCiclista);
                    String equipo = elementCiclista.getElementsByTagName("equipo").item(0).getTextContent();
                    System.out.println("equipo ciclista: " + equipo);
                    String tiempo = elementCiclista.getElementsByTagName("tiempo").item(0).getTextContent();
                    System.out.println("tiempo ciclista: " + tiempo);
                    System.out.println("__________________________________________");

                    //Creamos ciclista con los datos del XML
                    Ciclista ciclista = new Ciclista(atrCiclista, nombreCiclista, equipo, tiempo);

                    //Añadimos ciclista al podio
                    etapa.addCiclista(ciclista);
                }

                //Añadimos etapa a la carrera
                carrera.addEtapa(etapa);
            }
        } catch (SAXException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EjercicioXMLJSON.class.getName()).log(Level.SEVERE, null, ex);
        }

        return carrera;
    }
}
