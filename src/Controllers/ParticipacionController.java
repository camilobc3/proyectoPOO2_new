package Controllers;

import Models.*;
import DataAccess.ParticipacionRepository;
import DataAccess.ViajeRepository;
import DataAccess.ClienteRepository;
import java.util.List;

public class ParticipacionController {
    private ParticipacionRepository participacionRepository;
    private ViajeRepository viajeRepository;
    private ClienteRepository clienteRepository;
    
    public ParticipacionController() {
        this.participacionRepository = new ParticipacionRepository();
        this.viajeRepository = new ViajeRepository();
        this.clienteRepository = new ClienteRepository();
    }
    
    // Constructor for dependency injection
    public ParticipacionController(ParticipacionRepository participacionRepository, 
                                  ViajeRepository viajeRepository,
                                  ClienteRepository clienteRepository) {
        this.participacionRepository = participacionRepository;
        this.viajeRepository = viajeRepository;
        this.clienteRepository = clienteRepository;
    }
    
    public List<Participacion> getAllParticipaciones() {
        return participacionRepository.getAllParticipaciones();
    }
    
    public Participacion getParticipacionById(Integer id) {
        return participacionRepository.findParticipacionById(id);
    }
    
    public boolean addParticipacion(Integer viajeId, Integer clienteId) {
        if (viajeId == null || clienteId == null) {
            return false;
        }
        
        if (viajeRepository.findViajeById(viajeId) == null) {
            return false;
        }
        
        if (clienteRepository.findClienteById(clienteId) == null) {
            return false;
        }
        
        Participacion participacion = new Participacion();
        participacion.setViajeId(viajeId);
        participacion.setClienteId(clienteId);
        
        participacionRepository.saveParticipacion(participacion);
        return true;
    }
    
    public boolean updateParticipacion(Integer id, Integer viajeId, Integer clienteId) {
        Participacion participacion = participacionRepository.findParticipacionById(id);
        if (participacion == null) {
            return false;
        }
        
        if (viajeId != null) {
            if (viajeRepository.findViajeById(viajeId) == null) {
                return false;
            }
            participacion.setViajeId(viajeId);
        }
        
        if (clienteId != null) {
            if (clienteRepository.findClienteById(clienteId) == null) {
                return false;
            }
            participacion.setClienteId(clienteId);
        }
        
        participacionRepository.saveParticipacion(participacion);
        return true;
    }
    
    public boolean deleteParticipacion(Integer id) {
        participacionRepository.deleteParticipacion(id);
        return true;
    }
    
        public Viaje getViajeDeParticipacion(Integer participacionId){
        List<Viaje> viajes = viajeRepository.getAllViajes();
        Viaje miViaje = null;
        int idEsperado = getParticipacionById(participacionId).getViajeId();
        for(Viaje actual: viajes){
            if(actual.getId().equals(idEsperado)){
                miViaje = actual;
                return miViaje;
            }
        }
        return null;
    }
    
    public int numeroTrayectosPorParticipacion(Integer participacionId){
        int resultado = 0;
        Viaje miViaje = this.getViajeDeParticipacion(participacionId);
        ViajeController viajeController = new ViajeController();
        resultado = viajeController.getNumeroDeItinerariosPorViaje(miViaje.getId());
        return resultado;
    }
}

