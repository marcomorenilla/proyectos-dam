/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Marco
 */
public class Biblioteca {

    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        //Se crea primera consulta
        System.out.println("----- 1ª Consulta -----");
        String fromLibro = "from Libro";
        Query primeraConsulta = session.createQuery(fromLibro);
        List<Libro> listaPrimera = primeraConsulta.list();
        Iterator<Libro> iter = listaPrimera.iterator();

        System.out.println("Lista size: " + listaPrimera.size());
        while (iter.hasNext()) {
            Libro l = (Libro) iter.next();
            String isbn = l.getIsbn();
            String titulo = l.getTitulo();
            String autor = l.getAutor().getNombreAutor();
            System.out.println("-----------------------");
            System.out.println("ISBN: " + isbn);
            System.out.println("titulo: " + titulo);
            System.out.println("autor: " + autor);
            System.out.println("-----------------------");

        }

        //Se crea segunda consulta aunque podríamos sacar toda la información propuesta de la primera
        System.out.println("");
        System.out.println("----- 2ª Consulta -----");
        System.out.println("");

        String fromLibroAll = "From Libro l, Autor a, Editorial e, Tema t where l.autor.idAutor = a.idAutor and l.editorial.idEditorial = e.idEditorial and l.tema.idTema = t.idTema ";
        Query segundaConsulta = session.createQuery(fromLibroAll);
        List<Object> listaSegunda = segundaConsulta.list();

        System.out.println("Size: " + listaSegunda.size());

        for (Object o : listaSegunda) {
            Object[] object = (Object[]) o;
            Libro l = (Libro) object[0];
            Autor a = (Autor) object[1];
            Editorial e = (Editorial) object[2];
            Tema t = (Tema) object[3];

            System.out.println("Libro:");
            System.out.println("_______");
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Titulo: " + l.getTitulo());
            System.out.println("NumEjemplares: " + String.valueOf(l.getNumeroEjemplares()));
            System.out.println("Nombre autor: " + a.getNombreAutor());
            System.out.println("Nombre editorial: " + e.getNombreEditorial());
            System.out.println("Tema: " + t.getNombreTema());
            System.out.println("_________");

        }

        //Tercera consulta parametrizada
        Scanner scn = new Scanner(System.in);
        System.out.println("Introduce el nombre del libro a buscar en la BBDD: ");
        String libro = scn.nextLine();

        String fromLibroWhere = "from Libro l where l.titulo = :n";
        Query terceraConsulta = session.createQuery(fromLibroWhere);

        terceraConsulta.setParameter("n", libro);

        Libro l = (Libro) terceraConsulta.uniqueResult();
        if (l != null) {
            System.out.println("Libro:");
            System.out.println("_______");
            System.out.println("ISBN: " + l.getIsbn());
            System.out.println("Titulo: " + l.getTitulo());
            System.out.println("NumEjemplares: " + String.valueOf(l.getNumeroEjemplares()));
            System.out.println("Nombre autor: " + l.getAutor().getNombreAutor());
            System.out.println("Nombre editorial: " + l.getEditorial().getNombreEditorial());
            System.out.println("Tema: " + l.getTema().getNombreTema());
            System.out.println("_________");
        } else {
            System.out.println("El libro no se encuentra en la BBDD");
        }

        session.close();
        System.exit(0);

    }

}
