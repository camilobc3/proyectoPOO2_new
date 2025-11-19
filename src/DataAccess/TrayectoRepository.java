/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Trayecto;
import java.util.List;

public class TrayectoRepository {
    private IDataAccess<Trayecto> dataAccess;
    
    public TrayectoRepository() {
        this.dataAccess = new JsonRepository<>("trayectos.json", Trayecto.class);
    }
    
    // Constructor for dependency injection
    public TrayectoRepository(IDataAccess<Trayecto> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Trayecto> getAllTrayectos() {
        return dataAccess.findAll();
    }
    
    public Trayecto findTrayectoById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveTrayecto(Trayecto trayecto) {
        dataAccess.save(trayecto);
    }
    
    public void deleteTrayecto(Integer id) {
        dataAccess.delete(id);
    }
}

