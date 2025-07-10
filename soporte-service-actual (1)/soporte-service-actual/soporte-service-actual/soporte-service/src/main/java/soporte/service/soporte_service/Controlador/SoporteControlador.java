package soporte.service.soporte_service.Controlador;

import java.util.List;
import java.util.Map;
import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soporte.service.soporte_service.Entidades.Soporte;
import soporte.service.soporte_service.Repository.SoporteRepository;
import soporte.service.soporte_service.Servicio.SoporteServicio;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/soporte")
public class SoporteControlador {

    private final SoporteRepository soporteRepository;
    @Autowired

    private SoporteServicio soporteServicio;

    SoporteControlador(SoporteRepository soporteRepository){
        this.soporteRepository=soporteRepository;
    }

    @GetMapping("/{idSoporte}")
    public ResponseEntity<EntityModel<Soporte>> obtenerSoporte(@PathVariable("idSoporte")int idSoporte){
        Soporte soporte = soporteServicio.getSoporteById(idSoporte);
        if(soporte==null){
            return ResponseEntity.notFound().build();
        }else{
        EntityModel<Soporte>recurso= EntityModel.of(soporte);
        recurso.add(linkTo(methodOn(SoporteControlador.class).obtenerSoporte(idSoporte)).withSelfRel());
        recurso.add(linkTo(methodOn(SoporteControlador.class).listarSoporte()).withRel("todos-los-comprobantes"));
        recurso.add(linkTo(methodOn(SoporteControlador.class).guardarSoporte(soporte)).withRel("guardar-soporte"));
        recurso.add(linkTo(methodOn(SoporteControlador.class).eliminarSoporte(idSoporte)).withRel("eliminar-comprobante-por-comprobanteId"));
        recurso.add(linkTo(methodOn(SoporteControlador.class).respuestaSoporte(idSoporte,(Map<String,String>)null)).withRel("respuesta-soporte"));
        return ResponseEntity.ok(recurso);
        }
    }

    @GetMapping
    public ResponseEntity<List<Soporte>> listarSoporte(){
        List<Soporte>soportes=soporteServicio.getAll();
        if(soportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PostMapping
    public ResponseEntity<Soporte> guardarSoporte(@RequestBody Soporte soporte){
        Soporte newSoporte=soporteServicio.save(soporte);
        return ResponseEntity.ok(newSoporte);
    }

    @DeleteMapping("/{idSoporte}")
    public ResponseEntity<Soporte> eliminarSoporte(@PathVariable("idSoporte")int idSoporte) {
        Soporte soporte = soporteServicio.getSoporteById(idSoporte);
        if(soporte==null){
            return ResponseEntity.notFound().build();
        }
        soporteServicio.deleteSoporteById(idSoporte);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Soporte>> listarSoportesPorUsuario(@PathVariable("usuarioId")int usuarioId){
        List<Soporte>soportes=soporteServicio.byUsuarioId(usuarioId);
        if(soportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(soportes);
    }

    @PatchMapping("/{idSoporte}")
    public ResponseEntity<Soporte> respuestaSoporte(@PathVariable("idSoporte") int idSoporte, @RequestBody Map<String,String>cambios) {
        Soporte soporteExistente = soporteServicio.getSoporteById(idSoporte);
        if (soporteExistente == null) {return ResponseEntity.notFound().build();
        }
        if (cambios.containsKey("respuestaDeSolicitud")) {
        soporteExistente.setRespuestaDeSolicitud(cambios.get("respuestaDeSolicitud"));
        Soporte actualizado = soporteServicio.save(soporteExistente);
        return ResponseEntity.ok(actualizado);
    }
        return ResponseEntity.badRequest()
        .body(soporteExistente);
    }
}


