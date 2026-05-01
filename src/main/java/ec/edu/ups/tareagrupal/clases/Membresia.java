/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.tareagrupal.clases;
import java.time.LocalDate;
/**
 *
 * @author stephancedillo
 */
public class Membresia {
    
    String tipoMembresia;
    LocalDate fechaInicio;
    LocalDate fechaVencimiento;
    LocalDate hoy = LocalDate.now(); 
    public Membresia() {
    }

    public Membresia(String tipoMembresia, LocalDate fechaInicio, LocalDate fechaVencimiento) {
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Membresia{" + "tipoMembresia=" + tipoMembresia + ", fechaInicio=" + fechaInicio + ", fechaVencimiento=" + fechaVencimiento + '}';
    }
    
    public int calcularDescuento(){
        if(tipoMembresia.equalsIgnoreCase("Corporativa")){
            return 25;
        }else if(tipoMembresia.equalsIgnoreCase("Academica")){
            return 15;
        }else if(tipoMembresia.equalsIgnoreCase("Estudiantil")){
            return 20;
        }else{
            return 0;
        }
    }
    public boolean estaVigente(){
        return fechaVencimiento.isBefore(hoy);
    }
    public void renovar(){
        fechaInicio = hoy;
        fechaVencimiento = hoy;
        fechaVencimiento.plusMonths(3);
    }
    public String obtenerDiasRestantes(){
        int mesesRestantes = fechaVencimiento.getMonthValue() - fechaInicio.getMonthValue();
        int diasRestantes = fechaVencimiento.getDayOfMonth() - fechaInicio.getDayOfMonth();
        return "Los meses restantes son : " +  mesesRestantes + " y los dias faltantes : " + diasRestantes + " ";
    } 
    
    
}
