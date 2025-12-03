package Controllers;

import Models.*;
import Utils.*;
import DataAccess.MunicipioRepository;
import java.util.ArrayList;
import java.util.List;

public class MunicipioController {
    private MunicipioRepository municipioRepository;
    private HotelController hotelController; 
    
    public MunicipioController() {
        this.municipioRepository = new MunicipioRepository();
        this.hotelController = new HotelController();
    }
    
    // Constructor for dependency injection
    public MunicipioController(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
        this.hotelController = new HotelController();
    }
    
    public List<Municipio> getAllMunicipios() {
        return municipioRepository.getAllMunicipios();
    }
    
    public Municipio getMunicipioById(Integer id) {
        return municipioRepository.findMunicipioById(id);
    }
    
    public boolean addMunicipio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        Municipio municipio = new Municipio();
        municipio.setNombre(nombre.trim());
        
        municipioRepository.saveMunicipio(municipio);
        return true;
    }
    
    public boolean updateMunicipio(Integer id, String nombre) {
        Municipio municipio = municipioRepository.findMunicipioById(id);
        if (municipio == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            municipio.setNombre(nombre.trim());
        }
        
        municipioRepository.saveMunicipio(municipio);
        return true;
    }
    
    public boolean deleteMunicipio(Integer id) {
        municipioRepository.deleteMunicipio(id);
        return true;
    }
}

