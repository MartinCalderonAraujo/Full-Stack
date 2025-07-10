package descuentoscupones.service.descuentos_cupones_service.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import descuentoscupones.service.descuentos_cupones_service.entidades.Cupon;
import descuentoscupones.service.descuentos_cupones_service.repositorio.CuponRepository;
import descuentoscupones.service.descuentos_cupones_service.servicio.CuponService;

@RestController
@RequestMapping("/cupon")
public class CuponController {
    @Autowired
    private CuponService cuponServicio;

    private final CuponRepository cuponRepository;
 
    CuponController(CuponRepository cuponRepository){
        this.cuponRepository = cuponRepository;
    }

    @GetMapping("/{idCupon}")
    public ResponseEntity<EntityModel<Cupon>> listarCupones(@PathVariable("idCupon")int idCupon){
        Cupon cupon = cuponServicio.getCuponById(idCupon);
        if(cupon==null){
            return ResponseEntity.notFound().build();
        }else{
            EntityModel<Cupon>recurso= EntityModel.of(cupon);
            recurso.add(linkTo(methodOn(CuponController.class).obtenerCupon(idCupon)).withSelfRel());
            recurso.add(linkTo(methodOn(CuponController.class).listarCupones()).withRel("todos-los-comprobantes"));
            recurso.add(linkTo(methodOn(CuponController.class).eliminarCupon(idCupon)).withRel("eliminar-cupon-por-cuponId"));
            recurso.add(linkTo(methodOn(CuponController.class).guardarCupones(cupon)).withRel("guardar-cupon-por-cuponId"));
            return ResponseEntity.ok(recurso);
        }
        
    }
    
    //Listar cupones
    @GetMapping("/listarCupones")
    public ResponseEntity<CollectionModel<EntityModel<Cupon>>> listarCupones(){
        List<Cupon> cupon = cuponServicio.getAll();
        if (cupon.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<Cupon>> recursos = cupon.stream()
            .map(l -> EntityModel.of(l,
                    linkTo(methodOn(CuponController.class).obtenerCupon(l.getIdCupon())).withSelfRel(),
                    linkTo(methodOn(CuponController.class).listarCupones()).withRel("todos-los-cupones")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }

    //Obtener cupon por id
    @GetMapping("/obtenerCupon/{idCupon}")
    public ResponseEntity<EntityModel<Cupon>> obtenerCupon(@PathVariable("idCupon") int idCupon){
        Cupon cupon = cuponServicio.getCuponById(idCupon);
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Cupon> recurso = EntityModel.of(cupon);
        recurso.add(linkTo(methodOn(CuponController.class).obtenerCupon(idCupon)).withSelfRel());
        recurso.add(linkTo(methodOn(CuponController.class).listarCupones()).withRel("todos-los-envios"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping("/aplicarCupon/{idCupon}/{idProducto}")
    public ResponseEntity<Double> aplicarCupon(@PathVariable int idProducto, @RequestBody Cupon cupon) {
        double nuevoPrecio = cuponServicio.calcularValorDescuento(idProducto, cupon);
        return ResponseEntity.ok(nuevoPrecio);
    }

    //Elimina cupones por el idCupon
    @DeleteMapping("/eliminarCupon/{idCupon}")
    public ResponseEntity<Cupon> eliminarCupon(@PathVariable("idCupon") int idCupon){
        Cupon cupon = cuponServicio.getCuponById(idCupon);
        if(cupon == null){
            return ResponseEntity.notFound().build();
        }
        cuponServicio.deleteCuponById(idCupon);
        return ResponseEntity.noContent().build();
    }

    //Guardar cupones
    @PostMapping
    public ResponseEntity<EntityModel<Cupon>> guardarCupones(@RequestBody Cupon cupon){
        Cupon nuevoCupon = cuponServicio.saveCupon(cupon);

        EntityModel<Cupon> recurso = EntityModel.of(nuevoCupon);
        recurso.add(linkTo(methodOn(CuponController.class).obtenerCupon(nuevoCupon.getIdCupon())).withSelfRel());
        recurso.add(linkTo(methodOn(CuponController.class).listarCupones()).withRel("todos-los-cupones"));
        return ResponseEntity.ok(recurso);
    }
}