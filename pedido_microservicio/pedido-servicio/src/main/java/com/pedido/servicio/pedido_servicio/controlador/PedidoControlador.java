package com.pedido.servicio.pedido_servicio.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido.servicio.pedido_servicio.entidades.Pedido;
import com.pedido.servicio.pedido_servicio.servicio.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoControlador {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.getAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Pedido>> recursos = pedidos.stream()
            .map(p -> EntityModel.of(p,
                    linkTo(methodOn(PedidoControlador.class).obtenerPedido(p.getIdPedido())).withSelfRel(),
                    linkTo(methodOn(PedidoControlador.class).listarPedidos()).withRel("todos-los-pedidos"),
                    linkTo(methodOn(PedidoControlador.class).listarPedidosPorProducto(p.getIdPedido())).withRel("pedidos-del-producto")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<EntityModel<Pedido>> obtenerPedido(@PathVariable("idPedido") int idPedido) {
        Pedido pedido = pedidoService.getPedidoById(idPedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Pedido> recurso = EntityModel.of(pedido);
        recurso.add(linkTo(methodOn(PedidoControlador.class).obtenerPedido(idPedido)).withSelfRel());
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidos()).withRel("todos-los-pedidos"));
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidosPorProducto(idPedido)).withRel("pedidos-del-producto"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pedido>> guardarPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.save(pedido);

        EntityModel<Pedido> recurso = EntityModel.of(nuevoPedido);
        recurso.add(linkTo(methodOn(PedidoControlador.class).obtenerPedido(nuevoPedido.getIdPedido())).withSelfRel());
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidos()).withRel("todos-los-pedidos"));
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidosPorProducto(nuevoPedido.getIdPedido())).withRel("pedidos-del-producto"));

        return ResponseEntity.ok(recurso);
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<EntityModel<Pedido>> actualizarPedido(@PathVariable("idPedido") int idPedido, @RequestBody Pedido nuevoPedido) {
        Pedido pedidoExistente = pedidoService.getPedidoById(idPedido);
        if (pedidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        nuevoPedido.setIdPedido(idPedido);
        Pedido actualizado = pedidoService.save(nuevoPedido);

        EntityModel<Pedido> recurso = EntityModel.of(actualizado);
        recurso.add(linkTo(methodOn(PedidoControlador.class).obtenerPedido(idPedido)).withSelfRel());
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidos()).withRel("todos-los-pedidos"));
        recurso.add(linkTo(methodOn(PedidoControlador.class).listarPedidosPorProducto(idPedido)).withRel("pedidos-del-producto"));

        return ResponseEntity.ok(recurso);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable("idPedido") int idPedido) {
        Pedido pedido = pedidoService.getPedidoById(idPedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.deletePedidoById(idPedido);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> listarPedidosPorProducto(@PathVariable("idProducto") int idProducto) {
        List<Pedido> pedidos = pedidoService.byProductoId(idProducto);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Pedido>> recursos = pedidos.stream()
            .map(p -> EntityModel.of(p,
                    linkTo(methodOn(PedidoControlador.class).obtenerPedido(p.getIdPedido())).withSelfRel(),
                    linkTo(methodOn(PedidoControlador.class).listarPedidos()).withRel("todos-los-pedidos")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }
}
