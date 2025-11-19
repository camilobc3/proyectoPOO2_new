package Models;

/**
 * Clase que representa una cuota o tarifa asociada a un viaje
 */
public class Cuota {
    private Integer id;
    private Double monto;
    private Integer viajeId; // Referencia a Viaje (relación 1-n)
    
    public Cuota() {
    }
    
    public Cuota(Integer id, Double monto, Integer viajeId) {
        this.id = id;
        this.monto = monto;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getMonto() {
        return monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    public Integer getViajeId() {
        return viajeId;
    }
    
    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }
}

