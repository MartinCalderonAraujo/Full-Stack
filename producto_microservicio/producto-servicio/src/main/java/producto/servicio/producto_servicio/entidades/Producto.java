package producto.servicio.producto_servicio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    private String nombreProducto;
    private String marcaProducto;
    private int precioProducto;
    private int stockProducto;
    private int usuarioId;

    
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getMarcaProducto() {
        return marcaProducto;
    }
    public void setMarcaProducto(String descripcion) {
        this.marcaProducto = descripcion;
    }
    public int getPrecioProducto() {
        return precioProducto;
    }
    public void setPrecioProducto(int precio) {
        this.precioProducto = precio;
    }
    public int getStockProducto() {
        return stockProducto;
    }
    public void setStockProducto(int stock) {
        this.stockProducto = stock;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    @Override
    public String toString() {
        return "Producto Id producto= " + idProducto +
            ", Nombre producto= " + nombreProducto +
            ", Marca producto= " + marcaProducto +
            ", Precio producto= " + precioProducto +
            ", Stock producto= " + stockProducto +
            ", Id usuario= " + usuarioId;
    }
}
