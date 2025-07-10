package producto.servicio.producto_servicio.excepcion;

public class ProductoNotFound extends RuntimeException {
    public ProductoNotFound(int idProducto){
        super("Producto no encontrado con id: " + idProducto);
    }
}
