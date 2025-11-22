/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Usuario;
import java.util.List;

public class UsuarioRepository {
    private IDataAccess<Usuario> dataAccess;
    
    public UsuarioRepository() {
        this.dataAccess = new JsonRepository<>("Data/usuarios.json", Usuario.class);
    }
    
    // Constructor for dependency injection
    public UsuarioRepository(IDataAccess<Usuario> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Usuario> getAllUsuarios() {
        return dataAccess.findAll();
    }
    
    public Usuario findUsuarioById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveUsuario(Usuario usuario) {
        dataAccess.save(usuario);
    }
    
    public void deleteUsuario(Integer id) {
        dataAccess.delete(id);
    }
}

