package producto.servicio.producto_servicio.productoServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import producto.servicio.producto_servicio.entidades.Producto;
import producto.servicio.producto_servicio.excepcion.ProductoNotFound;
import producto.servicio.producto_servicio.repositorio.ProductoRepository;


@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepositorio;

    public List<Producto> getAllProducto(){
        return productoRepositorio.findAll();
    }

    public Producto getProductoById(int idProducto){
        return productoRepositorio.findById(idProducto).orElseThrow(() -> new ProductoNotFound(idProducto));
    }

    public Producto saveProducto(Producto producto){
        if (producto.getPrecioProducto() < 0) {
        throw new IllegalArgumentException("El precio del producto no puede ser negativo");
    }
        Producto nuevoProducto = productoRepositorio.save(producto);
        return nuevoProducto;
    }

    public List<Producto> getProductoByUsuarioId (int usuarioId){
        return productoRepositorio.findByUsuarioId(usuarioId);
    }

    public void deleteProductoById (int idProducto){
        productoRepositorio.deleteById(idProducto);
    }
}
