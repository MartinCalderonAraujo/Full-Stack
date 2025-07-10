package com.boleta.service.boleta_service;

import com.boleta.service.boleta_service.entidades.Comprobante;
import com.boleta.service.boleta_service.repositorio.BoletaRepository;
import com.boleta.service.boleta_service.servicio.BoletaServicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;





@SpringBootTest
public class BoletaServicioTest {

    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BoletaServicio boletaServicio;

    private Comprobante comprobante;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        comprobante = new Comprobante();
        comprobante.setComprobanteId(1);
        comprobante.setIdPedido(1);
        comprobante.setIdProducto(2);
        comprobante.setMarcaProducto("dior");
        comprobante.setNombreProducto("savauge");
        comprobante.setNombreUsuario("martin calderon");
        comprobante.setPrecioProducto(100000);
        comprobante.setUsuarioId(1);
        comprobante.setFechaPedido(LocalDate.now());
    }
/*listoooo */
    @Test
    void testGuardarComprobante() {
        when(boletaRepository.save(comprobante)).thenReturn(comprobante);

        Comprobante guardado = boletaServicio.save(comprobante);

        assertNotNull(guardado);
        assertEquals(1, guardado.getComprobanteId());
        verify(boletaRepository, times(1)).save(comprobante);
    }

    @Test
    void testObtenerPorCompronteId() {
        when(boletaRepository.findById(1)).thenReturn(Optional.of(comprobante));

        Comprobante comprobanteencontrado = boletaServicio.getBoletaById(1);

        assertNotNull(comprobanteencontrado);
        assertEquals(1, comprobanteencontrado.getIdPedido());
    }

    @Test
    void testObtenerTodas() {
        when(boletaRepository.findAll()).thenReturn(Collections.singletonList(comprobante));

        List<Comprobante> comprobantes = boletaServicio.getAll();

        assertEquals(1, comprobantes.size());
        assertEquals(1, comprobantes.get(0).getComprobanteId());
    }

    @Test
    public void testListarVariosPedidos() {
        Comprobante comprobante2= new Comprobante();
        comprobante2.setComprobanteId(1);
        comprobante2.setIdPedido(1);
        comprobante2.setIdProducto(2);
        comprobante2.setMarcaProducto("dior");
        comprobante2.setNombreProducto("sauvage");
        comprobante2.setNombreUsuario("martin calderon");
        comprobante2.setPrecioProducto(100000);
        comprobante2.setUsuarioId(1);
        comprobante2.setFechaPedido(LocalDate.now());

        Comprobante comprobante3= new Comprobante();
        comprobante3.setComprobanteId(2);
        comprobante3.setIdPedido(2);
        comprobante3.setIdProducto(3);
        comprobante3.setMarcaProducto("antonio banderas");
        comprobante3.setNombreProducto("queseyo");
        comprobante3.setNombreUsuario("sebastian garrido");
        comprobante3.setPrecioProducto(80000);
        comprobante3.setUsuarioId(2);
        comprobante3.setFechaPedido(LocalDate.now());

        List<Comprobante> lista = Arrays.asList(comprobante, comprobante2,comprobante3);

        when(boletaRepository.findAll()).thenReturn(lista);

        List<Comprobante> resultado = boletaServicio.getAll();

        assertEquals(3, resultado.size());


        assertEquals(1, resultado.get(0).getComprobanteId());
        assertEquals(1, resultado.get(0).getIdPedido());
        assertEquals(2, resultado.get(0).getIdProducto());
        assertEquals("dior", resultado.get(0).getMarcaProducto());
        assertEquals("savauge", resultado.get(0).getNombreProducto());
        assertEquals("martin calderon", resultado.get(0).getNombreUsuario());
        assertEquals(100000, resultado.get(0).getPrecioProducto());
        assertEquals(1, resultado.get(0).getUsuarioId());

        assertEquals(2, resultado.get(2).getComprobanteId());
        assertEquals(2, resultado.get(2).getIdPedido());
        assertEquals(3, resultado.get(2).getIdProducto());
        assertEquals("antonio banderas", resultado.get(2).getMarcaProducto());
        assertEquals("queseyo", resultado.get(2).getNombreProducto());
        assertEquals("sebastian garrido", resultado.get(2).getNombreUsuario());
        assertEquals(80000, resultado.get(2).getPrecioProducto());
        assertEquals(2, resultado.get(2).getUsuarioId());
    }

    @Test
    void testByUsuarioId() {
        when(boletaRepository.findByUsuarioId(1))
            .thenReturn(Collections.singletonList(comprobante));

        List<Comprobante> comprobantes = boletaServicio.byUsuarioId(1);

        assertEquals(1, comprobantes.size());
        assertEquals(1, comprobantes.get(0).getUsuarioId());
    }
}
