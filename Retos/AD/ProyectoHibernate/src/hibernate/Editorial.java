package hibernate;
// Generated 04-dic-2024 20:51:29 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Editorial generated by hbm2java
 */
public class Editorial  implements java.io.Serializable {


     private Integer idEditorial;
     private String nombreEditorial;
     private String direccion;
     private String telefono;
     private Set libros = new HashSet(0);

    public Editorial() {
    }

	
    public Editorial(String nombreEditorial, String direccion, String telefono) {
        this.nombreEditorial = nombreEditorial;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public Editorial(String nombreEditorial, String direccion, String telefono, Set libros) {
       this.nombreEditorial = nombreEditorial;
       this.direccion = direccion;
       this.telefono = telefono;
       this.libros = libros;
    }
   
    public Integer getIdEditorial() {
        return this.idEditorial;
    }
    
    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }
    public String getNombreEditorial() {
        return this.nombreEditorial;
    }
    
    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Set getLibros() {
        return this.libros;
    }
    
    public void setLibros(Set libros) {
        this.libros = libros;
    }




}


