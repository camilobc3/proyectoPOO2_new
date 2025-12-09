package Controllers;

import Models.*;
import DataAccess.ParticipacionRepository;
import DataAccess.ViajeRepository;
import DataAccess.ClienteRepository;
import Utils.filtrarListaPorId;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionController {
    private ParticipacionRepository participacionRepository;
    private ViajeRepository viajeRepository;
    private ClienteRepository clienteRepository;
    // ya no es final ni se instancia aquí para evitar la dependencia circular
    private ViajeController viajeController;
    
    //Constructor por defecto
    public ParticipacionController() {
        this.participacionRepository = new ParticipacionRepository();
        this.viajeRepository = new ViajeRepository();
        this.clienteRepository = new ClienteRepository();
        // No crear new ViajeController() aquí -> evita la recursión infinita
        //this.viajeController = null;
    }

    // Constructor for dependency injection
    public ParticipacionController(ParticipacionRepository participacionRepository, 
                                  ViajeRepository viajeRepository,
                                  ClienteRepository clienteRepository) {
        this.participacionRepository = participacionRepository;
        this.viajeRepository = viajeRepository;
        this.clienteRepository = clienteRepository;
        this.viajeController = null;
    }

    // Setter para inyectar la referencia al ViajeController (rompe la recursión)
    public void setViajeController(ViajeController viajeController) {
        this.viajeController = viajeController;
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

    public Viaje getViajeDeParticipacion(Integer participacionId) {
        if (participacionId == null) return null;

        Participacion participacion = getParticipacionById(participacionId);
        if (participacion == null) return null;

        Integer viajeId = participacion.getViajeId();
        if (viajeId == null) return null;

        return viajeRepository.findViajeById(viajeId);
    }

    public List<Integer> getMunicipiosIdByParticipacionId(Integer participacionId) {
        ViajeController viajeController = new ViajeController();
        Viaje viaje = getViajeDeParticipacion(participacionId);
        if (viaje == null) return new ArrayList<>();
        return viajeController.getMunicipiosIdByViajeId(viaje.getId());
    }


    public int numeroTrayectosPorParticipacion(Integer participacionId){  
        ViajeController viajeController = new ViajeController();
        int resultado = 0;
        Viaje miViaje = getViajeDeParticipacion(participacionId);
        if (miViaje == null) return 0;
        resultado = viajeController.getNumeroDeItinerariosPorViaje(miViaje.getId());
        return resultado;
    }

    public List<Integer> getAerolineasIdByParticipacionId(Integer participacionId){
        ViajeController viajeController = new ViajeController();
        Viaje miViaje = getViajeDeParticipacion(participacionId);
        if(miViaje==null) return new ArrayList<>();
        return viajeController.getAerolineasByViajeId(miViaje.getId());
    }

    public Cliente getClienteByParticipacionId(Integer participacionId) {
        if (participacionId == null) return null;

        Participacion participacion = getParticipacionById(participacionId);
        if (participacion == null) return null;

        Integer clienteId = participacion.getClienteId();
        if (clienteId == null) return null;

        return clienteRepository.findClienteById(clienteId);
    }
    
    //Método D
    public double sumatoriaTrayectoresAreosParticipacion(Integer participacionId){
        ViajeController viajeController = new ViajeController();
        Participacion participacion = getParticipacionById(participacionId);
        double respuesta = 0.0;
        respuesta = viajeController.sumatoriaTrayectosAereos(participacion.getViajeId());
        return respuesta;
    }
    
    public List<Participacion> participacionesByClienteId(Integer clienteId){
        List<Participacion> participaciones = getAllParticipaciones();
        List<Participacion> respuesta = filtrarListaPorId.filtrar(participaciones, a -> a.getClienteId().equals(clienteId));
        return respuesta;
    }

}