package descuentoscupones.service.descuentos_cupones_service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import descuentoscupones.service.descuentos_cupones_service.entidades.Cupon;
import descuentoscupones.service.descuentos_cupones_service.repositorio.CuponRepository;
import descuentoscupones.service.descuentos_cupones_service.servicio.CuponService;

public class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Captor
        private ArgumentCaptor<Cupon> cuponCaptura;
    
    //Test del metodo para obetener cupon
    @Test
    public void testGetAll(){
        Cupon cupon1 = new Cupon();
        Cupon cupon2 = new Cupon();
        
        cupon1.setIdCupon(1);
        cupon1.setNombreCupon("Cupon navidad");
        cupon1.setCampannaCupon("Navidad");
        cupon1.setDescuentoCupon(0.5);
        
        cupon2.setIdCupon(2);
        cupon2.setNombreCupon("Cupon año nuevo");
        cupon2.setCampannaCupon("Año nuevo");
        cupon2.setDescuentoCupon(0.1);

        when(cuponRepository.findAll()).thenReturn(Arrays.asList(cupon1, cupon2));

        List<Cupon> listaCupones = cuponService.getAll();
        assertEquals("Cupon navidad", listaCupones.get(0).getNombreCupon());
        
    }

    //Test obtener cupon por id
    @Test
    public void testGetCuponById(){
        Cupon cupon1 = new Cupon();
        Cupon cupon2 = new Cupon();
        
        cupon1.setIdCupon(1);
        cupon1.setNombreCupon("Cupon navidad");
        cupon1.setCampannaCupon("Navidad");
        cupon1.setDescuentoCupon(0.5);
        
        cupon2.setIdCupon(2);
        cupon2.setNombreCupon("Cupon año nuevo");
        cupon2.setCampannaCupon("Año nuevo");
        cupon2.setDescuentoCupon(0.1);

        when(cuponRepository.findAll()).thenReturn(Arrays.asList(cupon1, cupon2));

        List<Cupon> listaCupones = cuponService.getAll();
        assertEquals(1, listaCupones.get(0).getIdCupon());               
    }

    //Test guardar cupones
    @Test
    public void testSaveCupon(){
       
        Cupon cupon1 = new Cupon();
                
        cupon1.setIdCupon(1);
        cupon1.setNombreCupon("Cupon navidad");
        cupon1.setCampannaCupon("Navidad");
        cupon1.setDescuentoCupon(0.5);
        
        when(cuponService.saveCupon(any(Cupon.class))).thenReturn(cupon1);

        cuponService.saveCupon(cupon1);
        
        //verifica si se llamo al repositorio para guardar
        verify(cuponRepository).save(cuponCaptura.capture());

        Cupon cuponCapturado = cuponCaptura.getValue();
        
        System.out.println("Cupon1 : "+cuponCapturado);

        assertNotNull(cupon1.getIdCupon());
        assertEquals(cuponCapturado, cupon1);

        
    }

    //Eliminar cupon por id
    @Test
    public void testDeleteCuponById(){
        int idCupon = 1;
        
        cuponService.deleteCuponById(idCupon);

        verify(cuponRepository).deleteById(idCupon);

        System.out.println("Verificacion"+idCupon);       
    }

    @Test
    public void testSaveCuponConDescuentoNegativo() {
        Cupon cupon = new Cupon();
        cupon.setDescuentoCupon(-0.1);

        when(cuponRepository.save(any(Cupon.class))).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            cuponService.saveCupon(cupon);
        });
    }

    @Test
    public void testGetAllCuponesCuandoNoHayDatos() {
        when(cuponRepository.findAll()).thenReturn(Collections.emptyList());

        List<Cupon> lista = cuponService.getAll();
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testGetCuponByIdConValorNegativo() {
        Cupon resultado = cuponService.getCuponById(-5);
        assertNull(resultado);
    }

    @Test
    public void testObtenerCuponPorIdInexistente() {
        int id = 999;
        when(cuponRepository.findById(id)).thenReturn(Optional.empty());

        Cupon resultado = cuponService.getCuponById(id);
        assertNull(resultado);
    }
}
