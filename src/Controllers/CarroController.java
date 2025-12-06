package Controllers;

import Models.Carro;
import DataAccess.CarroRepository;
import java.util.List;

public class CarroController {
    private CarroRepository carroRepository;
    
    //Constructor por defecto
    public CarroController() {
        this.carroRepository = new CarroRepository();
    }
    
    // Constructor for dependency injection
    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }
    
    public List<Carro> getAllCarros() {
        return carroRepository.getAllCarros();
    }
    
    public Carro getCarroById(Integer id) {
        return carroRepository.findCarroById(id);
    }
    
    public boolean addCarro(String marcaGps) {
        if (marcaGps == null || marcaGps.trim().isEmpty()) {
            return false;
        }
        
        Carro carro = new Carro();
        carro.setMarcaGps(marcaGps.trim());
        
        carroRepository.saveCarro(carro);
        return true;
    }
    
    public boolean updateCarro(Integer id, String marcaGps) {
        Carro carro = carroRepository.findCarroById(id);
        if (carro == null) {
            return false;
        }
        
        if (marcaGps != null && !marcaGps.trim().isEmpty()) {
            carro.setMarcaGps(marcaGps.trim());
        }
        
        carroRepository.saveCarro(carro);
        return true;
    }
    
    public boolean deleteCarro(Integer id) {
        carroRepository.deleteCarro(id);
        return true;
    }
}

