package com.catalogo.producto.catalogo_producto_service.modelos;

public class Rango {
    private int minPrecio;
    private int maxPrecio;

    
    public Rango() {
    }
    
    public Rango(int minPrecio, int maxPrecio) {
        this.minPrecio = minPrecio;
        this.maxPrecio = maxPrecio;
    }

    public int getMinPrecio() {
        return minPrecio;
    }
    public void setMinPrecio(int minPrecio) {
        this.minPrecio = minPrecio;
    }
    public int getMaxPrecio() {
        return maxPrecio;
    }
    public void setMaxPrecio(int maxPrecio) {
        this.maxPrecio = maxPrecio;
    }
    

}
