package com.catalogo.producto.catalogo_producto_service.modelos;

public class Busqueda {
    private String searchLike;
    private String marcaProducto;
    private Rango rangoPrecio;
    
    public Busqueda() {
    }
    
    public Busqueda(String searchLike, String marcaProducto, Rango rangoPrecio) {
        this.searchLike = searchLike;
        this.marcaProducto = marcaProducto;
        this.rangoPrecio = rangoPrecio;
    }

    public String getSearchLike() {
        return searchLike;
    }
    public void setSearchLike(String searchLike) {
        this.searchLike = searchLike;
    }
    public String getMarcaProducto() {
        return marcaProducto;
    }
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
    public Rango getRangoPrecio() {
        return rangoPrecio;
    }
    public void setRangoPrecio(Rango rangoPrecio) {
        this.rangoPrecio = rangoPrecio;
    }

    

}
