package com.sucursal.service.sucursal_service.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sucursal.service.sucursal_service.entidades.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal,Integer>{
    List<Sucursal>findByUsuarioId(int usuarioId);
    List<Sucursal>findByProductoId(int idProducto);
    List<Sucursal> findByComprobanteId(int comprobanteId);
    boolean existsByNombreSucursal(String nombre);

}
