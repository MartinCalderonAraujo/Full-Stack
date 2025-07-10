package soporte.service.soporte_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soporte.service.soporte_service.Entidades.Soporte;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte,Integer>{
    List<Soporte>findByUsuarioId(int usuarioId);
}
