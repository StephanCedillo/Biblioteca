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
public class Factura {

    private int id = 0;
    private Usuario usuario = new Usuario();
    private Date fechaCompra = new Date();
    private Libro libro = new Libro();
    private double baseImponible;
    private double impuestos;
    private int descuento;
    private double costoTotal;

    int contadorId;

    public Factura() {
    }

    public Factura(double baseImponible, double impuestos, int descuento) {
        this.id = contadorId++;
        this.baseImponible = baseImponible;
        this.impuestos = impuestos;
        this.descuento = descuento;
        this.costoTotal = (baseImponible * (impuestos / 100.0))
                - (baseImponible * (descuento / 100.0))
                + baseImponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", usuario=" + usuario + ", fechaCompra=" + fechaCompra + ", libro=" + libro + ", baseImponible=" + baseImponible + ", impuestos=" + impuestos + ", descuento=" + descuento + ", costoTotal=" + costoTotal + '}';
    }

    public double calcularSubtotal() {
        return this.baseImponible;
    }

    public double calcularImpuestos() {
        return (this.baseImponible * (double) this.impuestos) / 100;
    }

    public void aplicarDescuento() {
        if (this.usuario != null && this.usuario.getMembresia() != null) {
            Membresia m = this.usuario.getMembresia();
            if (m.estaVigente()) {
                this.descuento = m.calcularDescuento();
                System.out.println("Descuento aplicado: " + this.descuento + "%");
            } else {
                this.descuento = 0;
                System.out.println("La membresía ha caducado. No hay descuento.");
            }
        }
    }

    public double calcularTotal() {
        this.aplicarDescuento();

        double valorImpuestos = this.calcularImpuestos();

        double valorDescuento = (this.baseImponible * this.descuento) / 100;
        this.costoTotal = (this.baseImponible + valorImpuestos) - valorDescuento;
        return this.costoTotal;
    }

    public String imprimirFactura() {
        double total = calcularTotal();
        double valorImpuestos = calcularImpuestos();
        double montoDescuento = (this.baseImponible * this.descuento) / 100;

        return "Factura Nro: " + id + "\n"
                + "Fecha: " + fechaCompra + "\n"
                + "Cliente: " + usuario.getDatosPersonales().getNombre() + " "
                + usuario.getDatosPersonales().getApellido() + "\n"
                + "Libro: " + libro.getNombre() + "\n"
                + "Subtotal: $" + baseImponible + "\n"
                + "Impuestos " + impuestos + " : " + valorImpuestos + "\n"
                + "Descuento " + descuento + "%: " + montoDescuento + "\n"
                + "TOTAL A PAGAR: $" + total + "\n";
    }

}
