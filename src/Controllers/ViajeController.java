package Controllers;

import Models.*;
import DataAccess.*;
import java.util.List;

public class ViajeController {
    private ViajeRepository viajeRepository;
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    
    public ViajeController() {
        this.viajeRepository = new ViajeRepository();
    }
    
    // Constructor for dependency injection
    public ViajeController(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }
    
    public List<Viaje> getAllViajes() {
        return viajeRepository.getAllViajes();
    }
    
    public Viaje getViajeById(Integer id) {
        return viajeRepository.findViajeById(id);
    }
    
    public boolean addViaje(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        Viaje viaje = new Viaje();
        viaje.setNombre(nombre.trim());
        
        viajeRepository.saveViaje(viaje);
        return true;
    }
    
    public boolean updateViaje(Integer id, String nombre) {
        Viaje viaje = viajeRepository.findViajeById(id);
        if (viaje == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            viaje.setNombre(nombre.trim());
        }
        
        viajeRepository.saveViaje(viaje);
        return true;
    }
    
    public boolean deleteViaje(Integer id) {
        viajeRepository.deleteViaje(id);
        return true;
    }
    
    public List<ItinerarioTransporte> getItinerariosDeViaje(Integer viajeId){
        return itinerarioTransporteRepository.getParticipacionesByViajeId(viajeId);
    }
    
    public int getNumeroDeItinerariosPorViaje(Integer viajeId){
        int resultado = 0;
        List<ItinerarioTransporte> misItinerarios = this.getItinerariosDeViaje(viajeId);
        resultado = misItinerarios.size();
        return resultado;
    }

}

