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
public class Usuario {
    
    private int idUsuario;
    
    private String nombre;
    
    private String apellido;
    
    private String telefono;
    
    private String correo;
    
    private String contraseña;
    
    private int tipo;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String telefono, String correo, String contraseña, int tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }



    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", tipo=" + tipo + '}';
    }
    
    
    
    
    
}
