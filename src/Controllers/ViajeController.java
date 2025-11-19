package Controllers;

import Models.Viaje;
import DataAccess.ViajeRepository;
import java.util.List;

public class ViajeController {
    private ViajeRepository viajeRepository;
    
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
}

