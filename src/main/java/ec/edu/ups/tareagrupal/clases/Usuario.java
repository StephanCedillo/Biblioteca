/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

/**
 *
 * @author stephancedillo
 */
public class Usuario {
    
    private String email;
    private String contrasena;
    private Persona datosPersonales = new Persona();
    private Membresia membresia = new Membresia() ;

    public Usuario() {
    }

    public Usuario(String email, String contrasena, Persona datosPersonales, Membresia membresia) {
        this.email = email;
        this.contrasena = contrasena;
        this.datosPersonales = datosPersonales;
        this.membresia = membresia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(Persona datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", contrasena=" + contrasena + ", datosPersonales=" + datosPersonales + ", membresia=" + membresia + '}';
    }
    
    public void devolverLibro(){
        
    }
    public void renovarMembresia(){
        membresia.renovar();
}
    public int obtenerDescuento(){
        int descuento = membresia.calcularDescuento();
        if(datosPersonales.isTieneDiscapacidad()){
            descuento +=20;
        }
        return descuento;
    }
    
    
    
}
