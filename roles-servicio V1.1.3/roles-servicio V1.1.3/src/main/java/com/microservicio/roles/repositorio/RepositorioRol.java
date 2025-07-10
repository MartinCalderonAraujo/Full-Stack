package com.microservicio.roles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.roles.entidades.Rol;

/**
 * Repositorio de la entidad Rol.
 */
@Repository
public interface RepositorioRol extends JpaRepository<Rol, Integer> {
    boolean existsByRolNombre(String nombre);
}
