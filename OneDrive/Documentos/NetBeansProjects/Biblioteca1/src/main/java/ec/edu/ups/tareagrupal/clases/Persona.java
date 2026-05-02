/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

/**
 *
 * @author stephancedillo
 */
public class Persona {

    private String cedula;
    private int edad;
    private String nombre;
    private String apellido;
    private String direccion;
    private boolean estadoVivo;
    private boolean tieneDiscapacidad;
    private String genero;

    public Persona() {
    }

    public Persona(String cedula, int edad, String nombre, String apellido, String direccion, boolean estadoVivo, boolean tieneDiscapacidad, String genero) {
        this.cedula = cedula;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.estadoVivo = estadoVivo;
        this.tieneDiscapacidad = tieneDiscapacidad;
        this.genero = genero;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstadoVivo() {
        return estadoVivo;
    }

    public void setEstadoVivo(boolean estadoVivo) {
        this.estadoVivo = estadoVivo;
    }

    public boolean isTieneDiscapacidad() {
        return tieneDiscapacidad;
    }

    public void setTieneDiscapacidad(boolean tieneDiscapacidad) {
        this.tieneDiscapacidad = tieneDiscapacidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", edad=" + edad + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", estadoVivo=" + estadoVivo + ", tieneDiscapacidad=" + tieneDiscapacidad + ", genero=" + genero + '}';
    }

}
