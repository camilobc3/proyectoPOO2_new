package Controllers;

import DataAccess.ComponentePlanRepository;
import Models.*;
import DataAccess.*;
import Controllers.*;
import java.util.List;

public class PlanController {
    private PlanRepository planRepository;
    private ComponentePlanRepository componentePlanRepository;
    
    public PlanController() {
        this.planRepository = new PlanRepository();
        this.componentePlanRepository = new ComponentePlanRepository();
    }
    
    // Constructor for dependency injection
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
        this.componentePlanRepository = new ComponentePlanRepository();
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
    
    public List<ComponentePlan> getComponentesPlanDelPlan(Integer planId){
        return componentePlanRepository.getComponentesPlanByPlanId(planId);
    }
    
    public int getNumeroActividadesTuristicasDeUnPlan(Integer planId){
        return getComponentesPlanDelPlan(planId).size();
    }
    
    public int maxNumActividadesEnUnPlanConAlMenosUnTrayectoTerrestre(){
        List<Plan> planes = getAllPlanes();
        int max=0;
        PlanController planController = new PlanController();
        for(Plan actual: planes){
            if(planController.getNumeroActividadesTuristicasDeUnPlan(actual.getId())>max){
                max = planController.getNumeroActividadesTuristicasDeUnPlan(actual.getId());
            }
        }
        return max;
    }
}

