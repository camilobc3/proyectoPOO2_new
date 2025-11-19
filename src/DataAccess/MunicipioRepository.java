/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Municipio;
import java.util.List;

public class MunicipioRepository {
    private IDataAccess<Municipio> dataAccess;
    
    public MunicipioRepository() {
        this.dataAccess = new JsonRepository<>("municipios.json", Municipio.class);
    }
    
    // Constructor for dependency injection
    public MunicipioRepository(IDataAccess<Municipio> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Municipio> getAllMunicipios() {
        return dataAccess.findAll();
    }
    
    public Municipio findMunicipioById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveMunicipio(Municipio municipio) {
        dataAccess.save(municipio);
    }
    
    public void deleteMunicipio(Integer id) {
        dataAccess.delete(id);
    }
}

