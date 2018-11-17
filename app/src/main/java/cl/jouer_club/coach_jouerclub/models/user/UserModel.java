package cl.jouer_club.coach_jouerclub.models.user;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("identificador")
    private int identificador;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellido")
    private String apellido;
    @SerializedName("alias")
    private String alias;
    @SerializedName("correo")
    private String correo;
    @SerializedName("contraseña")
    private String clave;
    @SerializedName("celular")
    private String celular;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("estado")
    private String estado;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("codigoVerificacion")
    private String codigoVerificacion;
    @SerializedName("fechaCreacion")
    private String fechaCreacion;
    @SerializedName("fechaActualizacion")
    private String fechaActualizacion;
    @SerializedName("fechaEliminacion")
    private String fechaEliminacion;

    public UserModel() {
    }

    //Login request constructor
    public UserModel(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }
}
