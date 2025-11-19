package Controllers;

import Models.Cliente;
import DataAccess.ClienteRepository;
import java.util.List;

public class ClienteController {
    private ClienteRepository clienteRepository;
    
    public ClienteController() {
        this.clienteRepository = new ClienteRepository();
    }
    
    // Constructor for dependency injection
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    public List<Cliente> getAllClientes() {
        return clienteRepository.getAllClientes();
    }
    
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findClienteById(id);
    }
    
    public boolean addCliente(String nombre, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty() || 
            correo == null || correo.trim().isEmpty()) {
            return false;
        }
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre.trim());
        cliente.setTelefono(telefono != null ? telefono.trim() : "");
        cliente.setCorreo(correo.trim());
        
        clienteRepository.saveCliente(cliente);
        return true;
    }
    
    public boolean updateCliente(Integer id, String nombre, String telefono, String correo) {
        Cliente cliente = clienteRepository.findClienteById(id);
        if (cliente == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            cliente.setNombre(nombre.trim());
        }
        if (telefono != null) {
            cliente.setTelefono(telefono.trim());
        }
        if (correo != null && !correo.trim().isEmpty()) {
            cliente.setCorreo(correo.trim());
        }
        
        clienteRepository.saveCliente(cliente);
        return true;
    }
    
    public boolean deleteCliente(Integer id) {
        clienteRepository.deleteCliente(id);
        return true;
    }
}

