package soporte.service.soporte_service.Entidades;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Soporte {

        private int idSoporte;
        private int idTrabajadorSoporte;
        private int usuarioId;
        private String tipoDeSolicitud;
        private String detalleDeSolicitud;
        private String respuestaDeSolicitud;
        private LocalDate fechaSolicitud;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        
        public int getIdSoporte() {
            return idSoporte;
        }
    
        public void setIdSoporte(int idSoporte) {
            this.idSoporte = idSoporte;
        }
    
        public int getIdTrabajadorSoporte() {
            return idTrabajadorSoporte;
        }
    
        public void setIdTrabajadorSoporte(int idTrabajadorSoporte) {
            this.idTrabajadorSoporte = idTrabajadorSoporte;
        }
    
        public int getUsuarioId() {
            return usuarioId;
        }
    
        public void setUsuarioId(int usuarioId) {
            this.usuarioId = usuarioId;
        }
    
        public String getTipoDeSolicitud() {
            return tipoDeSolicitud;
        }
    
        public void setTipoDeSolicitud(String tipoDeSolicitud) {
            this.tipoDeSolicitud = tipoDeSolicitud;
        }
    
        public String getDetalleDeSolicitud() {
            return detalleDeSolicitud;
        }
    
        public void setDetalleDeSolicitud(String detalleDeSolicitud) {
            this.detalleDeSolicitud = detalleDeSolicitud;
        }
    
        public String getRespuestaDeSolicitud() {
            return respuestaDeSolicitud;
        }
    
        public void setRespuestaDeSolicitud(String respuestaDeSolicitud) {
            this.respuestaDeSolicitud = respuestaDeSolicitud;
        }
    
        public LocalDate getFechaSolicitud() {
            return fechaSolicitud;
        }
    
        public void setFechaSolicitud(LocalDate fechaSolicitud) {
            this.fechaSolicitud = fechaSolicitud;
        }
    
        public Soporte() {
        }
    
    @Override
        public String toString() {
            return "Soporte [idSoporte=" + idSoporte +
            ", idTrabajadorSoporte=" + idTrabajadorSoporte + 
            ", usuarioId="+ usuarioId + 
            ", tipoDeSolicitud=" + tipoDeSolicitud + 
            ", detalleDeSolicitud=" + detalleDeSolicitud+ 
            ", respuestaDeSolicitud=" + respuestaDeSolicitud + 
            ", fechaSolicitud=" + fechaSolicitud +
            "]";
        }
}
