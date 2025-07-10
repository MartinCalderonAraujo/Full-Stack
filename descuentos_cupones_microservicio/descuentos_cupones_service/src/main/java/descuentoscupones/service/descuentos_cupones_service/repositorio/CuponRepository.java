package descuentoscupones.service.descuentos_cupones_service.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import descuentoscupones.service.descuentos_cupones_service.entidades.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Integer> {
    List<Cupon> findByIdProducto(int idProducto);
}
