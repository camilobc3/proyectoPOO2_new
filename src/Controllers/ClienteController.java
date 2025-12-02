package Controllers;

import Models.Cliente;
import Models.Participacion;
import DataAccess.ClienteRepository;
import DataAccess.ParticipacionRepository;
import Controllers.ParticipacionController;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private ClienteRepository clienteRepository;
    private ParticipacionRepository participacionRepository;
    private final ParticipacionController participacionController;
    
    public ClienteController() {
        this.clienteRepository = new ClienteRepository();
        this.participacionRepository = new ParticipacionRepository();
        this.participacionController = new ParticipacionController();
    }
    
    // Constructor for dependency injection
    public ClienteController(ClienteRepository clienteRepository, ParticipacionRepository participacionRepository) {
        this.clienteRepository = clienteRepository;
        this.participacionRepository = participacionRepository;
        this.participacionController = new ParticipacionController();
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
    
    public List<Participacion> getParticipacionesDeCliente(Integer clienteId){
        return participacionRepository.getParticipacionesByClienteId(clienteId);
    }
    
    public double TrayectosPorViaje(Integer clienteId){
        double resultado = 0.0;
        double suma = 0.0;
        List<Participacion> misParticipaciones = getParticipacionesDeCliente(clienteId);
        if(misParticipaciones.size()<1){ //No se considera este cliente porque no ha hecho mas de 1 viaje
            resultado = 0.0;
            return resultado;
        }
        for(Participacion actual: misParticipaciones){
            suma+=participacionController.numeroTrayectosPorParticipacion(actual.getId());
        }
        resultado = suma;
        return resultado;
    }
    
    public double promedioTrayectosPorViaje(){
        double resultado = 0.0;
        double suma = 0.0;
        double denominador = 0.0;
        List<Cliente> clientes = getAllClientes();
        for(Cliente actual: clientes){ //Recorrer todos los clientes
            if(this.TrayectosPorViaje(actual.getId()) != 0.0){ //Validar si se considera el cliente o no
                suma += this.TrayectosPorViaje(actual.getId());
                denominador += 1;
            }
        }
        
        if(denominador == 0){
            return 0.0;
        }
        
        resultado = suma / denominador;
        return resultado;
    }
    
    public List<Integer> getAerolineasIdByClienteId(Integer clienteId){
        if (clienteId == null) return new ArrayList<>();

        List<Participacion> misParticipaciones = getParticipacionesDeCliente(clienteId);
        if (misParticipaciones == null || misParticipaciones.isEmpty()) return new ArrayList<>();
        List<Integer> respuesta = new ArrayList<>();
        
        for(Participacion actual : misParticipaciones){
            List<Integer> aerolineasId = participacionController.getAerolineasIdByParticipacionId(actual.getId());
            if (aerolineasId != null) {
                for (Integer id : aerolineasId) {
                    if (!respuesta.contains(id)) { // evitar duplicados
                        respuesta.add(id);
                    }
                }
            }
        }

        return respuesta;
    }
    
    public int conteoClientesEnUnaAerolineaYEnMunicipio(Integer aerolineaId, Integer municipioId){
        int respuesta = 0;

        List<Cliente> clientes = getAllClientes();

        for(Cliente actual : clientes){

            List<Integer> aerolineasDelCliente = getAerolineasIdByClienteId(actual.getId());
            List<Integer> municipiosDelCliente = getMunicipiosIdByClienteId(actual.getId());

            boolean usaAerolínea = aerolineasDelCliente.contains(aerolineaId);
            boolean pasaPorMunicipio = municipiosDelCliente.contains(municipioId);

            if(usaAerolínea && pasaPorMunicipio){
                respuesta++;
            }
        }

        return respuesta;
    }

    
    public List<Integer> getMunicipiosIdByClienteId(Integer clienteId){
        if (clienteId == null) return new ArrayList<>();

        List<Participacion> misParticipaciones = getParticipacionesDeCliente(clienteId);
        if (misParticipaciones == null || misParticipaciones.isEmpty()) return new ArrayList<>();
        List<Integer> respuesta = new ArrayList<>();
        
        for(Participacion actual : misParticipaciones){
            List<Integer> municipiosId = participacionController.getMunicipiosIdByParticipacionId(actual.getId());
            if (municipiosId != null) {
                for (Integer id : municipiosId) {
                    if (!respuesta.contains(id)) { // evitar duplicados
                        respuesta.add(id);
                    }
                }
            }
        }

        return respuesta;
    }
    
}


