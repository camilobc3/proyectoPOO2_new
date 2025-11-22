/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.AsignacionGuia;
import java.util.List;

public class AsignacionGuiaRepository {
    private IDataAccess<AsignacionGuia> dataAccess;
    
    public AsignacionGuiaRepository() {
        this.dataAccess = new JsonRepository<>("Data/asignacionesGuia.json", AsignacionGuia.class);
    }
    
    // Constructor for dependency injection
    public AsignacionGuiaRepository(IDataAccess<AsignacionGuia> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<AsignacionGuia> getAllAsignacionesGuia() {
        return dataAccess.findAll();
    }
    
    public AsignacionGuia findAsignacionGuiaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveAsignacionGuia(AsignacionGuia asignacionGuia) {
        dataAccess.save(asignacionGuia);
    }
    
    public void deleteAsignacionGuia(Integer id) {
        dataAccess.delete(id);
    }
}

