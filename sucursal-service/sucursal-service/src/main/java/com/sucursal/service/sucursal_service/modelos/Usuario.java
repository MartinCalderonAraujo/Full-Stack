package com.sucursal.service.sucursal_service.modelos;

public class Usuario {
    private int usuarioId;
    private int usuarioRut;
    private char usuarioDv;
    private String usuarioNombre;
    private String usuarioEmail;
    private String usuarioDireccion;
    private int usuarioNumeroTelefonico;

    public Usuario() {
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioRut() {
        return usuarioRut;
    }

    public void setUsuarioRut(int usuarioRut) {
        this.usuarioRut = usuarioRut;
    }

    public char getUsuarioDv() {
        return usuarioDv;
    }

    public void setUsuarioDv(char usuarioDv) {
        this.usuarioDv = usuarioDv;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioDireccion() {
        return usuarioDireccion;
    }

    public void setUsuarioDireccion(String usuarioDireccion) {
        this.usuarioDireccion = usuarioDireccion;
    }

    public int getUsuarioNumeroTelefonico() {
        return usuarioNumeroTelefonico;
    }

    public void setUsuarioNumeroTelefonico(int usuarioNumeroTelefonico) {
        this.usuarioNumeroTelefonico = usuarioNumeroTelefonico;
    }

}
