/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.retomongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Marco
 */
public class RetoMongoDB {

    public static void main(String[] args) {

        //Conexión a MongoDB y obteniendo colección de la BBDD
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27018");
        MongoClient client = MongoClients.create(connectionString);
        MongoDatabase db = client.getDatabase("mibasededatos");
        MongoCollection<Document> coleccion = db.getCollection("amigos");

        System.out.println("Colección original:");
        mostrarCollection(coleccion);

        //insertarDocumento(coleccion);

        //insertarDocumentoAppend(coleccion);

        System.out.println("Coleccion utilizando iterator");
        iterarCollection(coleccion);

    }

    private static void mostrarCollection(MongoCollection<Document> coleccion) {
        //Pasando colección a una lista
        List<Document> consulta = coleccion.find().into(new ArrayList<Document>());

        for (int i = 0; i < consulta.size(); i++) {
            Document amigo = consulta.get(i);
            String nombre = amigo.getString("nombre");
            int telefono = amigo.getInteger("telefono");
            String curso = amigo.getString("curso");
            int nota = 0;
            
            if (amigo.containsKey("nota")) {
                nota = amigo.getInteger("nota");
            }

            System.out.println("____________amigo: " + (i + 1) + "____________");
            System.out.println("Nombre de amigo: " + nombre);
            System.out.println("Teléfono de amigo: " + telefono);
            System.out.println("Curso de amigo: " + curso);
            if (amigo.containsKey("nota")) {
                System.out.println("Nota de amigo: " + nota);
            }

            System.out.println("________________________________");
            System.out.println("");

        }
    }

    private static void insertarDocumento(MongoCollection<Document> coleccion) {
        Document doc = new Document();

        doc.put("nombre", "Paco");
        doc.put("telefono", 7845632);
        doc.put("curso", "2DAM");
        doc.put("fecha", new Date());

        coleccion.insertOne(doc);
    }

    private static void insertarDocumentoAppend(MongoCollection<Document> coleccion) {
        Document doc = new Document("nombre", "Pedro")
                .append("telefono", 78544233)
                .append("curso", "2DAM");

        coleccion.insertOne(doc);
    }

    private static void iterarCollection(MongoCollection<Document> coleccion) {
        MongoCursor<Document> cursor = coleccion.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
    }
}
