package com.catalogo.producto.catalogo_producto_service.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.catalogo.producto.catalogo_producto_service.entidades.Producto;
import com.catalogo.producto.catalogo_producto_service.modelos.Busqueda;

@Service
public class CatalogoService {
    @Autowired
    private RestTemplate restTemplate;
    public List<Producto> getFullProductosTable() {
    Producto[] arrayProductosFull = restTemplate.getForObject("http://localhost:8002/producto", Producto[].class);
    return Arrays.asList(arrayProductosFull);
    }


public List<Producto> ejecutarBusqueda(Busqueda busqueda) {
    boolean isRango = Optional.ofNullable(busqueda.getRangoPrecio()).isPresent();
    boolean isMarca = Optional.ofNullable(busqueda.getMarcaProducto()).isPresent() && !busqueda.getMarcaProducto().isEmpty();
    boolean isSearchLike = Optional.ofNullable(busqueda.getSearchLike()).isPresent() && !busqueda.getSearchLike().isEmpty();

    List<Producto> fullLista = getFullProductosTable();
    List<Producto> listaFiltrada = new ArrayList<>();


 if (isRango) {
        if (isMarca) {
            if (isSearchLike) {
        //caso 1 filtrar porel rango, marca y searchLike:
                    int minPrecio = busqueda.getRangoPrecio().getMinPrecio();
                    int maxPrecio = busqueda.getRangoPrecio().getMaxPrecio();

                    for (Producto p : fullLista) {
                        boolean marcaBool = p.getMarcaProducto().equalsIgnoreCase(busqueda.getMarcaProducto());
                        boolean nombreBool = p.getNombreProducto().toLowerCase().contains(busqueda.getSearchLike().toLowerCase());
                        boolean precioBool = p.getPrecioProducto() >= minPrecio && p.getPrecioProducto() <= maxPrecio;
                        boolean isAvalible = p.getStockProducto() >0;

                        if (marcaBool && nombreBool && precioBool && isAvalible) {
                            listaFiltrada.add(p);
                        }
                }
            } else {
                // caso 2 filtrar por rango y marca 
                int minPrecio = busqueda.getRangoPrecio().getMinPrecio();
                int maxPrecio = busqueda.getRangoPrecio().getMaxPrecio();

                for (Producto p : fullLista) {
                    boolean marcaBool = p.getMarcaProducto().equalsIgnoreCase(busqueda.getMarcaProducto());
                    boolean precioBool = p.getPrecioProducto() >= minPrecio && p.getPrecioProducto() <= maxPrecio;
                    boolean isAvalible = p.getStockProducto() >0;
                    if (marcaBool && precioBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            }
        } else {
            if (isSearchLike) {
                // caso 3 filtrar por el rango y searchLike  similar al nombre o marca
                int minPrecio = busqueda.getRangoPrecio().getMinPrecio();
                int maxPrecio = busqueda.getRangoPrecio().getMaxPrecio();
                String search = busqueda.getSearchLike().toLowerCase();

                for (Producto p : fullLista) {
                    boolean searchBool = p.getNombreProducto().toLowerCase().contains(search) || p.getMarcaProducto().toLowerCase().contains(search);
                    boolean precioBool = p.getPrecioProducto() >= minPrecio && p.getPrecioProducto() <= maxPrecio;
                    boolean isAvalible = p.getStockProducto() >0;

                    if (searchBool && precioBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            } else {
                //caso 4 filtrar solo por rango de precios
                int minPrecio = busqueda.getRangoPrecio().getMinPrecio();
                int maxPrecio = busqueda.getRangoPrecio().getMaxPrecio();

                for (Producto p : fullLista) {
                    boolean precioBool = p.getPrecioProducto() >= minPrecio && p.getPrecioProducto() <= maxPrecio;
                    boolean isAvalible = p.getStockProducto() >0;
                    if (precioBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            }
        }
    } else {
        if (isMarca) {
            if (isSearchLike) {
                //caso 5 filtrar por marca y searchLike
                String search = busqueda.getSearchLike().toLowerCase();

                for (Producto p : fullLista) {
                    boolean marcaBool = p.getMarcaProducto().equalsIgnoreCase(busqueda.getMarcaProducto());
                    boolean nombreBool = p.getNombreProducto().toLowerCase().contains(search);
                    boolean isAvalible = p.getStockProducto() >0;

                    if (marcaBool && nombreBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            } else {
                //caso 6 filtrar solo por marca
                for (Producto p : fullLista) {
                    boolean marcaBool = p.getMarcaProducto().equalsIgnoreCase(busqueda.getMarcaProducto());
                    boolean isAvalible = p.getStockProducto() >0;
                    if (marcaBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            }
        } else {
            if (isSearchLike) {
                //caso 7 filtrar solo por searchLike 
                String search = busqueda.getSearchLike().toLowerCase();

                for (Producto p : fullLista) {
                    boolean searchBool = p.getNombreProducto().toLowerCase().contains(search) || p.getMarcaProducto().toLowerCase().contains(search);
                    boolean isAvalible = p.getStockProducto() >0;
                    if (searchBool && isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
            } else {
                //caso 8 no se da filtro y devuelve la todos los productos
                for (Producto p : fullLista) {
                    boolean isAvalible = p.getStockProducto() >0;
                    if (isAvalible) {
                        listaFiltrada.add(p);
                    }
                }
                
            }
        }
    }
    
   return listaFiltrada;
}
}

