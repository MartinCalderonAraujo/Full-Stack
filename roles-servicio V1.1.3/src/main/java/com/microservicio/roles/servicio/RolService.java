package com.microservicio.roles.servicio;

import com.microservicio.roles.entidades.Rol;
import com.microservicio.roles.repositorio.RepositorioRol;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    private final RepositorioRol repositorioRol;

    public RolService(RepositorioRol repositorioRol) {
        this.repositorioRol = repositorioRol;
    }

    //ojito aca para el testing
    // SIEMPRE registrara roles en minusculas ojito con esto
    public Rol crearRol(Rol rol) {
         String nombre = Optional.ofNullable(rol.getRolNombre()).map(String::trim).map(String::toLowerCase).orElse(null);
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de rol no puede estar vacío");
        }
        if (repositorioRol.existsByRolNombre(nombre) == true) {
            throw new IllegalArgumentException("El nombre de rol ya existe");
        }
        rol.setRolNombre(nombre);
        return repositorioRol.save(rol);
    }

    public Optional<Rol> obtenerRolPorId(Integer id) {
        return repositorioRol.findById(id);
    }
}
