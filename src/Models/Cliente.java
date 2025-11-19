package Models;

/**
 * Clase que representa un cliente (hereda de Usuario)
 */
public class Cliente extends Usuario {
    
    public Cliente() {
        super();
    }
    
    public Cliente(Integer id, String nombre, String telefono, String correo) {
        super(id, nombre, telefono, correo);
    }
}

