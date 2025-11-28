package Models;

/**
 * Clase que representa un paquete contratado por un usuario
 */
public class PaqueteContratado {
    private Integer id;
    private String fechaInicio;
    private String fechaFin;
    private Integer usuarioId; // Referencia a Usuario (relación 1-n)
    private Integer planId; // Referencia a Plan (relación 1-n)
    private Integer viajeId; // Referencia a Viaje
    
    public PaqueteContratado() {
    }
    
    public PaqueteContratado(Integer id, String fechaInicio, String fechaFin, Integer usuarioId, Integer planId, Integer viajeId) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.viajeId = viajeId;
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
    
    public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public Integer getPlanId() {
        return planId;
    }
    
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * @return the viajeId
     */
    public Integer getViajeId() {
        return viajeId;
    }

    /**
     * @param viajeId the viajeId to set
     */
    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }
}

