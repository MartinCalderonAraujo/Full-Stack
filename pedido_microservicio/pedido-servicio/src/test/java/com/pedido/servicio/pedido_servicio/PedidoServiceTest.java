package com.pedido.servicio.pedido_servicio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.pedido.servicio.pedido_servicio.entidades.Pedido;
import com.pedido.servicio.pedido_servicio.repositorio.PedidoRepositorio;
import com.pedido.servicio.pedido_servicio.servicio.PedidoService;



public class PedidoServiceTest {

    @Mock
    private PedidoRepositorio pedidoRepositorio;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setIdCuentaCliente(100);
        pedido.setEstadoPedido("Pendiente");
        pedido.setTotalPedido(2500.0);
        pedido.setFechaPedido(LocalDate.now());
    }

    @Test
    public void testGuardarPedido() {
        when(pedidoRepositorio.save(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoService.save(pedido);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdPedido());
        assertEquals("Pendiente", resultado.getEstadoPedido());
    }

    @Test
    public void testObtenerPorId() {
        when(pedidoRepositorio.findById(1)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.getPedidoById(1);

        assertNotNull(resultado);
        assertEquals(100, resultado.getIdCuentaCliente());
    }

    @Test
    public void testListarPedido() {
        when(pedidoRepositorio.findAll()).thenReturn(Collections.singletonList(pedido));

        List<Pedido> resultado = pedidoService.getAll();

        assertEquals(1, resultado.size());
        assertEquals("Pendiente", resultado.get(0).getEstadoPedido());
    }

    @Test
    public void testListarVariosPedidos() {
        Pedido otroPedido = new Pedido();
        otroPedido.setIdPedido(2);
        otroPedido.setIdCuentaCliente(200);
        otroPedido.setEstadoPedido("Entregado");
        otroPedido.setTotalPedido(5000.0);
        otroPedido.setFechaPedido(LocalDate.now());

        List<Pedido> lista = Arrays.asList(pedido, otroPedido);

        when(pedidoRepositorio.findAll()).thenReturn(lista);

        List<Pedido> resultado = pedidoService.getAll();

        assertEquals(2, resultado.size());
        assertEquals("Pendiente", resultado.get(0).getEstadoPedido());
        assertEquals("Entregado", resultado.get(1).getEstadoPedido());
    }

    @Test
    public void testActualizarPedido() {
        Pedido actualizado = new Pedido();
        actualizado.setIdPedido(1);
        actualizado.setIdCuentaCliente(100);
        actualizado.setEstadoPedido("Confirmado");
        actualizado.setTotalPedido(3000.0);
        actualizado.setFechaPedido(LocalDate.now());

        when(pedidoRepositorio.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepositorio.save(actualizado)).thenReturn(actualizado);

        Pedido resultado = pedidoService.save(actualizado);

        assertEquals("Confirmado", resultado.getEstadoPedido());
        assertEquals(3000.0, resultado.getTotalPedido());
    }

    @Test
    public void testEliminarPedido() {
        doNothing().when(pedidoRepositorio).deleteById(1);

        pedidoService.deletePedidoById(1);

        verify(pedidoRepositorio, times(1)).deleteById(1);
    }
}
