package Controllers;

import Models.Aerolinea;
import DataAccess.AerolineaRepository;
import java.util.List;

public class AerolineaController {
    private AerolineaRepository aerolineaRepository;
    private ServicioTransporteController servicioRespository;
    
    public AerolineaController() {
        this.aerolineaRepository = new AerolineaRepository();
    }
    
    // Constructor for dependency injection
    public AerolineaController(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }
    
    public List<Aerolinea> getAllAerolineas() {
        return aerolineaRepository.getAllAerolineas();
    }
    
    public Aerolinea getAerolineaById(Integer id) {
        return aerolineaRepository.findAerolineaById(id);
    }
    
    public boolean addAerolinea(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(nombre.trim());
        
        aerolineaRepository.saveAerolinea(aerolinea);
        return true;
    }
    
    public boolean updateAerolinea(Integer id, String nombre) {
        Aerolinea aerolinea = aerolineaRepository.findAerolineaById(id);
        if (aerolinea == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            aerolinea.setNombre(nombre.trim());
        }
        
        aerolineaRepository.saveAerolinea(aerolinea);
        return true;
    }
    
    public boolean deleteAerolinea(Integer id) {
        aerolineaRepository.deleteAerolinea(id);
        return true;
    }
    
}

