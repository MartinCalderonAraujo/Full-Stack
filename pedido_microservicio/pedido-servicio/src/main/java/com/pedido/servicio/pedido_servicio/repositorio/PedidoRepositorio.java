package com.pedido.servicio.pedido_servicio.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedido.servicio.pedido_servicio.entidades.Pedido;



@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

    List<com.pedido.servicio.pedido_servicio.entidades.Pedido> findByEstadoPedido(String estadoPedido);

    List<Pedido> findByFechaPedidoBetween(LocalDate startDate, LocalDate endDate);
    List<Pedido>findByIdProducto(int idProducto);
}