package com.microservicio.roles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.roles.entidades.AsignacionUsuarioRol;

import java.util.Optional;

/**
 * Repositorio de la entidad AsignacionUsuarioRol.
 */
@Repository
public interface RepositorioAsignacionUsuarioRol extends JpaRepository<AsignacionUsuarioRol, Integer> {

    Optional<AsignacionUsuarioRol> findByUsuarioId(Integer usuarioId);
    boolean existsByUsuarioId(Integer usuarioId);
}
