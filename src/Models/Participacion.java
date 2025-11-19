package Models;

/**
 * Clase de asociación que representa la participación de un cliente en un viaje
 */
public class Participacion {
    private Integer id;
    private Integer viajeId; // Referencia a Viaje
    private Integer clienteId; // Referencia a Cliente
    
    public Participacion() {
    }
    
    public Participacion(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getViajeId() {
        return viajeId;
    }
    
    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }
    
    public Integer getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}

