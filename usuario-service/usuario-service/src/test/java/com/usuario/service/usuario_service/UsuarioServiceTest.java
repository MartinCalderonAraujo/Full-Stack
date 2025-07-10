package com.usuario.service.usuario_service;

import com.usuario.service.usuario_service.entidades.Usuario;
import com.usuario.service.usuario_service.repositorio.UsuarioRepository;
import com.usuario.service.usuario_service.servicio.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class UsuarioServiceTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @InjectMocks
  private UsuarioService usuarioService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  public void TestCrearUsuario() {

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre("Lia");
    u1.setUsuarioEmail("Lia@duoc.cl");

  }


  @Test
   public void testGetAll() {

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre("Lia");
    u1.setUsuarioEmail("Lia@duoc.cl");
    
    Usuario u2 = new Usuario();
    u2.setUsuarioId(2);
    u2.setUsuarioNombre("Pepe");
    u2.setUsuarioEmail("Pepe@duoc.cl");

    Mockito.when(this.usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));
    List<Usuario> resultado = this.usuarioService.getAll();
    Assertions.assertEquals(2, resultado.size());
    Assertions.assertEquals("Pepe", ((Usuario)resultado.get(1)).getUsuarioNombre());
  }


  @Test
  public void TestCrearUsuario_NombreNull() {

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre(null);
    u1.setUsuarioEmail("Lia@duoc.cl");

    Exception ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuarioPersonalizado(u1));
    assertEquals("El Nombre del Usuario no puede estar vacío", ex.getMessage());
  }

  /*
  @Test
  public void TestCrearUsuario_RutNull(){

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre("Lia");
    u1.setUsuarioEmail("Lia@duoc.cl");
    u1.setUsuarioRut(111);

    Exception ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuarioPersonalizado(u1));
      assertEquals("El Rut del Usuario no puede estar vacío", ex.getMessage());
  }
  */


  @Test
  public void TestCrearUsaurio_NombreEmpty() {

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre(" ");
    u1.setUsuarioEmail("Lia@duoc.cl");

    Exception ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuarioPersonalizado(u1));
    assertEquals("El Nombre del Usuario no puede estar vacío", ex.getMessage());
  }

  /*
  @Test
  public void TestCrearUsuario_RutEmpty(){

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre("Lia");
    u1.setUsuarioEmail("Lia@duoc.cl");
    u1.setUsuarioRut();

    Exception ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuarioPersonalizado(u1));
      assertEquals("El Rut del Usuario no puede estar vacío", ex.getMessage());
  }
  */

  
  @Test
  public void TestCrearUsaurio_RutRepetido() {

    Usuario u1 = new Usuario();
    u1.setUsuarioId(1);
    u1.setUsuarioNombre(" ");
    u1.setUsuarioEmail("Lia@duoc.cl");
    u1.setUsuarioRut(111);
    
    Usuario u2 = new Usuario();
    u2.setUsuarioId(1);
    u2.setUsuarioNombre(" ");
    u2.setUsuarioEmail("Lia@duoc.cl");
    u2.setUsuarioRut(111);

    Exception ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.crearUsuarioPersonalizado(u2));
    assertEquals("El Rut asociado ya tiene una cuenta", ex.getMessage());
  }


}