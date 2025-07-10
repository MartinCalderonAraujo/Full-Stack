package producto.servicio.producto_servicio.controlador;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import producto.servicio.producto_servicio.entidades.Producto;
import producto.servicio.producto_servicio.productoServicio.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoServicio;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listarProductos(){
        List<Producto> productos = productoServicio.getAllProducto();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Producto>> recursos = productos.stream()
            .map(l -> EntityModel.of(l,
                    linkTo(methodOn(ProductoController.class).obtenerProducto(l.getIdProducto())).withSelfRel(),
                    linkTo(methodOn(ProductoController.class).listarProductos()).withRel("todos-los-productos")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(recursos));
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<EntityModel<Producto>> obtenerProducto(@PathVariable("idProducto") int idProducto){
        Producto producto = productoServicio.getProductoById(idProducto);
        if(producto == null){
            return ResponseEntity.notFound().build();
        }

        EntityModel<Producto> recurso = EntityModel.of(producto);
        recurso.add(linkTo(methodOn(ProductoController.class).obtenerProducto(idProducto)).withSelfRel());
        recurso.add(linkTo(methodOn(ProductoController.class).listarProductos()).withRel("todos-los-productos"));

        return ResponseEntity.ok(recurso);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable("idProducto") int idProducto){
        Producto nuevoProducto = productoServicio.getProductoById(idProducto);
        if (nuevoProducto == null) {
            return ResponseEntity.notFound().build();
        }

        productoServicio.deleteProductoById(idProducto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Producto>> guardarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoServicio.saveProducto(producto);
        EntityModel<Producto> recurso = EntityModel.of(nuevoProducto);
        recurso.add(linkTo(methodOn(ProductoController.class).obtenerProducto(nuevoProducto.getIdProducto())).withSelfRel());
        recurso.add(linkTo(methodOn(ProductoController.class).listarProductos()).withRel("todos-los-productos"));

        return ResponseEntity.ok(recurso);
    }

    /* Buscar productos por el id del usuario */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity <List<Producto>> listarProductoPorUsuarioId(@PathVariable("usuarioId") int id){
        List<Producto> productos = productoServicio.getProductoByUsuarioId(id);
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
