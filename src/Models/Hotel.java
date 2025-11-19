package Models;

/**
 * Clase que representa un hotel
 */
public class Hotel {
    private Integer id;
    private String nombre;
    private Integer municipioId; // Referencia a Municipio (relación 1-n)
    
    public Hotel() {
    }
    
    public Hotel(Integer id, String nombre, Integer municipioId) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getMunicipioId() {
        return municipioId;
    }
    
    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }
}

