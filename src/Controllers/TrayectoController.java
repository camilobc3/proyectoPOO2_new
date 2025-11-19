package Controllers;

import Models.Trayecto;
import DataAccess.TrayectoRepository;
import DataAccess.MunicipioRepository;
import java.util.List;

public class TrayectoController {
    private TrayectoRepository trayectoRepository;
    private MunicipioRepository municipioRepository;
    
    public TrayectoController() {
        this.trayectoRepository = new TrayectoRepository();
        this.municipioRepository = new MunicipioRepository();
    }
    
    // Constructor for dependency injection
    public TrayectoController(TrayectoRepository trayectoRepository, MunicipioRepository municipioRepository) {
        this.trayectoRepository = trayectoRepository;
        this.municipioRepository = municipioRepository;
    }
    
    public List<Trayecto> getAllTrayectos() {
        return trayectoRepository.getAllTrayectos();
    }
    
    public Trayecto getTrayectoById(Integer id) {
        return trayectoRepository.findTrayectoById(id);
    }
    
    public boolean addTrayecto(Double costo, Integer municipioId) {
        if (costo == null || costo < 0) {
            return false;
        }
        
        if (municipioId != null && municipioRepository.findMunicipioById(municipioId) == null) {
            return false;
        }
        
        Trayecto trayecto = new Trayecto();
        trayecto.setCosto(costo);
        if (municipioId != null) {
            trayecto.setMunicipioId(municipioId);
        }
        
        trayectoRepository.saveTrayecto(trayecto);
        return true;
    }
    
    public boolean updateTrayecto(Integer id, Double costo, Integer municipioId) {
        Trayecto trayecto = trayectoRepository.findTrayectoById(id);
        if (trayecto == null) {
            return false;
        }
        
        if (costo != null && costo >= 0) {
            trayecto.setCosto(costo);
        }
        
        if (municipioId != null) {
            if (municipioRepository.findMunicipioById(municipioId) == null) {
                return false;
            }
            trayecto.setMunicipioId(municipioId);
        }
        
        trayectoRepository.saveTrayecto(trayecto);
        return true;
    }
    
    public boolean deleteTrayecto(Integer id) {
        trayectoRepository.deleteTrayecto(id);
        return true;
    }
}

