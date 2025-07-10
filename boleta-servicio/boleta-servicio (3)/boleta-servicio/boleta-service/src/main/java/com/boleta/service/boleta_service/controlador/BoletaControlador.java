package com.boleta.service.boleta_service.controlador;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import com.boleta.service.boleta_service.entidades.Comprobante;
import com.boleta.service.boleta_service.entidades.UsuarioProducto;
import com.boleta.service.boleta_service.modelos.Usuario;
import com.boleta.service.boleta_service.repositorio.BoletaRepository;
import com.boleta.service.boleta_service.servicio.BoletaServicio;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/comprobante")
public class BoletaControlador {

    private final BoletaRepository boletaRepository;
    @Autowired

    private BoletaServicio boletaServicio;

    BoletaControlador(BoletaRepository boletaRepository){
        this.boletaRepository=boletaRepository;
    }


    @GetMapping("/{comprobanteId}")
    public ResponseEntity<EntityModel<Comprobante>> obtenerComprobante(@PathVariable("comprobanteId")int comprobanteId){
        Comprobante comprobante = boletaServicio.getBoletaById(comprobanteId);
        if(comprobante==null){
            return ResponseEntity.notFound().build();
        }else{
        EntityModel<Comprobante>recurso= EntityModel.of(comprobante);
        recurso.add(linkTo(methodOn(BoletaControlador.class).obtenerComprobante(comprobanteId)).withSelfRel());
        recurso.add(linkTo(methodOn(BoletaControlador.class).listarComprobantes()).withRel("todos-los-comprobantes"));
        recurso.add(linkTo(methodOn(BoletaControlador.class).listarComprobantesPorUsuario(comprobanteId)).withRel("comprobante-del-usuario"));
        recurso.add(linkTo(methodOn(BoletaControlador.class).eliminarComprobante(comprobanteId)).withRel("eliminar-comprobante-por-comprobanteId"));
        return ResponseEntity.ok(recurso);
        }
    }

    @DeleteMapping("/{comprobanteId}")
    public ResponseEntity<Usuario> eliminarComprobante(@PathVariable("comprobanteId")int comprobanteId) {
        Comprobante comprobante = boletaServicio.getBoletaById(comprobanteId);
        if(comprobante==null){
            return ResponseEntity.notFound().build();
        }
        boletaServicio.deleteComprobanteById(comprobanteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Comprobante>> listarComprobantes(){

        List<Comprobante>comprobantes=boletaServicio.getAll();
        if(comprobantes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comprobantes);
    }

    @PostMapping
    public ResponseEntity<Comprobante> guardarComprobante(@RequestBody Comprobante comprobante){
        Comprobante nuevoComprobante=boletaServicio.save(comprobante);
        return ResponseEntity.ok(nuevoComprobante);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comprobante>> listarComprobantesPorUsuario(@PathVariable("usuarioId")int usuarioId){
        List<Comprobante>comprobantes=boletaServicio.byUsuarioId(usuarioId);
        if(comprobantes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comprobantes);
    }

    @GetMapping("/usuariosConProductos/{comprobanteId}")
    public ResponseEntity<List<UsuarioProducto>> getUsuariosConProductos(@PathVariable int comprobanteId) {
        List<UsuarioProducto> usuarioProductos = boletaServicio.getUsuariosConProductos(comprobanteId);
        if (usuarioProductos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarioProductos);
    }
    //GET http://localhost:8004/comprobante/{comprobanteId}/usuariosConProductos
    //a futuro funcional
}
