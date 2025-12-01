package Controllers;

import Models.*;
import DataAccess.*;
import java.util.ArrayList;
import java.util.List;

public class TrayectoController {
    private ServicioTransporteRepository servicioTransporteRepository;
    private TrayectoRepository trayectoRepository;
    private MunicipioRepository municipioRepository;
    
    public TrayectoController() {
        this.trayectoRepository = new TrayectoRepository();
        this.municipioRepository = new MunicipioRepository();
        this.servicioTransporteRepository = new ServicioTransporteRepository();
    }
    
    // Constructor for dependency injection
    public TrayectoController(TrayectoRepository trayectoRepository, MunicipioRepository municipioRepository) {
        this.trayectoRepository = trayectoRepository;
        this.municipioRepository = municipioRepository;
        this.servicioTransporteRepository = new ServicioTransporteRepository();
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
    
    public List<ServicioTransporte> getServiciosTransportePorIdTrayecto(Integer trayectoId){
        return servicioTransporteRepository.getServicioTransporteByTrayectoId(trayectoId);
    }
    
    public boolean isTrayectoConAlgunServicioTerrestre(Integer trayectoId){
        List<ServicioTransporte> misServiciosTransporte = getServiciosTransportePorIdTrayecto(trayectoId);
        for(ServicioTransporte actual : misServiciosTransporte){
            ServicioTransporteController servicioTransporteController = new ServicioTransporteController();
            if(servicioTransporteController.isTerrestre(actual.getId())){
                return true;
            }
        }
        return false;
    }
    
    public List<Integer> getAerolineasIdByTrayectoId(Integer trayectoId){
        List<ServicioTransporte> misServicios = getServiciosTransportePorIdTrayecto(trayectoId);
        List<Integer> respuesta = new ArrayList<>();
        ServicioTransporteController servicioTransporteController = new ServicioTransporteController();

        for(ServicioTransporte actual : misServicios){
            Integer aerolineaId = servicioTransporteController.getAerolineaIdFromServicioT(actual.getId());
            if(aerolineaId != null){       // Asume null si el vehiculo no es aeronave o no existe
                respuesta.add(aerolineaId);
            }
        }
        return respuesta;
    }

}

