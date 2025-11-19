package Controllers;

import Models.ActividadTuristica;
import DataAccess.ActividadTuristicaRepository;
import java.util.List;

public class ActividadTuristicaController {
    private ActividadTuristicaRepository actividadTuristicaRepository;
    
    public ActividadTuristicaController() {
        this.actividadTuristicaRepository = new ActividadTuristicaRepository();
    }
    
    // Constructor for dependency injection
    public ActividadTuristicaController(ActividadTuristicaRepository actividadTuristicaRepository) {
        this.actividadTuristicaRepository = actividadTuristicaRepository;
    }
    
    public List<ActividadTuristica> getAllActividadesTuristicas() {
        return actividadTuristicaRepository.getAllActividadesTuristicas();
    }
    
    public ActividadTuristica getActividadTuristicaById(Integer id) {
        return actividadTuristicaRepository.findActividadTuristicaById(id);
    }
    
    public boolean addActividadTuristica(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        ActividadTuristica actividadTuristica = new ActividadTuristica();
        actividadTuristica.setNombre(nombre.trim());
        
        actividadTuristicaRepository.saveActividadTuristica(actividadTuristica);
        return true;
    }
    
    public boolean updateActividadTuristica(Integer id, String nombre) {
        ActividadTuristica actividadTuristica = actividadTuristicaRepository.findActividadTuristicaById(id);
        if (actividadTuristica == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            actividadTuristica.setNombre(nombre.trim());
        }
        
        actividadTuristicaRepository.saveActividadTuristica(actividadTuristica);
        return true;
    }
    
    public boolean deleteActividadTuristica(Integer id) {
        actividadTuristicaRepository.deleteActividadTuristica(id);
        return true;
    }
}

