package com.microservicio.roles.controlador;

import com.microservicio.roles.servicio.RolService;
import com.microservicio.roles.entidades.AsignacionUsuarioRol;
import com.microservicio.roles.entidades.Rol;
import com.microservicio.roles.servicio.AsignacionUsuarioRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class ControladorRol {

    private final RolService rolService;
    private final AsignacionUsuarioRolService asignacionService;

    public ControladorRol(RolService rolService,
                          AsignacionUsuarioRolService asignacionService) {
        this.rolService = rolService;
        this.asignacionService = asignacionService;
    }

    /**
     * POST /roles
     * Crea un nuevo rol.
     */
    @PostMapping
    public ResponseEntity<EntityModel<Rol>> crearRol(@RequestBody Rol rol) {
        try {
            Rol creado = rolService.crearRol(rol);
            return ResponseEntity.ok(
                EntityModel.of(creado,
                    linkTo(methodOn(ControladorRol.class)
                        .obtenerRolPorId(creado.getRolId()))
                        .withSelfRel()
                )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /roles/{id}
     * Obtiene un rol por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> obtenerRolPorId(@PathVariable Integer id) {
        Optional<Rol> resultado = rolService.obtenerRolPorId(id);
        if (resultado.isPresent()) {
            Rol r = resultado.get();
            return ResponseEntity.ok(
                EntityModel.of(r,
                    linkTo(methodOn(ControladorRol.class)
                        .obtenerRolPorId(id))
                        .withSelfRel()
                )
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /roles/asignar
     * Asigna un rol a un usuario.
     */
    @PostMapping("/asignar")
    public ResponseEntity<AsignacionUsuarioRol> asignarRolAUsuario(
        //Convierte el input del @requestbody, en una instancia AsignacionUsuarioRol y lo nombra ASIGNACION, no olvidar ese nombre clave.
        //aclaración, la instancia  ASIGNACION tiene los atributos de su clase pero de ROL solo tiene el ID, esta incompleto.
            @RequestBody AsignacionUsuarioRol asignacion) {
        try {
            //guarda otra instancia llamada GUARDADA que sera igual a lo que retorne el servicio "asignacionService" 
            //cuando le entregas ASIGNACION, Aqui debes dirigirte al servicio AsignacionUsurarioRol
            AsignacionUsuarioRol guardada = asignacionService.asignarRol(asignacion);
            return ResponseEntity.ok(guardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /roles/usuario/{usuarioId}
     * Consulta el rol asignado a un usuario.
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<EntityModel<Rol>> obtenerRolDeUsuario(@PathVariable Integer usuarioId) {
        Optional<Rol> rolOptional = asignacionService.obtenerRolDeUsuario(usuarioId);
        if (rolOptional.isPresent()) {
            Rol r = rolOptional.get();
            return ResponseEntity.ok(
                EntityModel.of(r,
                    linkTo(methodOn(ControladorRol.class)
                        .obtenerRolDeUsuario(usuarioId))
                        .withSelfRel()
                )
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /roles/asignar/{usuarioId}
     * Elimina la asignación de rol de un usuario.
     */
    @DeleteMapping("/asignar/{usuarioId}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Integer usuarioId) {
        Optional<Rol> rolOptional = asignacionService.obtenerRolDeUsuario(usuarioId);
        if (rolOptional.isPresent()) {
            asignacionService.eliminarAsignacion(usuarioId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}