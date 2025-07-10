package com.catalogo.producto.catalogo_producto_service;

import com.catalogo.producto.catalogo_producto_service.entidades.Producto;
import com.catalogo.producto.catalogo_producto_service.modelos.Busqueda;
import com.catalogo.producto.catalogo_producto_service.modelos.Rango;
import com.catalogo.producto.catalogo_producto_service.servicio.CatalogoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestingCatalogoService {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CatalogoService catalogoService;

    private List<Producto> productoTestLista;

    @BeforeEach
    public void setup() {
        Producto p1 = new Producto(1, "Perfume Floral", "Hugo Boss", 15000, 10,1);
        Producto p2 = new Producto(2, "Colonia Deportiva", "Adidas", 12000, 5,1);
        Producto p3 = new Producto(3, "Aroma Citrico", "Zara", 17000, 6,1);
        Producto p4 = new Producto(4, "Aroma Arandano", "Zara", 20000, 9,1);
        Producto p5 = new Producto(5, "Perfume PacoRobame", "Giorgio Armani", 42000, 1,1);
        //Fabrizio 
        Producto p6 = new Producto(6, "Perfume Barras BravVvas Signature", "Colo-Colo", 1312, 0, 1);



        productoTestLista = Arrays.asList(p1, p2, p3, p4, p5);
    }

    @Test
    public void SearchCaso1() {
        // Arrange
        Busqueda busquedaSimulated = new Busqueda();
        busquedaSimulated.setMarcaProducto("Zara");
        busquedaSimulated.setSearchLike("oma");
        busquedaSimulated.setRangoPrecio(new Rango(10000, 20000));

        when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
            .thenReturn(productoTestLista.toArray(new Producto[0]));

        // Act
        List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Aroma Citrico", resultado.get(0).getNombreProducto());
        assertEquals("Aroma Arandano", resultado.get(1).getNombreProducto());
    }

    @Test


    //caso 2 rango y marca
public void SearchCaso2() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setMarcaProducto("Zara");
    busquedaSimulated.setRangoPrecio(new Rango(15000, 21000));

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(2, resultado.size());
    assertEquals("Aroma Citrico", resultado.get(0).getNombreProducto());
    assertEquals("Aroma Arandano", resultado.get(1).getNombreProducto());
}

// caso 3 rango y searchLike
@Test
public void SearchCaso3() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setSearchLike("oral");
    busquedaSimulated.setRangoPrecio(new Rango(10000, 15000));

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(1, resultado.size());
    assertEquals("Perfume Floral", resultado.get(0).getNombreProducto());
}

//caso 4 solo rango
@Test
public void SearchCaso4() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setRangoPrecio(new Rango(1984, 17000));

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(3, resultado.size());
    assertTrue(resultado.stream().anyMatch(p -> p.getNombreProducto().equals("Perfume Floral")));
    assertTrue(resultado.stream().anyMatch(p -> p.getNombreProducto().equals("Colonia Deportiva")));
    assertTrue(resultado.stream().anyMatch(p -> p.getNombreProducto().equals("Aroma Citrico")));
}

//caso 5  marca + searchLike
@Test
public void SearchCaso5() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setMarcaProducto("Zara");
    busquedaSimulated.setSearchLike("Aroma");

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(2, resultado.size());
    assertTrue(resultado.stream().allMatch(p -> p.getMarcaProducto().equals("Zara")));
}

// Caso 6: solo marca
@Test
public void SearchCaso6() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setMarcaProducto("Giorgio Armani");

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(1, resultado.size());
    assertTrue(resultado.stream().allMatch(p -> p.getMarcaProducto().equals("Giorgio Armani")));
}

// Caso 7: solo searchLike
@Test
public void SearchCaso7() {
    Busqueda busquedaSimulated = new Busqueda();
    busquedaSimulated.setSearchLike("perfume");

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(2, resultado.size());
    assertTrue(resultado.stream().anyMatch(p -> p.getNombreProducto().equals("Perfume Floral")));
    assertTrue(resultado.stream().anyMatch(p -> p.getNombreProducto().equals("Perfume PacoRobame")));
}

// Caso 8: sin filtros (solo stock > 0)
@Test
public void SearchCaso8() {
    Busqueda busquedaSimulated = new Busqueda(); // Sin filtros

    when(restTemplate.getForObject(anyString(), eq(Producto[].class)))
        .thenReturn(productoTestLista.toArray(new Producto[0]));

    List<Producto> resultado = catalogoService.ejecutarBusqueda(busquedaSimulated);

    assertEquals(5, resultado.size());
    assertTrue(resultado.stream().noneMatch(p -> p.getStockProducto() <= 0));
}
}