package com.sucursal.service.sucursal_service.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*import org.springframework.web.client.RestTemplate;*/
import java.util.Optional;

import com.sucursal.service.sucursal_service.entidades.Sucursal;
import com.sucursal.service.sucursal_service.modelos.Producto;
import com.sucursal.service.sucursal_service.modelos.Usuario;
import com.sucursal.service.sucursal_service.repositorio.SucursalRepository;

@Service
public class SucursalService {

    
    @Autowired
    private RestTemplate restTemplate;
    
    @SuppressWarnings("unchecked")
    public List<Producto> getProductos(int idSucursal){
        List<Producto> productos = restTemplate.getForObject("http://localhost:8002/producto/sucursal/"+idSucursal, List.class);
        return productos;
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> getUsuarios(int id){
        List<Usuario> usuarios = restTemplate.getForObject("http://localhost:8001/usuario/sucursal/"+id, List.class);
        return usuarios;
    }
    
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> getAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal getSucursalById(int id){
        return sucursalRepository.findById(id).orElse(null);
    }

    public Sucursal save(Sucursal sucursal){
        Sucursal nuevaSucursal = sucursalRepository.save(sucursal);
        return nuevaSucursal;
    }

    public Sucursal crearSucursalPersonalizada(Sucursal sucursal){

        String nombre = Optional.ofNullable(sucursal.getNombreSucursal()).map(String::trim).map(String::toLowerCase).orElse(null);

        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de sucursal no puede estar vacío");
        }
        if (sucursalRepository.existsByNombreSucursal(nombre) == true) {
            throw new IllegalArgumentException("El nombre de sucursal ya existe");
        }
        
        sucursal.setNombreSucursal(nombre);
        return sucursalRepository.save(sucursal);
    }

    public void deleteSucursalById(int id){
        sucursalRepository.deleteById(id);
    }

    public List<Sucursal>getByUsuarioId(int usuarioId){
        return sucursalRepository.findByUsuarioId(usuarioId);
    }

    public List<Sucursal>getByProductoId(int idProducto){
        return sucursalRepository.findByProductoId(idProducto);
    }
    

    /*
    public List<Sucursal> findByComprobanteId(int comprobanteId) {
        return sucursalRepository.findByComprobanteId(comprobanteId);
    }
    */
}

