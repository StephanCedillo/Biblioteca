/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

/**
 *
 * @author stephancedillo
 */
public class Usuario extends Persona {
    
    private String email;
    private String contrasena;
    private Membresia membresia = new Membresia() ;

    public Usuario() {
    }

   

    public Usuario(String email, String contrasena, String cedula, int edad, String nombre, String apellido, String direccion, boolean estadoVivo, boolean tieneDiscapacidad, String genero,Membresia membresia) {
        super(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
        this.email = email;
        this.contrasena = contrasena;
        this.membresia= membresia;
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

  
    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
         String resultado = super.toString();
        return "Usuario{" + "email=" + email + ", contrasena=" + contrasena + ",  "+ resultado + " membresia=" + membresia + '}';
    }
    public void agregarMembresia(){
    }
    
    public void renovarMembresia(){
        membresia.renovar();
}
   
    
    
    
}
