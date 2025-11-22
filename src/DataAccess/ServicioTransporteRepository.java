/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.ServicioTransporte;
import java.util.List;

public class ServicioTransporteRepository {
    private IDataAccess<ServicioTransporte> dataAccess;
    
    public ServicioTransporteRepository() {
        this.dataAccess = new JsonRepository<>("Data/serviciosTransporte.json", ServicioTransporte.class);
    }
    
    // Constructor for dependency injection
    public ServicioTransporteRepository(IDataAccess<ServicioTransporte> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<ServicioTransporte> getAllServiciosTransporte() {
        return dataAccess.findAll();
    }
    
    public ServicioTransporte findServicioTransporteById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveServicioTransporte(ServicioTransporte servicioTransporte) {
        dataAccess.save(servicioTransporte);
    }
    
    public void deleteServicioTransporte(Integer id) {
        dataAccess.delete(id);
    }
}

