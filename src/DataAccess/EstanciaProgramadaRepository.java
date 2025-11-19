/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.EstanciaProgramada;
import java.util.List;

public class EstanciaProgramadaRepository {
    private IDataAccess<EstanciaProgramada> dataAccess;
    
    public EstanciaProgramadaRepository() {
        this.dataAccess = new JsonRepository<>("estanciasProgramadas.json", EstanciaProgramada.class);
    }
    
    // Constructor for dependency injection
    public EstanciaProgramadaRepository(IDataAccess<EstanciaProgramada> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<EstanciaProgramada> getAllEstanciasProgramadas() {
        return dataAccess.findAll();
    }
    
    public EstanciaProgramada findEstanciaProgramadaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveEstanciaProgramada(EstanciaProgramada estanciaProgramada) {
        dataAccess.save(estanciaProgramada);
    }
    
    public void deleteEstanciaProgramada(Integer id) {
        dataAccess.delete(id);
    }
}

