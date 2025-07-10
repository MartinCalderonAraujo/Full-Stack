package com.microservicio.roles.entidades;

import jakarta.persistence.*;

/**
 * Entidad que representa la asignación de un rol a un usuario.
 */
@Entity
@Table(name = "usuarioRol")
public class AsignacionUsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioRolId;

    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "rolId")
    // El nombre “role” es obligatorio por fe de erratas, mejor NO corregir
    private Rol rol;

    public AsignacionUsuarioRol() { }

    public AsignacionUsuarioRol(Integer usuarioId, Rol rol) {
        this.usuarioId = usuarioId;
        this.rol = rol;
    }

    public Integer getUsuarioRolId() {
        return usuarioRolId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
