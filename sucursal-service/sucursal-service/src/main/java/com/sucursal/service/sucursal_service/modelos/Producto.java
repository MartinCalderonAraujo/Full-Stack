package com.sucursal.service.sucursal_service.modelos;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String marcaProducto;
    private int precioProducto;
    private int stockProducto;

    public Producto() {
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
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
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
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }
    
}
