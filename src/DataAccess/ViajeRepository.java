/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Viaje;
import java.util.List;

public class ViajeRepository {
    private IDataAccess<Viaje> dataAccess;
    
    public ViajeRepository() {
        this.dataAccess = new JsonRepository<>("Data/viajes.json", Viaje.class);
    }
    
    // Constructor for dependency injection
    public ViajeRepository(IDataAccess<Viaje> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Viaje> getAllViajes() {
        return dataAccess.findAll();
    }
    
    public Viaje findViajeById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveViaje(Viaje viaje) {
        dataAccess.save(viaje);
    }
    
    public void deleteViaje(Integer id) {
        dataAccess.delete(id);
    }
}

