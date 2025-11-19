package Controllers;

import Models.Usuario;
import DataAccess.UsuarioRepository;
import java.util.List;

public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    
    public UsuarioController() {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    // Constructor for dependency injection
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.getAllUsuarios();
    }
    
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findUsuarioById(id);
    }
    
    // Note: Usuario is abstract, use Cliente or Guia instead
    public boolean addUsuario(String nombre, String telefono, String correo) {
        // Usuario is abstract, this method should not be used directly
        return false;
    }
    
    public boolean updateUsuario(Integer id, String nombre, String telefono, String correo) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        if (usuario == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            usuario.setNombre(nombre.trim());
        }
        if (telefono != null) {
            usuario.setTelefono(telefono.trim());
        }
        if (correo != null && !correo.trim().isEmpty()) {
            usuario.setCorreo(correo.trim());
        }
        
        usuarioRepository.saveUsuario(usuario);
        return true;
    }
    
    public boolean deleteUsuario(Integer id) {
        usuarioRepository.deleteUsuario(id);
        return true;
    }
}

