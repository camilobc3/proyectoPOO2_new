package Models;

/**
 * Clase que representa una habitación de hotel
 */
public class Habitacion {
    private Integer id;
    private Integer capacidad;
    private Double costo;
    private Integer hotelId; // Referencia a Hotel (relación 1-n)
    
    public Habitacion() {
    }
    
    public Habitacion(Integer id, Integer capacidad, Double costo, Integer hotelId) {
        this.id = id;
        this.capacidad = capacidad;
        this.costo = costo;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public Double getCosto() {
        return costo;
    }
    
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
    public Integer getHotelId() {
        return hotelId;
    }
    
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
}

