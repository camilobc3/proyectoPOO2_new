package Models;

/**
 * Clase que representa un guía turístico
 */
public class Guia extends Usuario {
    
    public Guia() {
        super();
    }
    
    public Guia(Integer id, String nombre, String telefono, String correo) {
        super(id, nombre, telefono, correo);
    }   
}

