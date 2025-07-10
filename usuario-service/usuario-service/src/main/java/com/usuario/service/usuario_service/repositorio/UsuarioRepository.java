package com.usuario.service.usuario_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usuario.service.usuario_service.entidades.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    List<Usuario> findByComprobanteId(int comprobanteId);
    boolean existsByUsuarioNombre(String nombre);

}