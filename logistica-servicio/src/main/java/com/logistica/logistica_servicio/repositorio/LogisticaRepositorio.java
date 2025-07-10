package com.logistica.logistica_servicio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistica.logistica_servicio.entidades.Logistica;

@Repository
public interface LogisticaRepositorio extends JpaRepository<Logistica, Integer> {
    List<Logistica>findByIdPedido(int idPedido);
}
