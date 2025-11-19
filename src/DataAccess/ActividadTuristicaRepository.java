/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.ActividadTuristica;
import java.util.List;

public class ActividadTuristicaRepository {
    private IDataAccess<ActividadTuristica> dataAccess;
    
    public ActividadTuristicaRepository() {
        this.dataAccess = new JsonRepository<>("actividadesTuristicas.json", ActividadTuristica.class);
    }
    
    // Constructor for dependency injection
    public ActividadTuristicaRepository(IDataAccess<ActividadTuristica> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<ActividadTuristica> getAllActividadesTuristicas() {
        return dataAccess.findAll();
    }
    
    public ActividadTuristica findActividadTuristicaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveActividadTuristica(ActividadTuristica actividadTuristica) {
        dataAccess.save(actividadTuristica);
    }
    
    public void deleteActividadTuristica(Integer id) {
        dataAccess.delete(id);
    }
}

