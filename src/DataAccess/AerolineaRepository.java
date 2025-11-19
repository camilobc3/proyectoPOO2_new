/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Aerolinea;
import java.util.List;

public class AerolineaRepository {
    private IDataAccess<Aerolinea> dataAccess;
    
    public AerolineaRepository() {
        this.dataAccess = new JsonRepository<>("aerolineas.json", Aerolinea.class);
    }
    
    // Constructor for dependency injection
    public AerolineaRepository(IDataAccess<Aerolinea> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Aerolinea> getAllAerolineas() {
        return dataAccess.findAll();
    }
    
    public Aerolinea findAerolineaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveAerolinea(Aerolinea aerolinea) {
        dataAccess.save(aerolinea);
    }
    
    public void deleteAerolinea(Integer id) {
        dataAccess.delete(id);
    }
}

