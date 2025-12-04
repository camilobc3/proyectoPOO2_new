/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Participacion;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionRepository {
    private IDataAccess<Participacion> dataAccess;
    
    public ParticipacionRepository() {
        this.dataAccess = new JsonRepository<>("Data/participaciones.json", Participacion.class);
    }
    
    // Constructor for dependency injection
    public ParticipacionRepository(IDataAccess<Participacion> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Participacion> getAllParticipaciones() {
        return dataAccess.findAll();
    }
    
    public Participacion findParticipacionById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveParticipacion(Participacion participacion) {
        dataAccess.save(participacion);
    }
    
    public void deleteParticipacion(Integer id) {
        dataAccess.delete(id);
    }
}

