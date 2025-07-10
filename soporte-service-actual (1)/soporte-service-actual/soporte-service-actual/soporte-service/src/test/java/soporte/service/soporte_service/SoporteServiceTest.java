package soporte.service.soporte_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import soporte.service.soporte_service.Entidades.Soporte;
import soporte.service.soporte_service.Repository.SoporteRepository;
import soporte.service.soporte_service.Servicio.SoporteServicio;

@SpringBootTest
public class SoporteServiceTest {

    @Mock
    private SoporteRepository soporteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SoporteServicio soporteServicio;

    private Soporte soporte;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        soporte = new Soporte();
        soporte.setIdSoporte(1);
        soporte.setIdTrabajadorSoporte(1);;
        soporte.setUsuarioId(1); 
        soporte.setTipoDeSolicitud("reclamo"); 
        soporte.setDetalleDeSolicitud("tuve problemas al ver los productos en trayecto"); 
        soporte.setRespuestaDeSolicitud("Se esta revisando pero de ante mano se pide una disculpa y se le dara un cupon de descuento"); 
        soporte.setFechaSolicitud(LocalDate.now());

        Soporte soporte3= new Soporte();
        soporte3 = new Soporte();
        soporte3.setIdSoporte(2);
        soporte3.setIdTrabajadorSoporte(2);;
        soporte3.setUsuarioId(2); 
        soporte3.setTipoDeSolicitud("consulta"); 
        soporte3.setDetalleDeSolicitud("me gustaria saber como aplicar mis cupones"); 
        soporte3.setRespuestaDeSolicitud(""); 
        soporte3.setFechaSolicitud(LocalDate.now());
    }
/*listoooo */
    @Test
    void testGuardarSoporte() {
        when(soporteRepository.save(soporte)).thenReturn(soporte);

        Soporte guardado = soporteServicio.save(soporte);

        assertNotNull(guardado);
        assertEquals(1, guardado.getIdSoporte());
        verify(soporteRepository, times(1)).save(soporte);
    }

    @Test
    void testObtenerPorIdSoporte() {
        when(soporteRepository.findById(1)).thenReturn(Optional.of(soporte));

        Soporte soporteEncontrado = soporteServicio.getSoporteById(1);

        assertNotNull(soporteEncontrado);
        assertEquals(1, soporteEncontrado.getIdSoporte());
    }

    @Test
    void testObtenerTodos() {
        when(soporteRepository.findAll()).thenReturn(Collections.singletonList(soporte));

        List<Soporte> soportes = soporteServicio.getAll();

        assertEquals(1, soportes.size());
        assertEquals(1, soportes.get(0).getIdSoporte());
    }

    @Test
    public void testListarVariosPedidos() {
        Soporte soporte2= new Soporte();
        soporte2 = new Soporte();
        soporte2.setIdSoporte(1);
        soporte2.setIdTrabajadorSoporte(1);;
        soporte2.setUsuarioId(1); 
        soporte2.setTipoDeSolicitud("reclamo"); 
        soporte2.setDetalleDeSolicitud("tuve problemas al ver los productos en trayecto"); 
        soporte2.setRespuestaDeSolicitud("Se esta revisando pero de ante mano se pide una disculpa y se le dara un cupon de descuento"); 
        soporte2.setFechaSolicitud(LocalDate.now());

        Soporte soporte3= new Soporte();
        soporte3 = new Soporte();
        soporte3.setIdSoporte(2);
        soporte3.setIdTrabajadorSoporte(2);;
        soporte3.setUsuarioId(2); 
        soporte3.setTipoDeSolicitud("consulta"); 
        soporte3.setDetalleDeSolicitud("me gustaria saber como aplicar mis cupones"); 
        soporte3.setRespuestaDeSolicitud(""); 
        soporte3.setFechaSolicitud(LocalDate.now());

        List<Soporte> lista = Arrays.asList(soporte, soporte2,soporte3);

        when(soporteRepository.findAll()).thenReturn(lista);

        List<Soporte> resultado = soporteServicio.getAll();

        assertEquals(3, resultado.size());


        assertEquals(1, resultado.get(0).getIdSoporte());
        assertEquals(1, resultado.get(0).getIdTrabajadorSoporte());;
        assertEquals(1, resultado.get(0).getUsuarioId());;
        assertEquals("reclamo", resultado.get(0).getTipoDeSolicitud());
        assertEquals("tuve problemas al ver los productos en trayecto", resultado.get(1).getDetalleDeSolicitud());
        assertEquals("Se esta revisando pero de ante mano se pide una disculpa y se le dara un cupon de descuento", resultado.get(1).getRespuestaDeSolicitud());
        assertEquals(LocalDate.now(), resultado.get(1).getFechaSolicitud());

        assertEquals(1, resultado.get(1).getIdSoporte());
        assertEquals(1, resultado.get(1).getIdTrabajadorSoporte());
        assertEquals(1, resultado.get(1).getUsuarioId());;
        assertEquals("consulta", resultado.get(2).getTipoDeSolicitud());
        assertEquals("me gustaria saber como aplicar mis cupones", resultado.get(2).getDetalleDeSolicitud());
        assertEquals("", resultado.get(2).getRespuestaDeSolicitud());
        assertEquals(LocalDate.now(), resultado.get(2).getFechaSolicitud());
    }

    @Test
    void testByUsuarioId() {
        when(soporteRepository.findByUsuarioId(1))
            .thenReturn(Collections.singletonList(soporte));

        List<Soporte> soportes = soporteServicio.byUsuarioId(1);

        assertEquals(1, soportes.size());
        assertEquals(1, soportes.get(0).getUsuarioId());
    }
}




