package Models;

/**
 * Clase abstracta que representa un vehículo genérico
 */
public abstract class Vehiculo {
    protected Integer id;
    protected String marcaGps;
    
    public Vehiculo() {
    }
    
    public Vehiculo(Integer id, String marcaGps) {
        this.id = id;
        this.marcaGps = marcaGps;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMarcaGps() {
        return marcaGps;
    }
    
    public void setMarcaGps(String marcaGps) {
        this.marcaGps = marcaGps;
    }
}

