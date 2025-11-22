/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Cuota;
import java.util.List;

public class CuotaRepository {
    private IDataAccess<Cuota> dataAccess;
    
    public CuotaRepository() {
        this.dataAccess = new JsonRepository<>("Data/cuotas.json", Cuota.class);
    }
    
    // Constructor for dependency injection
    public CuotaRepository(IDataAccess<Cuota> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Cuota> getAllCuotas() {
        return dataAccess.findAll();
    }
    
    public Cuota findCuotaById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveCuota(Cuota cuota) {
        dataAccess.save(cuota);
    }
    
    public void deleteCuota(Integer id) {
        dataAccess.delete(id);
    }
}

