package com.catalogo.producto.catalogo_producto_service.entidades;


public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String marcaProducto;
    private int precioProducto;
    private int stockProducto;
    private int usuarioId;

        
    
    
    public Producto() {
    }
    public Producto(int idProducto, String nombreProducto, String marcaProducto, int precioProducto, int stockProducto,
            int usuarioId) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.usuarioId = usuarioId;
    }
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
    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
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

}
