/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;
import java.util.Date;
/**
 *
 * @author stephancedillo
 */
public class Registro {
    private String id;
    private Date fechaPedido= new Date();
    private Usuario usuario = new Usuario();
    private Libro libro = new Libro();
    private boolean estado;
    private Date fechaDevolucion= new Date();
    private Factura factura= new Factura();
    private boolean estadoFactura;

    public Registro() {
    }

    public Registro(String id, boolean estado, boolean estadoFactura) {
        this.id = id;
        this.estado = estado;
        this.estadoFactura = estadoFactura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
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

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public boolean isEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(boolean estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    @Override
    public String toString() {
        return "Registro{" + "id=" + id + ", fechaPedido=" + fechaPedido + ", usuario=" + usuario + ", libro=" + libro + ", estado=" + estado + ", fechaDevolucion=" + fechaDevolucion + ", factura=" + factura + ", estadoFactura=" + estadoFactura + '}';
    }

    
    
}
