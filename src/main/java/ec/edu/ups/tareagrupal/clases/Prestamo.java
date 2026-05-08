/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author stephancedillo
 */
public class Prestamo {

    private int id;
    private Usuario usuario;
    private Libro libro;
    private boolean estado;

    private LocalDate fechaPedido = LocalDate.now();
    private LocalDate fechaDevolucion = fechaPedido.plusMonths(1);
    ;

 

    private static int contadorId = 0;

    public Prestamo() {
         id = contadorId++;
    }

    public Prestamo(Usuario usuario,Libro libro ,boolean estado) {
        id = contadorId++;
        this.estado = estado;
        this.usuario = usuario;
        this.libro = libro;
   
   
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

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
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
        return "Registro{" + "id=" + id + ", fechaPedido=" + fechaPedido + ", usuario=" + usuario + ", libro=" + libro + ", estado=" + estado + ", fechaDevolucion=" + fechaDevolucion +  '}';
    }

    public void registrarPrestamo() {

        if (libro.estaDisponible()) {

            this.fechaPedido = LocalDate.now();

            this.fechaDevolucion = fechaPedido.plusMonths(1);

            this.estado = true;

            this.libro.prestar();

            System.out.println("Préstamo registrado con éxito.");
            System.out.println("Fecha de devolución: " + fechaDevolucion);

        } else {

            System.out.println("El libro no se puede prestar porque no está disponible.");
        }
    }

    public void registrarDevolucion() {
        this.estado = false;
        this.libro.devolver();
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

    

}
