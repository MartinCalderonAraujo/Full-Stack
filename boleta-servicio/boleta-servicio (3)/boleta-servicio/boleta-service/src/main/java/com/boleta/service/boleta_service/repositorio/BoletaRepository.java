package com.boleta.service.boleta_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boleta.service.boleta_service.entidades.Comprobante;

@Repository
public interface BoletaRepository extends JpaRepository<Comprobante,Integer>{
    List<Comprobante>findByUsuarioId(int usuarioId);
    List<Comprobante>findByIdPedido(int idPedido);


}
