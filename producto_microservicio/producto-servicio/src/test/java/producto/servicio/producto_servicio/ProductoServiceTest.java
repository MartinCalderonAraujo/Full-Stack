package producto.servicio.producto_servicio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import producto.servicio.producto_servicio.entidades.Producto;
import producto.servicio.producto_servicio.productoServicio.ProductoService;
import producto.servicio.producto_servicio.repositorio.ProductoRepository;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Captor
    private ArgumentCaptor<Producto> captura;

    @Test
    public void testGetAllProductos(){
        Producto producto1 = new Producto();
        
        producto1.setIdProducto(1);
        producto1.setNombreProducto("ThinkPad");
        producto1.setMarcaProducto("Lenovo");
        producto1.setPrecioProducto(500000);
        producto1.setStockProducto(100);
        producto1.setUsuarioId(1);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1));

        List<Producto> listaProductos = productoService.getAllProducto();
        assertEquals("ThinkPad", listaProductos.get(0).getNombreProducto());
    }

    @Test
    public void testGetProductosById(){
        Producto producto1 = new Producto();
        
        producto1.setIdProducto(1);
        producto1.setNombreProducto("ThinkPad");
        producto1.setMarcaProducto("Lenovo");
        producto1.setPrecioProducto(500000);
        producto1.setStockProducto(100);
        producto1.setUsuarioId(1);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1));
        List<Producto> listaProductos = productoService.getAllProducto();
        assertEquals("ThinkPad", listaProductos.get(0).getNombreProducto());
    }

    @Test
    public void testSaveProducto(){
        Producto producto1 = new Producto();
        
        producto1.setIdProducto(1);
        producto1.setNombreProducto("ThinkPad");
        producto1.setMarcaProducto("Lenovo");
        producto1.setPrecioProducto(500000);
        producto1.setStockProducto(100);
        producto1.setUsuarioId(1);

        when(productoService.saveProducto(any(Producto.class))).thenReturn(producto1);

        productoService.saveProducto(producto1);
        verify(productoRepository).save(captura.capture());

        Producto productoCapturado = captura.getValue();

        System.out.println(productoCapturado);
        assertEquals(producto1, productoCapturado);
    }

    @Test
    public void testDeleteProductoById(){
        int idProducto = 2;
        productoService.deleteProductoById(idProducto);

        verify(productoRepository).deleteById(idProducto);

        System.err.println("Verificacion"+idProducto);
    }

    @Test
    public void testGetAllProductosVacio1() {
        when(productoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Producto> productos = productoService.getAllProducto();
        assertTrue(productos.isEmpty());
    }

    @Test
    public void testSaveProductoConPrecioNegativo() {
        Producto producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Notebook");
        producto.setPrecioProducto(-10000);
        assertThrows(IllegalArgumentException.class, () -> productoService.saveProducto(producto));
    }

    @Test
    public void testSaveProductoConStockNulo() {
        Producto producto = new Producto();
        producto.setIdProducto(2);
        producto.setNombreProducto("Mouse");

        productoService.saveProducto(producto);

        verify(productoRepository).save(captura.capture());
        Producto productoCapturado = captura.getValue();
        assertEquals(0, productoCapturado.getStockProducto());
    }

    @Test
    public void testEliminarProductoNoExistente() {
        int idInexistente = 10;

        doThrow(new EmptyResultDataAccessException(1)).when(productoRepository).deleteById(idInexistente);

        assertThrows(EmptyResultDataAccessException.class, () -> productoService.deleteProductoById(idInexistente));
        System.out.println("No existe producto");
    }
}
