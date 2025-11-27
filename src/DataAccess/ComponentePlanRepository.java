/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.*;
import Utils.*;
import java.util.ArrayList;
import java.util.List;

public class ComponentePlanRepository {
    private IDataAccess<ComponentePlan> dataAccess;
    
    public ComponentePlanRepository() {
        this.dataAccess = new JsonRepository<>("Data/componentesPlan.json", ComponentePlan.class);
    }
    
    // Constructor for dependency injection
    public ComponentePlanRepository(IDataAccess<ComponentePlan> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<ComponentePlan> getAllComponentesPlan() {
        return dataAccess.findAll();
    }
    
    public ComponentePlan findComponentePlanById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveComponentePlan(ComponentePlan componentePlan) {
        dataAccess.save(componentePlan);
    }
    
    public void deleteComponentePlan(Integer id) {
        dataAccess.delete(id);
    }
    
    public List<ComponentePlan> getComponentesPlanByPlanId(Integer planId){
        List<ComponentePlan> componentes = getAllComponentesPlan();
        List<ComponentePlan> respuesta = filtrarListaPorId.filtrar(componentes, a -> a.getPlanId().equals(planId));
        return respuesta;
    }
}

