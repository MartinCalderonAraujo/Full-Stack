package com.sucursal.service.sucursal_service;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sucursal.service.sucursal_service.entidades.Sucursal;
import com.sucursal.service.sucursal_service.modelos.Usuario;
import com.sucursal.service.sucursal_service.repositorio.SucursalRepository;
import com.sucursal.service.sucursal_service.servicio.SucursalService;

@SpringBootTest
class SucursalServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private SucursalRepository sucursalRepository;
	@InjectMocks
	private SucursalService sucursalService;

	@Test
   public void testGetAll() {
      Sucursal s1 = new Sucursal();
      s1.setIdSucursal(1);
      s1.setNombreSucursal("Sede1");
      s1.setDireccionSucursal("Av Siempreviva 742");

      Sucursal s2 = new Sucursal();
      s2.setIdSucursal(2);
      s2.setNombreSucursal("Sede2");
      s2.setDireccionSucursal("CalleInventada 123");

      Mockito.when(this.sucursalRepository.findAll()).thenReturn(Arrays.asList(s1, s2));
      List<Sucursal> resultado = this.sucursalService.getAll();
	  Assertions.assertEquals(2, resultado.size());
      Assertions.assertEquals("Sede1", ((Sucursal)resultado.get(1)).setNombreSucursal());
   }

   @Test
   public void TestCrearSucursal_NombreNull() {
      Sucursal s1 = new Sucursal();
      s1.setIdSucursal(1);
      s1.setNombreSucursal("Sede1");
      s1.setDireccionSucursal("Av Siempreviva 742");
      Exception ex = (Exception)Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	this.sucursalService.crearSucursalPersonalizada(s1);
      	});
      Assertions.assertEquals("El Nombre del Sucursal no puede estar vacío", ex.getMessage());
   }

   @Test
   public void TestCrearSucursal_NombreEmpty() {
      Sucursal s1 = new Sucursal();
      s1.setIdSucursal(1);
      s1.setNombreSucursal("Sede1");
      s1.setDireccionSucursal("Av Siempreviva 742");
      Exception ex = (Exception)Assertions.assertThrows(IllegalArgumentException.class, () -> {
         this.sucursalService.crearSucursalPersonalizada(s1);
      });
      Assertions.assertEquals("El Nombre del Usuario no puede estar vacío", ex.getMessage());
   }

}
