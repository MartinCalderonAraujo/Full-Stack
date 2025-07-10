package com.sucursal.service.sucursal_service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sucursal.service.sucursal_service.entidades.Sucursal;
import com.sucursal.service.sucursal_service.repositorio.SucursalRepository;
import com.sucursal.service.sucursal_service.servicio.SucursalService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @SuppressWarnings("unused")
    private final SucursalRepository sucursalRepository;

    @Autowired
    private SucursalService sucursalService;

    SucursalController(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    /*---------------------------------------------------------------------------------------------*/

    /* OBTENER SUCURSAL */
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(@PathVariable("id")int id) {
        Sucursal sucursal = sucursalService.getSucursalById(id);

        if(sucursal==null){
            return ResponseEntity.notFound().build();
        }else{
        EntityModel<Sucursal>recurso= EntityModel.of(sucursal);

        recurso.add(linkTo(methodOn(SucursalController.class).obtenerSucursal(id)).withSelfRel());
        recurso.add(linkTo(methodOn(SucursalController.class).listarSucursal()).withRel("todos-los-usuarios"));

        return ResponseEntity.ok(sucursal);
        }
    }

    /* ELIMINAR SUCURSAL */
    @DeleteMapping("/{id}")
    public ResponseEntity<Sucursal> eliminarSucursal(@PathVariable("id")int id) {
        Sucursal sucursal = sucursalService.getSucursalById(id);
    
        if(sucursal==null){
            return ResponseEntity.notFound().build();
        }
        sucursalService.deleteSucursalById(id);
        return ResponseEntity.noContent().build();
    }

    /* LISTAR SUCURSAL */
    @GetMapping
    public ResponseEntity<List<Sucursal>> listarSucursal(){

        List<Sucursal> sucursales = sucursalService.getAll();
        
        if (sucursales.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
        CollectionModel<Sucursal>recurso= CollectionModel.of(sucursales);
        
        recurso.add(linkTo(methodOn(SucursalController.class).listarSucursal()).withSelfRel());

        return ResponseEntity.ok(sucursales);
        }
    }

    /* GUARDAR SUCURSAL */
    @PostMapping
    public ResponseEntity<Sucursal> guardarSucursal(@RequestBody Sucursal sucursal){
        
        Sucursal nuevaSucursal = sucursalService.save(sucursal);
        return ResponseEntity.ok(nuevaSucursal);
    }

    /*---------------------------------------------------------------------------------------------*/

    /* LISTAR USUARIO */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Sucursal>> listarUsuarioPorSucursal(@PathVariable("usuarioId")int usuarioId){
        List<Sucursal>sucursales = sucursalService.getByUsuarioId(usuarioId);
        if(sucursales.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }
    
    /* LISTAR PRODUCTO */
    @GetMapping("/productos/{idProducto}")
    public ResponseEntity<List<Sucursal>> listarProducto(@PathVariable("idProducto")int id){
        
        List<Sucursal> sucursales = sucursalService.getByProductoId(id);

        if(sucursales == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursales);
    }
}
    
    
    

