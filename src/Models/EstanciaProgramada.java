package Models;

/**
 * Clase de asociación que representa una estancia programada (vincula Habitacion con Usuario)
 */
public class EstanciaProgramada {
    private Integer id;
    private Integer habitacionId; // Referencia a Habitacion
    private Integer itinerarioTransporteId; // Referencia a Usuario
    
    public EstanciaProgramada() {
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getHabitacionId() {
        return habitacionId;
    }
    
    public void setHabitacionId(Integer habitacionId) {
        this.habitacionId = habitacionId;
    }
    
    public Integer getItinerarioTransporteId() {
        return itinerarioTransporteId;
    }
    
    public void setItinerarioTransporteId(Integer itinerarioTransporteId) {
        this.itinerarioTransporteId = itinerarioTransporteId;
    }
}

