package Models;

/**
 * Clase que representa un viaje
 */
public class Viaje {
    private Integer id;
    private String nombre;
    
    public Viaje() {
    }
    
    public Viaje(Integer id, String nombre, Integer planId) {
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
    
}

