package Models;

/**
 * Clase que representa una actividad turística
 */
public class ActividadTuristica {
    private Integer id;
    private String nombre;

    
    public ActividadTuristica() {
    }
    
    public ActividadTuristica(Integer id, String nombre, Integer municipioId) {
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

