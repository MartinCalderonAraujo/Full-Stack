package soporte.service.soporte_service.Modelos;

public class Usuario {

    
    private int usuarioId;
    private int usuarioRut;
    private char usuarioDv;
    private String usuarioNombre;
    private String usuarioEmail;
    private String usuarioDireccion;
    private int usuarioNumeroTelefonico;
    private String tipoUsuario;
    private int comprobanteId;



    
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int id) {
        this.usuarioId = id;
    }
     public int getUsuarioRut() {
        return usuarioRut;
    }
    public void setUsuarioRut(int usuarioRut) {
        this.usuarioRut = usuarioRut;
    }
    public char getUsuarioDv() {
        return usuarioDv;
    }
    public void setUsuarioDv(char usuarioDv) {
        this.usuarioDv = usuarioDv;
    }
    public String getUsuarioNombre() {
        return usuarioNombre;
    }
    public void setUsuarioNombre(String nombre) {
        this.usuarioNombre = nombre;
    }
    public String getUsuarioEmail() {
        return usuarioEmail;
    }
    public void setUsuarioEmail(String email) {
        this.usuarioEmail = email;
    }
        public String getUsuarioDireccion() {
        return usuarioDireccion;
    }
    public void setUsuarioDireccion(String usuarioDireccion) {
        this.usuarioDireccion = usuarioDireccion;
    }
    public int getUsuarioNumeroTelefonico() {
        return usuarioNumeroTelefonico;
    }
    public void setUsuarioNumeroTelefonico(int usuarioNumeroTelefonico) {
        this.usuarioNumeroTelefonico = usuarioNumeroTelefonico;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public int getComprobanteId() {
        return comprobanteId;
    }
    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }

    public Usuario() {
    }
    
    @Override
    public String toString() {
        return "Usuario [usuarioId=" + usuarioId + 
        ", usuarioRut=" + usuarioRut + 
        ", usuarioDv=" + usuarioDv + 
        ", usuarioNombre=" + usuarioNombre + 
        ", usuarioEmail=" + usuarioEmail + 
        ", usuarioDireccion="+ usuarioDireccion + 
        ", usuarioNumeroTelefonico=" + usuarioNumeroTelefonico + 
        ", tipoUsuario="+ tipoUsuario + 
        ", comprobanteId=" + comprobanteId + 
        "]";
    }

}
