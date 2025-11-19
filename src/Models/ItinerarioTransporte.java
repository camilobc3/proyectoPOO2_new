package Models;

/**
 * Clase de asociación que representa un itinerario de transporte (vincula Viaje con Trayecto)
 */
public class ItinerarioTransporte {
    private Integer id;
    private Integer orden;
    private Integer viajeId; // Referencia a Viaje
    private Integer trayectoId; // Referencia a Trayecto
    
    public ItinerarioTransporte() {
    }
    
    public ItinerarioTransporte(Integer id, Integer orden) {
        this.id = id;
        this.orden = orden;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOrden() {
        return orden;
    }
    
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    
    public Integer getViajeId() {
        return viajeId;
    }
    
    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }
    
    public Integer getTrayectoId() {
        return trayectoId;
    }
    
    public void setTrayectoId(Integer trayectoId) {
        this.trayectoId = trayectoId;
    }
}

