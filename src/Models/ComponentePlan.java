package Models;

/**
 * Clase de asociación que representa un componente de un plan (vincula Plan con ActividadTuristica)
 */
public class ComponentePlan {
    private Integer id;
    private Integer planId; // Referencia a Plan
    private Integer actividadTuristicaId; // Referencia a ActividadTuristica
    
    public ComponentePlan() {
    }
    
    public ComponentePlan(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPlanId() {
        return planId;
    }
    
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
    
    public Integer getActividadTuristicaId() {
        return actividadTuristicaId;
    }
    
    public void setActividadTuristicaId(Integer actividadTuristicaId) {
        this.actividadTuristicaId = actividadTuristicaId;
    }
}

