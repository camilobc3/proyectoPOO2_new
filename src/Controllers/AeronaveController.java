package Controllers;

import Models.Aeronave;
import Models.Trayecto;
import DataAccess.AeronaveRepository;
import DataAccess.AerolineaRepository;
import Utils.filtrarListaPorId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AeronaveController {
    private AeronaveRepository aeronaveRepository;
    private AerolineaRepository aerolineaRepository;

    //Constructor por defecto
    public AeronaveController() {
        this.aeronaveRepository = new AeronaveRepository();
        this.aerolineaRepository = new AerolineaRepository();
    }
    
    // Constructor for dependency injection
    public AeronaveController(AeronaveRepository aeronaveRepository, AerolineaRepository aerolineaRepository) {
        this.aeronaveRepository = aeronaveRepository;
        this.aerolineaRepository = aerolineaRepository;
    }
    
    public List<Aeronave> getAllAeronaves() {
        return aeronaveRepository.getAllAeronaves();
    }
    
    public Aeronave getAeronaveById(Integer id) {
        return aeronaveRepository.findAeronaveById(id);
    }
    
    public boolean addAeronave(String marcaGps, Integer aerolineaId) {
        if (marcaGps == null || marcaGps.trim().isEmpty()) {
            return false;
        }
        
        if (aerolineaId != null && aerolineaRepository.findAerolineaById(aerolineaId) == null) {
            return false;
        }
        
        Aeronave aeronave = new Aeronave();
        aeronave.setMarcaGps(marcaGps.trim());
        if (aerolineaId != null) {
            aeronave.setAerolineaId(aerolineaId);
        }
        
        aeronaveRepository.saveAeronave(aeronave);
        return true;
    }
    
    public boolean updateAeronave(Integer id, String marcaGps, Integer aerolineaId) {
        Aeronave aeronave = aeronaveRepository.findAeronaveById(id);
        if (aeronave == null) {
            return false;
        }
        
        if (marcaGps != null && !marcaGps.trim().isEmpty()) {
            aeronave.setMarcaGps(marcaGps.trim());
        }
        
        if (aerolineaId != null) {
            if (aerolineaRepository.findAerolineaById(aerolineaId) == null) {
                return false;
            }
            aeronave.setAerolineaId(aerolineaId);
        }
        
        aeronaveRepository.saveAeronave(aeronave);
        return true;
    }
    
    public boolean deleteAeronave(Integer id) {
        aeronaveRepository.deleteAeronave(id);
        return true;
    }
    

    
    public List<Aeronave> getAeronavesByAerolineaId(Integer aerolineaId){
        List<Aeronave> aeronaves = getAllAeronaves();
        List<Aeronave> respuesta = filtrarListaPorId.filtrar(aeronaves, a -> a.getAerolineaId().equals(aerolineaId));
        return respuesta;
    }
    
    public Trayecto menorCostoTrayectoAerolinea(Integer idAerolinea){
        ServicioTransporteController servicioTransporteController = new ServicioTransporteController();
        
        Trayecto respuesta = null;
        Double menor = Double.MAX_VALUE;
        List<Aeronave> aeronavesAerolinea = getAeronavesByAerolineaId(idAerolinea);
        for(Aeronave a : aeronavesAerolinea){
            if(servicioTransporteController.menorCostoTrayectoAeronave(a.getId()).getCosto() < menor){
                respuesta = servicioTransporteController.menorCostoTrayectoAeronave(a.getId());
                menor = servicioTransporteController.menorCostoTrayectoAeronave(a.getId()).getCosto();
            }
        }
        return respuesta;
    }

}

