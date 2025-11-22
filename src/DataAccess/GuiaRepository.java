/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Guia;
import java.util.List;

public class GuiaRepository {
    private IDataAccess<Guia> dataAccess;
    
    public GuiaRepository() {
        this.dataAccess = new JsonRepository<>("Data/guias.json", Guia.class);
    }
    
    // Constructor for dependency injection
    public GuiaRepository(IDataAccess<Guia> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Guia> getAllGuias() {
        return dataAccess.findAll();
    }
    
    public Guia findGuiaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveGuia(Guia guia) {
        dataAccess.save(guia);
    }
    
    public void deleteGuia(Integer id) {
        dataAccess.delete(id);
    }
}

