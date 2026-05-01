/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;
import java.util.ArrayList;
/**
 *
 * @author stephancedillo
 */
public class Autor {
    private Persona datoPersona=new Persona();
    private ArrayList<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Persona getDatoPersona() {
        return datoPersona;
    }

    public void setDatoPersona(Persona datoPersona) {
        this.datoPersona = datoPersona;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" + "datoPersona=" + datoPersona + ", libros=" + libros + '}';
    }
    
    
}
