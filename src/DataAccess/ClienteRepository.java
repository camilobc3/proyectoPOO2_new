/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Cliente;
import java.util.List;

public class ClienteRepository {
    private IDataAccess<Cliente> dataAccess;
    
    public ClienteRepository() {
        this.dataAccess = new JsonRepository<>("Data/clientes.json", Cliente.class);
    }
    
    // Constructor for dependency injection
    public ClienteRepository(IDataAccess<Cliente> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Cliente> getAllClientes() {
        return dataAccess.findAll();
    }
    
    public Cliente findClienteById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveCliente(Cliente cliente) {
        dataAccess.save(cliente);
    }
    
    public void deleteCliente(Integer id) {
        dataAccess.delete(id);
    }
}

