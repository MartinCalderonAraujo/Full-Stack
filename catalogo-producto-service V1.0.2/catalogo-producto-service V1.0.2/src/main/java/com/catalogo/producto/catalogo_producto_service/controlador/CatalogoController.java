package com.catalogo.producto.catalogo_producto_service.controlador;

import com.catalogo.producto.catalogo_producto_service.entidades.Producto;
import com.catalogo.producto.catalogo_producto_service.modelos.Busqueda;
import com.catalogo.producto.catalogo_producto_service.servicio.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    // GET /catalogo retorna la lista completa de productos
    @GetMapping
    public ResponseEntity<CollectionModel<Producto>> obtenerTodos() {
        List<Producto> productos = catalogoService.getFullProductosTable();

        return ResponseEntity.ok(CollectionModel.of(productos,
                        linkTo(methodOn(CatalogoController.class).buscarConFiltros(null)).withRel("buscar-productos"))
        );
    }

    // POST /catalogo/buscar aplica filtros
    @PostMapping("/buscar")
    public ResponseEntity<CollectionModel<Producto>> buscarConFiltros(@RequestBody Busqueda busqueda) {
        String fixedSearchLike = busqueda.getSearchLike();
        if (fixedSearchLike != null) {
        busqueda.setSearchLike(fixedSearchLike.trim());
    }
        List<Producto> resultados = catalogoService.ejecutarBusqueda(busqueda);
        return ResponseEntity.ok(
                CollectionModel.of(resultados,
                        linkTo(methodOn(CatalogoController.class).obtenerTodos()).withRel("catalogo-completo"))
        );
    }
}