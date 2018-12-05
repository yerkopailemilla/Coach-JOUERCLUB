package cl.jouer_club.coach_jouerclub.models.workshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import cl.jouer_club.coach_jouerclub.models.Link;

public class WorkshopModel implements Serializable {

    @SerializedName("identificador")
    @Expose
    private int identificador;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("ubication")
    @Expose
    private String ubication;

    @SerializedName("jugadores")
    @Expose
    private int jugadores;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("inicio")
    @Expose
    private String inicio;
    @SerializedName("termino")
    @Expose
    private String termino;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("instructor")
    @Expose
    private int instructor;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("fechaActualizacion")
    @Expose
    private String fechaActualizacion;
    @SerializedName("fechaEliminacion")
    @Expose
    private String fechaEliminacion = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public WorkshopModel() {
    }

    public WorkshopModel(String nombre,
                         String descripcion,
                         int jugadores,
                         String latitud,
                         String longitud,
                         String inicio,
                         String termino,
                         String estado,
                         int instructor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.jugadores = jugadores;
        this.latitud = latitud;
        this.longitud = longitud;
        this.inicio = inicio;
        this.termino = termino;
        this.estado = estado;
        this.instructor = instructor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getInstructor() {
        return instructor;
    }

    public void setInstructor(int instructor) {
        this.instructor = instructor;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
