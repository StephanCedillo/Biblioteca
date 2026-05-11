/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author stephancedillo
 */
public class Prestamo {

    private int id;
    private Usuario usuario;// Asociación
    private List<Libro> libros;// Asociación
    private boolean estado;

    private LocalDate fechaPedido = LocalDate.now(); 
    private LocalDate fechaDevolucion = fechaPedido.plusMonths(1);
    private static int contadorId = 0;

    public Prestamo() {
        id = contadorId++;
        libros = new ArrayList<>();
    }

    public Prestamo(Usuario usuario, boolean estado) {
        id = contadorId++;
        this.estado = estado;
        this.usuario = usuario;
        libros = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Libro> getLibro() {
        return libros;
    }

    public void setLibro(Libro libro) {
        this.libros = libros;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        String resultado= ""; 
        resultado +="---Registro---"+"\n" 
                + "id=" + id +"\n" +
                "fechaPedido=" + fechaPedido + "\n" +
                "usuario=" + usuario + 
                "libro=";
                
          for(Libro libro: libros){
            resultado+= libro.toString() + "\n";
        }
        resultado+="estado=" + estado +"\n" +
                "fechaDevolucion=" + fechaDevolucion ;
        return resultado;
    }

    public boolean registrarPrestamo(Libro libro) {
        boolean aux;

        if (libro.estaDisponible()) {

            this.fechaPedido = LocalDate.now();

            this.fechaDevolucion = fechaPedido.plusMonths(1);

            this.estado = true;

            libro.prestar();

            System.out.println("Préstamo registrado con éxito.");
            System.out.println("Fecha de devolución: " + fechaDevolucion);
            aux = true;

        } else {
            aux = false;

            System.out.println("El libro no se puede prestar porque no está disponible.");
        }
        return aux;
    }

    public void registrarDevolucion() {
        this.estado = false;
        for (Libro libro : libros) {
            libro.devolver();
        }

        System.out.println("Devolución registrada.");
    }

    // El afeter compara la fecha de debolucion
    //si la fecha es superior devuelve true y 
    // el and && hace que solo retorne si la fecha true 
    //osea que es superior a la de devoluvion y el estado true osea aun 
    //esta en las manos del usuario 
    public boolean estaAtrasado() {

        LocalDate hoy = LocalDate.now();

        return hoy.isAfter(fechaDevolucion) && estado;
    }

    public void agregarLibro(Libro libro) {
        boolean siPoderAgregar = registrarPrestamo(libro);
        if (siPoderAgregar) {
            libros.add(libro);
        }
    }

}
