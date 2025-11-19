/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.ItinerarioTransporte;
import java.util.List;

public class ItinerarioTransporteRepository {
    private IDataAccess<ItinerarioTransporte> dataAccess;
    
    public ItinerarioTransporteRepository() {
        this.dataAccess = new JsonRepository<>("itinerariosTransporte.json", ItinerarioTransporte.class);
    }
    
    // Constructor for dependency injection
    public ItinerarioTransporteRepository(IDataAccess<ItinerarioTransporte> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<ItinerarioTransporte> getAllItinerariosTransporte() {
        return dataAccess.findAll();
    }
    
    public ItinerarioTransporte findItinerarioTransporteById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveItinerarioTransporte(ItinerarioTransporte itinerarioTransporte) {
        dataAccess.save(itinerarioTransporte);
    }
    
    public void deleteItinerarioTransporte(Integer id) {
        dataAccess.delete(id);
    }
}

