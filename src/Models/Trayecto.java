package Models;

/**
 * Clase que representa un trayecto o ruta de un viaje
 */
public class Trayecto {
    private Integer id;
    private Double costo;
    private Integer municipioId; // Referencia a Municipio
    
    public Trayecto() {
    }
    
    public Trayecto(Integer id, Double costo) {
        this.id = id;
        this.costo = costo;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }
    
    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }
}

