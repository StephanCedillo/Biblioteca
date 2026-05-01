/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

/**
 *
 * @author stephancedillo
 */
public class Libro {
    
    private String ISBN;
    private Autor autor= new Autor();
    private String nombre;
    private String genero;
    private boolean sirestriccionEdad;
    private int numeroPaginas;
    private String idioma;
    private boolean siestadoDisponibilidad;

    public Libro() {
    }
    
    public Libro(String ISBN, String nombre, String genero, boolean sirestriccionEdad, int numeroPaginas, String idioma, boolean siestadoDisponibilidad) {
        this.ISBN = ISBN;
        this.nombre = nombre;
        this.genero = genero;
        this.sirestriccionEdad = sirestriccionEdad;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
        this.siestadoDisponibilidad = siestadoDisponibilidad;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isSirestriccionEdad() {
        return sirestriccionEdad;
    }

    public void setSirestriccionEdad(boolean sirestriccionEdad) {
        this.sirestriccionEdad = sirestriccionEdad;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isSiestadoDisponibilidad() {
        return siestadoDisponibilidad;
    }

    public void setSiestadoDisponibilidad(boolean siestadoDisponibilidad) {
        this.siestadoDisponibilidad = siestadoDisponibilidad;
    }

    @Override
    public String toString() {
        return "Libro{" + "ISBN=" + ISBN + ", autor=" + autor + ", nombre=" + nombre + ", genero=" + genero + ", sirestriccionEdad=" + sirestriccionEdad + ", numeroPaginas=" + numeroPaginas + ", idioma=" + idioma + ", siestadoDisponibilidad=" + siestadoDisponibilidad + '}';
    }
    
    
    
}
