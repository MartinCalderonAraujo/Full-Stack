package com.logistica.logistica_servicio.controlador;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.logistica_servicio.entidades.Logistica;
import com.logistica.logistica_servicio.repositorio.LogisticaRepositorio;
import com.logistica.logistica_servicio.servicio.LogisticaServicio;

@RestController
@RequestMapping("/logistica")
public class LogisticaControlador {

    private final LogisticaRepositorio logisticaRepositorio;

    @Autowired
    private LogisticaServicio logisticaServicio;

    LogisticaControlador(LogisticaRepositorio logisticaRepositorio) {
        this.logisticaRepositorio = logisticaRepositorio;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Logistica>> crear(@RequestBody Logistica logistica) {
        Logistica nuevaLogistica = logisticaServicio.guardar(logistica);

        EntityModel<Logistica> recurso = EntityModel.of(nuevaLogistica);
        recurso.add(linkTo(methodOn(LogisticaControlador.class).obtenerLogistica(nuevaLogistica.getIdEnvio())).withSelfRel());
        recurso.add(linkTo(methodOn(LogisticaControlador.class).listarLogisticas()).withRel("todos-los-envios"));

        return ResponseEntity.ok(recurso);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Logistica>>> listarLogisticas() {
        List<Logistica> logisticas = logisticaServicio.getAll();
        if (logisticas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Logistica>> recursos = logisticas.stream()
            .map(l -> EntityModel.of(l,
                    linkTo(methodOn(LogisticaControlador.class).obtenerLogistica(l.getIdEnvio())).withSelfRel(),
                    linkTo(methodOn(LogisticaControlador.class).listarLogisticas()).withRel("todos-los-envios")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }

    @GetMapping("/{idEnvio}")
    public ResponseEntity<EntityModel<Logistica>> obtenerLogistica(@PathVariable("idEnvio") int idEnvio) {
        Logistica logistica = logisticaServicio.buscarPorId(idEnvio);
        if (logistica == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Logistica> recurso = EntityModel.of(logistica);
        recurso.add(linkTo(methodOn(LogisticaControlador.class).obtenerLogistica(idEnvio)).withSelfRel());
        recurso.add(linkTo(methodOn(LogisticaControlador.class).listarLogisticas()).withRel("todos-los-envios"));

        return ResponseEntity.ok(recurso);
    }

    @PutMapping("/{idEnvio}")
    public ResponseEntity<EntityModel<Logistica>> actualizar(@PathVariable int idEnvio, @RequestBody Logistica logistica) {
        try {
            Logistica logisticaActualizada = logisticaServicio.actualizar(idEnvio, logistica);

            EntityModel<Logistica> recurso = EntityModel.of(logisticaActualizada);
            recurso.add(linkTo(methodOn(LogisticaControlador.class).obtenerLogistica(idEnvio)).withSelfRel());
            recurso.add(linkTo(methodOn(LogisticaControlador.class).listarLogisticas()).withRel("todos-los-envios"));

            return ResponseEntity.ok(recurso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEnvio}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable("idEnvio") int idEnvio) {
        Logistica logistica = logisticaServicio.buscarPorId(idEnvio);
        if (logistica == null) {
            return ResponseEntity.notFound().build();
        }

        logisticaServicio.eliminar(idEnvio);
        return ResponseEntity.noContent().build();
    }
}
