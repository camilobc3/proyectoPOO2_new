package Models;

/**
 * Clase que representa un servicio de transporte
 */
public class ServicioTransporte {
    private Integer id;
    private String fechaInicio;
    private String fechaFin;
    private Double costo;
    private Integer trayectoId; // Referencia a Trayecto (relación 1-n)
    private Integer vehiculoId; // Referencia a Vehiculo (relación n-1)
    
    public ServicioTransporte() {
    }
    
    public ServicioTransporte(Integer id, String fechaInicio, String fechaFin, Double costo, Integer trayectoId, Integer vehiculoId) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public String getFechaFin() {
        return fechaFin;
    }
    
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public Integer getTrayectoId() {
        return trayectoId;
    }
    
    public void setTrayectoId(Integer trayectoId) {
        this.trayectoId = trayectoId;
    }
    
    public Integer getVehiculoId() {
        return vehiculoId;
    }
    
    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}

