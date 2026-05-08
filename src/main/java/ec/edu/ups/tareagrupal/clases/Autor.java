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
public class Autor extends Persona{

    
    private ArrayList<Libro> libros = new ArrayList<>();

     public Autor() {
        
    }
    
     public Autor(String cedula, int edad, String nombre, String apellido, String direccion, boolean estadoVivo, boolean tieneDiscapacidad, String genero) {
        super(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" + "datoPersona="  + ", libros=" + libros + '}';
    }

    public void agregarLibro(Libro libro) {
        if (libro != null) {
            this.libros.add(libro);
        }
    }

    //isEmpty verifica que en la lista haya almenos un caracter y debuelve un booleano 
    //true si no existen caracteres osea que no tiene libros, y false si existe por lo 
    //menos un caracter :p
    public String mostrarLibrosPublicados() {
        if (libros.isEmpty()) {
            return "El autor no tiene libros registrados.";
        }

        String lista = "Libros de " +  getNombre() + " " + getApellido() + ":\n";
        for (Libro libro : libros) {
            lista += "- " + libro.estaDisponible() + " (ISBN: " + libro.toString() + ")\n";
        }
        return lista;
    }

    //el .size cuanta cuantos elemento estan dentro de un Arraylist
    //devuelve un int de osea de la cantidad de elementos pues
    public int contarLibros() {
        return this.libros.size();
    }

}