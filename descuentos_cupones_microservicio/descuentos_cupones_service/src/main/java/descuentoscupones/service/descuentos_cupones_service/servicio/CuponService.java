package descuentoscupones.service.descuentos_cupones_service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import descuentoscupones.service.descuentos_cupones_service.entidades.Cupon;
import descuentoscupones.service.descuentos_cupones_service.modelo.Producto;
import descuentoscupones.service.descuentos_cupones_service.repositorio.CuponRepository;

@Service
public class CuponService {
    
    @Autowired
    private RestTemplate restTemplate;

    private final String urlProducto = "http://localhost:8002/producto"; // Asegúrate del puerto

    private Producto obtenerProductoPorId(int idProducto) {
        String url = urlProducto + "/" + idProducto;
        return restTemplate.getForObject(url, Producto.class);
    }

    public double calcularValorDescuento(int idProducto, Cupon cupon) {
        Producto producto = obtenerProductoPorId(idProducto);
        return producto.getPrecioProducto() * cupon.getDescuentoCupon();
    }

    @Autowired
    private CuponRepository cuponRepository;

    //get all
    public List<Cupon> getAll(){
        return cuponRepository.findAll();
    }
    
    //Obtener cupon por id
    public Cupon getCuponById(int idCupon){
        return cuponRepository.findById(idCupon).orElse(null);
    }

    //Guardar cupon
    public Cupon saveCupon(Cupon cupon){
        Cupon nuevoCupon = cuponRepository.save(cupon);
        return nuevoCupon;
    }

    //Eliminar cupon por el id
    public void deleteCuponById(int idCupon){
        cuponRepository.deleteById(idCupon);
    }
}
