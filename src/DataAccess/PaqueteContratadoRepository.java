/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.*;
import Utils.*;
import java.util.List;

public class PaqueteContratadoRepository {
    private IDataAccess<PaqueteContratado> dataAccess;
    
    public PaqueteContratadoRepository() {
        this.dataAccess = new JsonRepository<>("Data/paquetesContratados.json", PaqueteContratado.class);
    }
    
    // Constructor for dependency injection
    public PaqueteContratadoRepository(IDataAccess<PaqueteContratado> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<PaqueteContratado> getAllPaquetesContratados() {
        return dataAccess.findAll();
    }
    
    public PaqueteContratado findPaqueteContratadoById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void savePaqueteContratado(PaqueteContratado paqueteContratado) {
        dataAccess.save(paqueteContratado);
    }
    
    public void deletePaqueteContratado(Integer id) {
        dataAccess.delete(id);
    }
        
    public List<PaqueteContratado> getPaquetesContratadosByPlanId(Integer planId){
        List<PaqueteContratado> paquetes = getAllPaquetesContratados();
        List<PaqueteContratado> result = filtrarListaPorId.filtrar(paquetes,a -> a.getPlanId().equals(planId));
        return result;
    }
}

