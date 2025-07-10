package com.microservicio.roles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.microservicio.roles.entidades.AsignacionUsuarioRol;
import com.microservicio.roles.entidades.Rol;
import com.microservicio.roles.repositorio.RepositorioAsignacionUsuarioRol;
import com.microservicio.roles.repositorio.RepositorioRol;
import com.microservicio.roles.servicio.AsignacionUsuarioRolService;

public class AsignacionUsuarioRolServicioTesting {

    @Mock
    private  RepositorioAsignacionUsuarioRol repositorioAsignacionUsuarioRol;
    @Mock
    private RepositorioRol repositorioRol;  
    @InjectMocks
    private AsignacionUsuarioRolService asignacionUsuarioRolService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
// ----------------- TESTS DE ASIGNAR ROL ----------------
    @Test
    public void TestAsignarRol_RolIdNull(){
        //se busca probar que pasa si Rol existe pero tiene id Null
        Rol r1 = new Rol();
        r1.setRolId(null);
        r1.setRolNombre("ROL_SIN_ID");
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1);
        UR1.setRol(r1);
            //Aqui se testea el metodo del servicio y debe retornar un exception en particular porque no tiene id el rol
        Exception ex= assertThrows(IllegalArgumentException.class, () -> asignacionUsuarioRolService.asignarRol(UR1));
            //respuesta deseada que se busca validar:
        assertEquals("El rol es obligatorio", ex.getMessage());
    }

    @Test
    public void TestAsignarRol_NullRol(){
        //se busca probar que pasa si AsignarUsuarioRol no viene con una Instancia Rol adjunta
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1);
        UR1.setRol(null);
        Exception ex= assertThrows(IllegalArgumentException.class, () -> asignacionUsuarioRolService.asignarRol(UR1));
            //respuesta deseada que se busca validar:
        assertEquals("El rol es obligatorio", ex.getMessage());
    }

    @Test
    public void TestAsignarRol_RolNotFound(){
        //se busca probar que pasa si el json esta bien adjuntado PERO el rol NO existe dentro de la BD
        Rol r1 = new Rol();
        r1.setRolId(69);
        r1.setRolNombre("ROL_INEXISTENTE");
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1);
        UR1.setRol(r1);
        //aqui  buscar en la BD retornara nulo, es decir, NO esta registrado en la BD
        when(repositorioRol.findById(69)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> asignacionUsuarioRolService.asignarRol(UR1));
        

        assertEquals("Rol no encontrado: 69", ex.getMessage());
    }
@Test
    public void TestAsignarRol_UsuarioRepeated(){
        //se busca probar un caso con rol correcto pero id repetido (ya con un rol asignado)
        //USUARIOROL ya registrado (UR1)
        Rol r1 = new Rol();
        r1.setRolId(1);
        r1.setRolNombre(null); 
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1);
        UR1.setRol(r1);
        when(repositorioAsignacionUsuarioRol.existsByUsuarioId(UR1.getUsuarioId())).thenReturn(true);

        //aqui se simula el usuario consultado que YA existe en la BD )
        Exception ex= assertThrows(IllegalArgumentException.class, () -> asignacionUsuarioRolService.asignarRol(UR1));
        // aqui se  busca por rol id 1 y retorna el ROL pre-existente r2 correspondiente a la id
        // Una vez el flujo valido se termino, se validan los datos y que el rol este COMPLETO
        assertEquals("El usuario ya tiene un rol asignado", ex.getMessage());
    }

    @Test
    public void TestAsignarRol_ValidCase(){
        //se busca probar un caso Correcto con Id de Rol existente e ID usuario
        Rol r1 = new Rol();
        r1.setRolId(1);
        r1.setRolNombre(null); 
        //aqui se simula el Rol consultado que SÍ existe en la BD (devuelto por el repositorio tras consultar mediante id de r1)
        Rol r2 = new Rol();
        r2.setRolId(1);
        r2.setRolNombre("admin");
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1);
        UR1.setRol(r1);
        // aqui se  busca por rol id 1 y retorna el ROL pre-existente r2 correspondiente a la id
        when(repositorioAsignacionUsuarioRol.existsByUsuarioId(UR1.getUsuarioId())).thenReturn(false);
        when(repositorioRol.findById(1)).thenReturn(Optional.of(r2));
        when(repositorioAsignacionUsuarioRol.save(UR1)).thenReturn(UR1);
        AsignacionUsuarioRol CasoValido = asignacionUsuarioRolService.asignarRol(UR1);
        // Una vez el flujo valido se termino, se validan los datos y que el rol este COMPLETO
        assertEquals(UR1, CasoValido);
        assertEquals("admin", CasoValido.getRol().getRolNombre());
    }
// ----------------- TESTS DE OBTENER ROL DE USUARIO ----------------
    //caso en que soliciten un usuario que noexiste
    @Test
    public void TestingObtenerRolDeUsuario_NoUser(){
    when(repositorioAsignacionUsuarioRol.findByUsuarioId(1)).thenReturn(Optional.empty());

    Optional<Rol> UR1 = asignacionUsuarioRolService.obtenerRolDeUsuario(1);

    // Verifica que está presente y que es el correcto
    assertEquals(false, UR1.isPresent());
    }

    //caso en que soliciten un usuario que NO tenga un rol
    @Test
    public void TestingObtenerRolDeUsuario_NullRol(){
    Rol r1 = new Rol();
        r1.setRolId(null);
        r1.setRolNombre(null);

    AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
    UR1.setUsuarioId(420);
    UR1.setRol(r1);

    when(repositorioAsignacionUsuarioRol.findByUsuarioId(420)).thenReturn(Optional.of(UR1));

    Optional<Rol> CasoNullRol = asignacionUsuarioRolService.obtenerRolDeUsuario(420);

    // Verifica que está presente pero no tiene un rol asociado
    assertEquals(true, CasoNullRol.isPresent());
    assertEquals(null, CasoNullRol.get().getRolNombre());
    }

    //caso valido donde usuario existe y entrega el nombre del rol correspondiente
    @Test
    public void TestingObtenerRolDeUsuario_CasoValido(){
        //creamos el rol
    Rol r1 = new Rol();
        r1.setRolId(1);
        r1.setRolNombre("admin");
    // creamos el Asignarusuariorol que devolvera la busqueda
    AsignacionUsuarioRol    UR1 = new AsignacionUsuarioRol();
    UR1.setUsuarioId(34);
    UR1.setRol(r1);
        //retorna UR1 al buscar por usuario con id 34
    when(repositorioAsignacionUsuarioRol.findByUsuarioId(34)).thenReturn(Optional.of(UR1));
        // llama al servicio y guarda en variable CasoValido el rol o NULL.
    Optional<Rol> CasoValido = asignacionUsuarioRolService.obtenerRolDeUsuario(34);

    // Verifica que está presente y que es el correcto
    assertEquals(true, CasoValido.isPresent());
    assertEquals("admin", CasoValido.get().getRolNombre());
    }
//------------------- TESTS DE ELIMINAR ASIGNACIÓN ----------
//Aqui elimina al usuario que SI tenga una asignación.
    @Test
    public void TestEliminarAsignacion_CasoValido(){
        Rol r1 = new Rol();
        r1.setRolId(119);
        r1.setRolNombre("Presidente de Chile");
        AsignacionUsuarioRol UR1 = new AsignacionUsuarioRol();
        UR1.setUsuarioId(1973);
        UR1.setRol(r1);

        // cuando busca por id el usuario retornara UR1
        when(repositorioAsignacionUsuarioRol.findByUsuarioId(1973)).thenReturn(Optional.of(UR1));

        // Ejecuta el servicio que debe eliminar la democracia
        asignacionUsuarioRolService.eliminarAsignacion(1973);

        //Esto confirma que el metodo delete fue ejecutado con UR1
        org.mockito.Mockito.verify(repositorioAsignacionUsuarioRol).delete(UR1);
    }

    // caso en que el usuario NO tiene ninguna asignación registrada
    @Test
    public void TestEliminarAsignacion_NotFound(){
        //cuando se busca al usuario 404, no se encuentra, retorna un empty
        when(repositorioAsignacionUsuarioRol.findByUsuarioId(404)).thenReturn(Optional.empty());
        
        asignacionUsuarioRolService.eliminarAsignacion(404);
        // Verifica que NO se llamo al metodo delete.
        org.mockito.Mockito.verify(repositorioAsignacionUsuarioRol, org.mockito.Mockito.never()).delete(org.mockito.Mockito.any());
    }
    }





