/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Plan;
import java.util.List;

public class PlanRepository {
    private IDataAccess<Plan> dataAccess;
    
    public PlanRepository() {
        this.dataAccess = new JsonRepository<>("Data/planes.json", Plan.class);
    }
    
    // Constructor for dependency injection
    public PlanRepository(IDataAccess<Plan> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Plan> getAllPlanes() {
        return dataAccess.findAll();
    }
    
    public Plan findPlanById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void savePlan(Plan plan) {
        dataAccess.save(plan);
    }
    
    public void deletePlan(Integer id) {
        dataAccess.delete(id);
    }
}

