package Controllers;

import Models.*;
import DataAccess.*;
import java.util.List;

public class ItinerarioTransporteController {
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    private ViajeRepository viajeRepository;
    private TrayectoRepository trayectoRepository;
    
    public ItinerarioTransporteController() {
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        this.viajeRepository = new ViajeRepository();
        this.trayectoRepository = new TrayectoRepository();
    }
    
    // Constructor for dependency injection
    public ItinerarioTransporteController(ItinerarioTransporteRepository itinerarioTransporteRepository, 
                                         ViajeRepository viajeRepository,
                                         TrayectoRepository trayectoRepository) {
        this.itinerarioTransporteRepository = itinerarioTransporteRepository;
        this.viajeRepository = viajeRepository;
        this.trayectoRepository = trayectoRepository;
    }
    
    public List<ItinerarioTransporte> getAllItinerariosTransporte() {
        return itinerarioTransporteRepository.getAllItinerariosTransporte();
    }
    
    public ItinerarioTransporte getItinerarioTransporteById(Integer id) {
        return itinerarioTransporteRepository.findItinerarioTransporteById(id);
    }
    
    public boolean addItinerarioTransporte(Integer orden, Integer viajeId, Integer trayectoId) {
        if (orden == null || orden < 0) {
            return false;
        }
        
        if (viajeId != null && viajeRepository.findViajeById(viajeId) == null) {
            return false;
        }
        
        if (trayectoId != null && trayectoRepository.findTrayectoById(trayectoId) == null) {
            return false;
        }
        
        ItinerarioTransporte itinerarioTransporte = new ItinerarioTransporte();
        itinerarioTransporte.setOrden(orden);
        if (viajeId != null) {
            itinerarioTransporte.setViajeId(viajeId);
        }
        if (trayectoId != null) {
            itinerarioTransporte.setTrayectoId(trayectoId);
        }
        
        itinerarioTransporteRepository.saveItinerarioTransporte(itinerarioTransporte);
        return true;
    }
    
    public boolean updateItinerarioTransporte(Integer id, Integer orden, Integer viajeId, Integer trayectoId) {
        ItinerarioTransporte itinerarioTransporte = itinerarioTransporteRepository.findItinerarioTransporteById(id);
        if (itinerarioTransporte == null) {
            return false;
        }
        
        if (orden != null && orden >= 0) {
            itinerarioTransporte.setOrden(orden);
        }
        
        if (viajeId != null) {
            if (viajeRepository.findViajeById(viajeId) == null) {
                return false;
            }
            itinerarioTransporte.setViajeId(viajeId);
        }
        
        if (trayectoId != null) {
            if (trayectoRepository.findTrayectoById(trayectoId) == null) {
                return false;
            }
            itinerarioTransporte.setTrayectoId(trayectoId);
        }
        
        itinerarioTransporteRepository.saveItinerarioTransporte(itinerarioTransporte);
        return true;
    }
    
    public boolean deleteItinerarioTransporte(Integer id) {
        itinerarioTransporteRepository.deleteItinerarioTransporte(id);
        return true;
    }
    
    public List<ServicioTransporte> getServiciosTransporteByItinerarioTransporte(Integer itinerarioId){
        ItinerarioTransporte itinerario = itinerarioTransporteRepository.findItinerarioTransporteById(itinerarioId); //Obtener itinerario
        Trayecto miTrayecto = trayectoRepository.findTrayectoById(itinerario.getTrayectoId()); //Obtener Trayecto relacionado
        TrayectoController trayectoController = new TrayectoController();
        return trayectoController.getServiciosTransportePorIdTrayecto(miTrayecto.getId()); //Retornar los serviciosTransporte relacionados al trayecto
    }
    
    public boolean isItinerarioConAlgunTrayectoTerrestre(Integer itinerarioId){
        ItinerarioTransporte itinerario = itinerarioTransporteRepository.findItinerarioTransporteById(itinerarioId); //Obtener itinerario
        Trayecto miTrayecto = trayectoRepository.findTrayectoById(itinerario.getTrayectoId()); //Obtener Trayecto relacionado
        TrayectoController trayectoController = new TrayectoController();
        return trayectoController.isTrayectoConAlgunServicioTerrestre(miTrayecto.getId());
    }
}

