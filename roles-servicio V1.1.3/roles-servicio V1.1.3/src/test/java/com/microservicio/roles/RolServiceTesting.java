package com.microservicio.roles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.microservicio.roles.entidades.Rol;
import com.microservicio.roles.repositorio.RepositorioRol;
import com.microservicio.roles.servicio.RolService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RolServiceTesting {

    @Mock
    private RepositorioRol repositorioRol;

    @InjectMocks
    private RolService rolService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
    public void TestCrearRol_NombreNull() {
        Rol r1 = new Rol();
        r1.setRolNombre(null);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> rolService.crearRol(r1));
        assertEquals("El nombre de rol no puede estar vacío", ex.getMessage());
    }

    @Test
    public void TestCrearRol_NombreEmpty() {
        Rol r1 = new Rol();
        r1.setRolNombre("   ");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> rolService.crearRol(r1));
        assertEquals("El nombre de rol no puede estar vacío", ex.getMessage());
    }

    @Test
    public void TestCrearRol_NombreRepeated() {
        Rol r1 = new Rol();
        r1.setRolNombre("Gerente");

        when(repositorioRol.existsByRolNombre("gerente")).thenReturn(true);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> rolService.crearRol(r1));
        assertEquals("El nombre de rol ya existe", ex.getMessage());
    }

    @Test
    public void TestCrearRol_Valido() {
        Rol r1 = new Rol();
        r1.setRolNombre("Admin");

        when(repositorioRol.existsByRolNombre("admin")).thenReturn(false);
        when(repositorioRol.save(r1)).thenReturn(r1);

        Rol rolValido = rolService.crearRol(r1);
        assertEquals("admin", rolValido.getRolNombre());
        assertEquals(r1, rolValido);
    }
}