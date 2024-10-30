/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marco.ejercicioxml.controllers;

import models.Carrera;
import java.io.File;
import java.io.IOException;
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

    //Método para leer el documento XML
    public void XMLReadDoc(String path, String item) throws SAXException, IOException, ParserConfigurationException {
        //Parseamos el archivo con el método parse creado en la clase
        Document doc = XMLParseDoc(path);

        //Primero normalizar
        doc.getDocumentElement().normalize();

        //Creamos una nodeList de etapas
        NodeList nlElement = doc.getElementsByTagName(item);

        //Recorrer la lista convirtiendo de nodelist a nodo
        for (int i = 0; i < nlElement.getLength(); i++) {
            Node nodeElement = nlElement.item(i);

            //check node
            if (nodeElement.getNodeType() == Node.ELEMENT_NODE) {

                //Empezamos a procesar
                //Hacemos cast
                Element element = (Element) nodeElement;

                System.out.println(element.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println(element.getElementsByTagName("equipo").item(0).getTextContent());
                System.out.println(element.getElementsByTagName("tiempo").item(0).getTextContent());

            }
        }

    }
}
