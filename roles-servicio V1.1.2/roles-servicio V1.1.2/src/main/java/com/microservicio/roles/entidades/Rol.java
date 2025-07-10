package com.microservicio.roles.entidades;

import jakarta.persistence.*;

/**
 * Entidad que representa un rol de usuario.
 */
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolId;

    @Column(unique = true, nullable = false)
    private String rolNombre;

    public Rol() { }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer id) {
        this.rolId = id;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String nombre) {
        this.rolNombre = nombre;
    }
}
