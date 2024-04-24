/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author JEYSON
 */
public class Orden extends Producto{
    
    
    private int idOrden;
    
    private int idUser;
    
    private int cantidad;
    
    private String fecha;

    public Orden() {
    }

    
    
    public Orden(int idOrden, int idUser, int cantidad, String fecha) {
        this.idOrden = idOrden;
        this.idUser = idUser;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Orden(int idUser, int cantidad, String fecha) {
        this.idUser = idUser;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Orden{" + "idOrden=" + idOrden + ", idUser=" + idUser + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
    }
    
    
    
    
    
}
