/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retojson;

/**
 *
 * @author Marco
 */
public class Hero {

    //Atributos
    private String apodo;
    private String nombre;
    private String link;
    private String img;
    private int size;

    //Constructores
    public Hero() {

    }

    public Hero(String apodo, String nombre, String link, String img, int size) {
        this.apodo = apodo;
        this.nombre = nombre;
        this.link = link;
        this.img = img;
        this.size = size;
    }

  
    //Getter and Setter
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
