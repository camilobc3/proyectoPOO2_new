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
        
        conectarControladores(); // Llama al método que conecta
    }
    
    public ClienteController(ClienteRepository clienteRepository, ParticipacionRepository participacionRepository) {
        this.clienteRepository = clienteRepository;
        this.participacionRepository = participacionRepository;
        this.participacionController = new ParticipacionController();
        
        conectarControladores(); // Llama al mismo método
    }
    
    // Método privado para conectar los controladores
    private void conectarControladores() {
        ViajeController viajeController = new ViajeController();
        
        // Conectar ParticipacionController con ViajeController
        this.participacionController.setViajeController(viajeController);
        
        // Conectar ViajeController con ParticipacionController
        viajeController.setParticipacionController(this.participacionController);
    }
    
    public List<Cliente> getAllClientes() {
        return clienteRepository.getAllClientes();
    }
    
    public Cliente getClienteById(Integer id) {
        if (id == null) return null;
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
        if (id == null) return false;

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
        if (id == null) return false;
        clienteRepository.deleteCliente(id);
        return true;
    }
    
    public List<Participacion> getParticipacionesDeCliente(Integer clienteId){
        if (clienteId == null) return new ArrayList<>();
        List<Participacion> lista = participacionRepository.getParticipacionesByClienteId(clienteId);
        return lista != null ? lista : new ArrayList<>();
    }
    
    public double TrayectosPorViaje(Integer clienteId){   
        if (clienteId == null) return 0.0;

        double resultado = 0.0;
        double suma = 0.0;
        List<Participacion> misParticipaciones = getParticipacionesDeCliente(clienteId);

        if (misParticipaciones == null || misParticipaciones.size() < 1) { 
            return 0.0;
        }

        for(Participacion actual: misParticipaciones){
            suma += participacionController.numeroTrayectosPorParticipacion(actual.getId());
        }

        resultado = suma;
        return resultado;
    }
    
    public double promedioTrayectosPorViaje(){
        double sumaTotalTrayectos = 0.0;
        int totalViajes = 0;  // Solo viajes de clientes con >1 viaje
        int clientesConMasDeUnViaje = 0;

        List<Cliente> clientes = getAllClientes();

        for(Cliente actual: clientes){ 
            if (actual != null) {
                List<Participacion> participaciones = getParticipacionesDeCliente(actual.getId());

                // ❗ FILTRO IMPORTANTE: Solo clientes con MÁS DE UN VIAJE
                if (participaciones.size() > 1) {
                    clientesConMasDeUnViaje++;
                    totalViajes += participaciones.size();  // Todos sus viajes

                    for(Participacion p : participaciones){
                        sumaTotalTrayectos += participacionController.numeroTrayectosPorParticipacion(p.getId());
                    }
                }
            }
        }

        if(totalViajes == 0){
            return 0.0;
        }

        return sumaTotalTrayectos / totalViajes;
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
                    if (!respuesta.contains(id)) { 
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
                    if (!respuesta.contains(id)) { 
                        respuesta.add(id);
                    }
                }
            }
        }

        return respuesta;
    }
    
    public boolean isClienteConMasDeUnViaje(Integer clienteId){
        if (clienteId == null) return false;

        List<Participacion> misParticipaciones = getParticipacionesDeCliente(clienteId);
        if (misParticipaciones == null) return false;

        return misParticipaciones.size() > 1;
    }
}
