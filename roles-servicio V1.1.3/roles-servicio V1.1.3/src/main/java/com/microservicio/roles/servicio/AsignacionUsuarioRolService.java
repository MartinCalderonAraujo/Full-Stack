package com.microservicio.roles.servicio;

import com.microservicio.roles.entidades.AsignacionUsuarioRol;
import com.microservicio.roles.entidades.Rol;
import com.microservicio.roles.repositorio.RepositorioAsignacionUsuarioRol;
import com.microservicio.roles.repositorio.RepositorioRol;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsignacionUsuarioRolService {

    private final RepositorioAsignacionUsuarioRol repositorioAsignacion;
    private final RepositorioRol repositorioRol;

    public AsignacionUsuarioRolService(RepositorioAsignacionUsuarioRol repositorioAsignacion,
                                       RepositorioRol repositorioRol) {
        this.repositorioAsignacion = repositorioAsignacion;
        this.repositorioRol = repositorioRol;
    }
//Esto verifica primero "ASIGNACION" (que se envia desde el controlador)
//si retorna rol nulo, o que el rol tenga una id nulo, es decir valida que el rol NO sea nulo 
    public AsignacionUsuarioRol asignarRol(AsignacionUsuarioRol asignacion) {
        if (asignacion.getRol() == null || asignacion.getRol().getRolId() == null) {
            throw new IllegalArgumentException("El rol es obligatorio");}
            //aqui valida que el usuario ya no este en la BD, es decir, tenga un unico rol
        if (repositorioAsignacion.existsByUsuarioId(asignacion.getUsuarioId()) == true){
            throw new IllegalArgumentException("El usuario ya tiene un rol asignado");
        }
        
        // aqui crea un entero "roleid" que copia el valor del id del Rol de asignacion
        Integer roleId = asignacion.getRol().getRolId();
        //ahora nos aseguramos el rol exista dentro de la BD, si no existe, se ejecutara el orElseThrow
        Rol rol = repositorioRol.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + roleId));
        //si el rol existe y es valida, a ASIGNACION le asigna la instancia "rol" con el resto de atributos de ROL necesarios para la BD
        asignacion.setRol(rol);
        //retorna la instancia asignacion ya con la instancia ROL establecida correctamente y listo para ser guardados en la BD
        return repositorioAsignacion.save(asignacion);
    }
    //recordatorio que .map solo se ejecuta si el valor ROL NO ES NULL eso quiere decir que si contiene "algo", 
    //entonces ejecuta la linea dentro de .map(------) sobre ese "algo"
    public Optional<Rol> obtenerRolDeUsuario(Integer usuarioId) {
        return repositorioAsignacion.findByUsuarioId(usuarioId)
                .map(AsignacionUsuarioRol::getRol);
    }

    public void eliminarAsignacion(Integer usuarioId) {
        repositorioAsignacion.findByUsuarioId(usuarioId)
                .ifPresent(repositorioAsignacion::delete);
    }
}
