package Controllers;

import Models.AsignacionGuia;
import DataAccess.AsignacionGuiaRepository;
import DataAccess.GuiaRepository;
import DataAccess.ActividadTuristicaRepository;
import java.util.List;

public class AsignacionGuiaController {
    private AsignacionGuiaRepository asignacionGuiaRepository;
    private GuiaRepository guiaRepository;
    private ActividadTuristicaRepository actividadTuristicaRepository;
    
    public AsignacionGuiaController() {
        this.asignacionGuiaRepository = new AsignacionGuiaRepository();
        this.guiaRepository = new GuiaRepository();
        this.actividadTuristicaRepository = new ActividadTuristicaRepository();
    }
    
    // Constructor for dependency injection
    public AsignacionGuiaController(AsignacionGuiaRepository asignacionGuiaRepository, 
                                   GuiaRepository guiaRepository,
                                   ActividadTuristicaRepository actividadTuristicaRepository) {
        this.asignacionGuiaRepository = asignacionGuiaRepository;
        this.guiaRepository = guiaRepository;
        this.actividadTuristicaRepository = actividadTuristicaRepository;
    }
    
    public List<AsignacionGuia> getAllAsignacionesGuia() {
        return asignacionGuiaRepository.getAllAsignacionesGuia();
    }
    
    public AsignacionGuia getAsignacionGuiaById(Integer id) {
        return asignacionGuiaRepository.findAsignacionGuiaById(id);
    }
    
    public boolean addAsignacionGuia(Integer guiaId, Integer actividadTuristicaId) {
        if (guiaId == null || actividadTuristicaId == null) {
            return false;
        }
        
        if (guiaRepository.findGuiaById(guiaId) == null) {
            return false;
        }
        
        if (actividadTuristicaRepository.findActividadTuristicaById(actividadTuristicaId) == null) {
            return false;
        }
        
        AsignacionGuia asignacionGuia = new AsignacionGuia();
        asignacionGuia.setGuiaId(guiaId);
        asignacionGuia.setActividadTuristicaId(actividadTuristicaId);
        
        asignacionGuiaRepository.saveAsignacionGuia(asignacionGuia);
        return true;
    }
    
    public boolean updateAsignacionGuia(Integer id, Integer guiaId, Integer actividadTuristicaId) {
        AsignacionGuia asignacionGuia = asignacionGuiaRepository.findAsignacionGuiaById(id);
        if (asignacionGuia == null) {
            return false;
        }
        
        if (guiaId != null) {
            if (guiaRepository.findGuiaById(guiaId) == null) {
                return false;
            }
            asignacionGuia.setGuiaId(guiaId);
        }
        
        if (actividadTuristicaId != null) {
            if (actividadTuristicaRepository.findActividadTuristicaById(actividadTuristicaId) == null) {
                return false;
            }
            asignacionGuia.setActividadTuristicaId(actividadTuristicaId);
        }
        
        asignacionGuiaRepository.saveAsignacionGuia(asignacionGuia);
        return true;
    }
    
    public boolean deleteAsignacionGuia(Integer id) {
        asignacionGuiaRepository.deleteAsignacionGuia(id);
        return true;
    }
}

