package descuentoscupones.service.descuentos_cupones_service.modelo;

public class Producto {
    private int idProducto;
    private double precioProducto;
    
    public Producto() {
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public double getPrecioProducto() {
        return precioProducto;
    }
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }   
}
