package Controllers;

import Models.ComponentePlan;
import DataAccess.ComponentePlanRepository;
import DataAccess.PlanRepository;
import DataAccess.ActividadTuristicaRepository;
import java.util.List;

public class ComponentePlanController {
    private ComponentePlanRepository componentePlanRepository;
    private PlanRepository planRepository;
    private ActividadTuristicaRepository actividadTuristicaRepository;
    
    public ComponentePlanController() {
        this.componentePlanRepository = new ComponentePlanRepository();
        this.planRepository = new PlanRepository();
        this.actividadTuristicaRepository = new ActividadTuristicaRepository();
    }
    
    // Constructor for dependency injection
    public ComponentePlanController(ComponentePlanRepository componentePlanRepository, 
                                   PlanRepository planRepository,
                                   ActividadTuristicaRepository actividadTuristicaRepository) {
        this.componentePlanRepository = componentePlanRepository;
        this.planRepository = planRepository;
        this.actividadTuristicaRepository = actividadTuristicaRepository;
    }
    
    public List<ComponentePlan> getAllComponentesPlan() {
        return componentePlanRepository.getAllComponentesPlan();
    }
    
    public ComponentePlan getComponentePlanById(Integer id) {
        return componentePlanRepository.findComponentePlanById(id);
    }
    
    public boolean addComponentePlan(Integer planId, Integer actividadTuristicaId) {
        if (planId == null || actividadTuristicaId == null) {
            return false;
        }
        
        if (planRepository.findPlanById(planId) == null) {
            return false;
        }
        
        if (actividadTuristicaRepository.findActividadTuristicaById(actividadTuristicaId) == null) {
            return false;
        }
        
        ComponentePlan componentePlan = new ComponentePlan();
        componentePlan.setPlanId(planId);
        componentePlan.setActividadTuristicaId(actividadTuristicaId);
        
        componentePlanRepository.saveComponentePlan(componentePlan);
        return true;
    }
    
    public boolean updateComponentePlan(Integer id, Integer planId, Integer actividadTuristicaId) {
        ComponentePlan componentePlan = componentePlanRepository.findComponentePlanById(id);
        if (componentePlan == null) {
            return false;
        }
        
        if (planId != null) {
            if (planRepository.findPlanById(planId) == null) {
                return false;
            }
            componentePlan.setPlanId(planId);
        }
        
        if (actividadTuristicaId != null) {
            if (actividadTuristicaRepository.findActividadTuristicaById(actividadTuristicaId) == null) {
                return false;
            }
            componentePlan.setActividadTuristicaId(actividadTuristicaId);
        }
        
        componentePlanRepository.saveComponentePlan(componentePlan);
        return true;
    }
    
    public boolean deleteComponentePlan(Integer id) {
        componentePlanRepository.deleteComponentePlan(id);
        return true;
    }
}

