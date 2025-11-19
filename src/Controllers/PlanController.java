package Controllers;

import Models.Plan;
import DataAccess.PlanRepository;
import java.util.List;

public class PlanController {
    private PlanRepository planRepository;
    
    public PlanController() {
        this.planRepository = new PlanRepository();
    }
    
    // Constructor for dependency injection
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }
    
    public List<Plan> getAllPlanes() {
        return planRepository.getAllPlanes();
    }
    
    public Plan getPlanById(Integer id) {
        return planRepository.findPlanById(id);
    }
    
    public boolean addPlan(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        Plan plan = new Plan();
        plan.setNombre(nombre.trim());
        
        planRepository.savePlan(plan);
        return true;
    }
    
    public boolean updatePlan(Integer id, String nombre) {
        Plan plan = planRepository.findPlanById(id);
        if (plan == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            plan.setNombre(nombre.trim());
        }
        
        planRepository.savePlan(plan);
        return true;
    }
    
    public boolean deletePlan(Integer id) {
        planRepository.deletePlan(id);
        return true;
    }
}

