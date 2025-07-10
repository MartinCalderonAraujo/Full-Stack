package producto.servicio.producto_servicio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import producto.servicio.producto_servicio.entidades.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    List<Producto> findByUsuarioId(int usuarioId);
}
