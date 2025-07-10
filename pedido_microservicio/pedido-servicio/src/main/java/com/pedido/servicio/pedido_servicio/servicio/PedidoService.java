package com.pedido.servicio.pedido_servicio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pedido.servicio.pedido_servicio.entidades.Pedido;
import com.pedido.servicio.pedido_servicio.modelo.Producto;
import com.pedido.servicio.pedido_servicio.repositorio.PedidoRepositorio;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public List<Pedido> getAll() {
        return pedidoRepositorio.findAll();
    }

    public Pedido getPedidoById(int idPedido) {
        return pedidoRepositorio.findById(idPedido).orElse(null);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    public void deletePedidoById(int id){
        pedidoRepositorio.deleteById(id);
    }

    @Autowired
    private RestTemplate restTemplate;
    public List<Producto> getProductos(int idPedido){
        List<Producto>productos=restTemplate.getForObject("http://localhost:8005/producto/pedido/"+idPedido,List.class);
        return productos;
    }

    public List<Pedido>byProductoId(int idProducto){
        return pedidoRepositorio.findByIdProducto(idProducto);
    }

    public Pedido actualizar(int idPedido, Pedido pedido) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
}