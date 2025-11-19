/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Aeronave;
import java.util.List;

public class AeronaveRepository {
    private IDataAccess<Aeronave> dataAccess;
    
    public AeronaveRepository() {
        this.dataAccess = new JsonRepository<>("aeronaves.json", Aeronave.class);
    }
    
    // Constructor for dependency injection
    public AeronaveRepository(IDataAccess<Aeronave> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Aeronave> getAllAeronaves() {
        return dataAccess.findAll();
    }
    
    public Aeronave findAeronaveById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveAeronave(Aeronave aeronave) {
        dataAccess.save(aeronave);
    }
    
    public void deleteAeronave(Integer id) {
        dataAccess.delete(id);
    }
}

