package com.logistica.logistica_servicio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.logistica.logistica_servicio.entidades.Logistica;
import com.logistica.logistica_servicio.repositorio.LogisticaRepositorio;
import com.logistica.logistica_servicio.servicio.LogisticaServicio;

@SpringBootTest
public class LogisticaServicioTest {

    @Mock
    private LogisticaRepositorio logisticaRepositorio;

    @InjectMocks
    private LogisticaServicio logisticaServicio;

//    @BeforeEach
//    void setUp() {
//        logisticaRepositorio = Mockito.mock(LogisticaRepositorio.class);
//        logisticaServicio = new LogisticaServicio();
//        logisticaServicio.logisticaRepositorio = logisticaRepositorio;
//    }

    @Test
    void listarTodos() {
        List<Logistica> lista = Arrays.asList(
                new Logistica("Despachado", LocalDate.now(), "Transportista1", LocalDate.now().plusDays(5)),
                new Logistica("En camino", LocalDate.now().minusDays(1), "Transportista2", LocalDate.now().plusDays(3))
        );
        when(logisticaRepositorio.findAll()).thenReturn(lista);

        List<Logistica> resultado = logisticaServicio.listarTodos();
        assertEquals(2, resultado.size());
        verify(logisticaRepositorio, times(1)).findAll();
    }

    @Test
    void buscarPorId() {
        Logistica logistica = new Logistica("Despachado", LocalDate.now(), "Transportista1", LocalDate.now().plusDays(5));
        when(logisticaRepositorio.findById(1)).thenReturn(Optional.of(logistica));

        Optional<Logistica> resultado = logisticaRepositorio.findById(1);
        assertTrue(resultado.isPresent());
        assertEquals("Despachado", resultado.get().getEstadoPedido());
        verify(logisticaRepositorio, times(1)).findById(1);
    }

    @Test
    void guardar() {
        Logistica logistica = new Logistica("Despachado", LocalDate.now(), "Transportista1", LocalDate.now().plusDays(5));
        when(logisticaRepositorio.save(logistica)).thenReturn(logistica);

        Logistica resultado = logisticaServicio.guardar(logistica);
        assertNotNull(resultado);
        verify(logisticaRepositorio, times(1)).save(logistica);
    }

    @Test
    void actualizar() {
        Logistica existente = new Logistica("Despachado", LocalDate.now(), "Transportista1", LocalDate.now().plusDays(5));
        Logistica actualizada = new Logistica("Entregado", LocalDate.now(), "TransportistaNuevo", LocalDate.now().plusDays(2));
        when(logisticaRepositorio.findById(1)).thenReturn(Optional.of(existente));
        when(logisticaRepositorio.save(any(Logistica.class))).thenReturn(actualizada);

        Logistica resultado = logisticaServicio.actualizar(1, actualizada);
        assertEquals("Entregado", resultado.getEstadoPedido());
        assertEquals("TransportistaNuevo", resultado.getTransportista());
        verify(logisticaRepositorio, times(1)).findById(1);
        verify(logisticaRepositorio, times(1)).save(any(Logistica.class));
    }

    @Test
    void eliminar() {
        doNothing().when(logisticaRepositorio).deleteById(1);

        logisticaServicio.eliminar(1);
        verify(logisticaRepositorio, times(1)).deleteById(1);
    }
}