package Models;

/**
 * Clase de asociación que representa la asignación de un guía a una actividad turística
 */
public class AsignacionGuia {
    private Integer id;
    private Integer guiaId; // Referencia a Guia
    private Integer actividadTuristicaId; // Referencia a ActividadTuristica
    
    public AsignacionGuia() {
    }
    
    public AsignacionGuia(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getGuiaId() {
        return guiaId;
    }
    
    public void setGuiaId(Integer guiaId) {
        this.guiaId = guiaId;
    }
    
    public Integer getActividadTuristicaId() {
        return actividadTuristicaId;
    }
    
    public void setActividadTuristicaId(Integer actividadTuristicaId) {
        this.actividadTuristicaId = actividadTuristicaId;
    }
}

