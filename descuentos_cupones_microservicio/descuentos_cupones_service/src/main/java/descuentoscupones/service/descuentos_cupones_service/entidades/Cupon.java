package descuentoscupones.service.descuentos_cupones_service.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idCupon;
    private String nombreCupon;
    private String campannaCupon;
    private double descuentoCupon;   
    private int idProducto;
    
    
    public int getIdCupon() {
        return idCupon;
    }
    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
    public String getNombreCupon() {
        return nombreCupon;
    }
    public void setNombreCupon(String nombreCupon) {
        this.nombreCupon = nombreCupon;
    }
    public String getCampañaCupon() {
        return campannaCupon;
    }
    public void setCampannaCupon(String campannaCupon) {
        this.campannaCupon = campannaCupon;
    }
    public double getDescuentoCupon() {
        return descuentoCupon;
    }
    public void setDescuentoCupon(double descuentoCupon) {
        this.descuentoCupon = descuentoCupon;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Cupon{idCupon=" + idCupon +
            ", nombreCupon='" + nombreCupon + '\'' +
            ", campannaCupon='" + campannaCupon + '\'' +
            ", descuentoCupon=" + descuentoCupon + '}';
    }



}
