package com.usuario.service.usuario_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int usuarioId;
    private int usuarioRut;
    private char usuarioDv;
    private String usuarioNombre;
    private String usuarioEmail;
    private String usuarioDireccion;
    private int usuarioNumeroTelefonico;
    private String tipoUsuario;
    private int comprobanteId;
    
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int id) {
        this.usuarioId = id;
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
    public void setUsuarioNombre(String nombre) {
        this.usuarioNombre = nombre;
    }
    public String getUsuarioEmail() {
        return usuarioEmail;
    }
    public void setUsuarioEmail(String email) {
        this.usuarioEmail = email;
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
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public int getComprobanteId() {
        return comprobanteId;
    }
    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    public Usuario() {
    }
    
}


