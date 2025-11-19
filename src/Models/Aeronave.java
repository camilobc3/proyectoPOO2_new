package Models;

/**
 * Clase que representa una aeronave (hereda de Vehiculo)
 */
public class Aeronave extends Vehiculo {
    private Integer aerolineaId; // Referencia a Aerolinea (relación 1-n)
    
    public Aeronave() {
        super();
    }
    
    public Aeronave(Integer id, String marcaGps, Integer aerolineaId) {
        super(id, marcaGps);
    }
    
    public Integer getAerolineaId() {
        return aerolineaId;
    }
    
    public void setAerolineaId(Integer aerolineaId) {
        this.aerolineaId = aerolineaId;
    }
}

