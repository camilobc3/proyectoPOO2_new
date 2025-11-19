package Models;

/**
 * Clase que representa un carro (hereda de Vehiculo)
 */
public class Carro extends Vehiculo {

    private Integer hotelId;
    
    public Carro() {
        super();
    }
    
    public Carro(Integer id, String marcaGps) {
        super(id, marcaGps);
    }

    public Integer getHotelId() {
        return hotelId;
    }
    
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
}

