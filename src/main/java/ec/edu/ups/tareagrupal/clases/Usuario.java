/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;

import static ec.udu.ups.tareagrupal.biblioteca.Biblioteca.pedirFechaLocalDate;
import static ec.udu.ups.tareagrupal.biblioteca.Biblioteca.pedirSiNo;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author stephancedillo
 */
public class Usuario extends Persona {

    private String email;
    private String contrasena;
    private Membresia membresia;

    public Usuario() {
        membresia = new Membresia();
    }

    public Usuario(String email, String contrasena, String cedula, int edad, String nombre, String apellido, String direccion, boolean estadoVivo, boolean tieneDiscapacidad, String genero) {
        super(cedula, edad, nombre, apellido, direccion, estadoVivo, tieneDiscapacidad, genero);
        this.email = email;
        this.contrasena = contrasena;
        this.membresia = membresia;
        membresia = new Membresia();
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
        return "Usuario{" + "email=" + email + ", contrasena=" + contrasena + ",  " + resultado + " membresia=" + membresia + '}';
    }

    public void agregarMembresia() {
        Scanner leer = new Scanner(System.in);

        if (isTieneDiscapacidad() || getEdad() > 60) {
            membresia = new Membresia("Especial", LocalDate.now());
        } else {
            System.out.println("\n--- INGRESAR DATOS DE MEMBRESIA ---");
            System.out.println("Usted quiere tener membresia?(S/N) ");
            boolean resultado = pedirSiNo();
            if (resultado) {
                System.out.println("Ingresa su tipo de membresia: ");
                String tipo = leer.next();

                LocalDate fechaInicio = pedirFechaLocalDate();

                LocalDate fechaVencimiento = pedirFechaLocalDate();

                membresia = new Membresia(tipo, fechaInicio);

            } else {
                membresia = new Membresia();
            }

        }

    }

    public void renovarMembresia() {
        membresia.renovar();
    }

}
