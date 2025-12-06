package Controllers;

import DataAccess.ComponentePlanRepository;
import Models.*;
import DataAccess.*;
import Controllers.*;
import java.util.ArrayList;
import java.util.List;
import Utils.*;

public class PlanController {
    private PlanRepository planRepository;
    private ComponentePlanRepository componentePlanRepository;
    private PaqueteContratadoRepository paqueteContratadoRepository;
    private  ComponentePlanController componentePlanController;
    private  PaqueteContratadoController paqueteContratadoController;
    
    //Constructor por defecto
    public PlanController() {
        this.planRepository = new PlanRepository();
        this.componentePlanRepository = new ComponentePlanRepository();
        this.paqueteContratadoRepository = new PaqueteContratadoRepository();
        //this.componentePlanController = new ComponentePlanController();
        //this.paqueteContratadoController = new PaqueteContratadoController();
    }
    
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
        this.componentePlanRepository = new ComponentePlanRepository();
        this.paqueteContratadoRepository = new PaqueteContratadoRepository();
        this.componentePlanController = new ComponentePlanController();
        this.paqueteContratadoController = new PaqueteContratadoController();
    }
    
    public List<Plan> getAllPlanes() {
        return planRepository.getAllPlanes();
    }
    
    public Plan getPlanById(Integer id) {
        if (id == null) return null;
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
        if (id == null) return false;
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
        if (id == null) return false;
        planRepository.deletePlan(id);
        return true;
    }
    
    public List<ComponentePlan> getComponentesPlanDelPlan(Integer planId){
        
        ComponentePlanController componentePlanController = new ComponentePlanController();
        List<ComponentePlan> componentes = componentePlanController.getAllComponentesPlan();
        if(componentes==null)return new ArrayList<>();
        
        List<ComponentePlan> result = filtrarListaPorId.filtrar(componentes,a -> a.getPlanId().equals(planId));
        if(result==null)return new ArrayList<>();
        
        return result;
    }
    
    public int getNumeroActividadesTuristicasDeUnPlan(Integer planId){
        if (planId == null) return 0;
        return getComponentesPlanDelPlan(planId).size();
    }
    
    public int maxNumActividadesEnUnPlanConAlMenosUnTrayectoTerrestre(){
        List<Plan> planes = getAllPlanes();
        int max = 0;

        for(Plan actual: planes){
            if(actual != null &&
               getNumeroActividadesTuristicasDeUnPlan(actual.getId()) > max &&
               isPlanConAlgunTrayectoTerrestre(actual.getId())) {

                max = getNumeroActividadesTuristicasDeUnPlan(actual.getId());
            }
        }
        return max;
    }
    
    public List<PaqueteContratado> getPaquetesContratadosPorPlanId(Integer planId){
        List<PaqueteContratado> paquetes = paqueteContratadoController.getAllPaquetesContratados();
        if(paquetes==null)return new ArrayList<>();
        
        List<PaqueteContratado> result = filtrarListaPorId.filtrar(paquetes,a -> a.getPlanId().equals(planId));
        if(result==null)return new ArrayList<>();
        
        return result;
    }
    
    public boolean isPlanConAlgunTrayectoTerrestre(Integer planId){
        if (planId == null) return false;
        
        List<PaqueteContratado> misPaquetes = getPaquetesContratadosPorPlanId(planId);
        
        for(PaqueteContratado actual : misPaquetes){
            if(paqueteContratadoController.isPaqueteConAlgunTerrestre(actual.getId())){
                return true;
            }
        }
        return false;
    }
    
    public List<ActividadTuristica> getActividadesTuristicasByPlanId(Integer planId){
        if (planId == null) return new ArrayList<>();

        List<ComponentePlan> misComponentes = getComponentesPlanDelPlan(planId);
        if(misComponentes == null) return new ArrayList<>();
        
        List<ActividadTuristica> respuesta = new ArrayList<>();
        for(ComponentePlan actual : misComponentes){
            respuesta.add(componentePlanController.getActividadTuristicaByComponentePlanId(actual.getId()));
        }
        return respuesta;
    }
    
    public boolean isNameInSubListActividades(String nombre, List<ActividadTuristica> lista){
        if(lista == null || nombre == null) return false;

        String nombreBuscadoNormalizado = normalizarParaComparacion(nombre);

        for(ActividadTuristica actual : lista){
            String nombreActualNormalizado = normalizarParaComparacion(actual.getNombre());

            if(nombreActualNormalizado.equals(nombreBuscadoNormalizado)){
                return true;
            }
        }
        return false;
    }

    private String normalizarParaComparacion(String texto) {
        if (texto == null) return "";
        return texto.toLowerCase().trim()
                    .replace("ó", "o")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ú", "u")
                    .replace("ñ", "n");
    }
    
    public List<Cliente> getClientesByPlanId(Integer planId){
        if (planId == null) return new ArrayList<>();

        List<PaqueteContratado> misPaquetes = getPaquetesContratadosPorPlanId(planId);
        if(misPaquetes == null) return new ArrayList<>();
        
        List<Cliente> result = new ArrayList<>();
        
        for(PaqueteContratado actual : misPaquetes){
            List<Cliente> listTemp = paqueteContratadoController.getClientesByPaqueteId(actual.getId());
            for(Cliente clienteActual : listTemp){
                if(!result.contains(clienteActual)){
                    result.add(clienteActual);
                }
            }
        }
        return result;
    }
    
    public boolean isPlanContratadoPorAlgunClienteConMasDeUnViaje(Integer planId){
        if (planId == null) return false;

        List<Cliente> misClientes = getClientesByPlanId(planId);
        if(misClientes == null) return false;
        
        ClienteController clienteController = new ClienteController();
        
        for(Cliente actual : misClientes){
            if(clienteController.isClienteConMasDeUnViaje(actual.getId())){
                return true;
            }
        }
        return false;
    }
    
    public List<Plan> getListaPlanesConActividadPorNombreYContratadosPorClienteConMasDeUnViaje(String nombre){
        List<Plan> planes = getAllPlanes();
        if(planes == null) return new ArrayList<>();

        List<Plan> respuesta = new ArrayList<>(); 

        for(Plan actual : planes){
            if(isPlanContratadoPorAlgunClienteConMasDeUnViaje(actual.getId())){
                List<ActividadTuristica> listaActividadesActual = getActividadesTuristicasByPlanId(actual.getId());
                if(isNameInSubListActividades(nombre, listaActividadesActual)){
                    respuesta.add(actual);
                }
            }
        }
        return respuesta;
    }
    
    //Método E Camilo
    public boolean planConMasDeTresActividades(Integer planId){
        boolean respuesta = false;
        if(getComponentesPlanDelPlan(planId).size() >= 3){
            respuesta = true;
        }
        return respuesta;
    }
    
    public List<Plan> getPlanesByPaqueteContratadoId(Integer paqueteId){
        List<Plan> planes = getAllPlanes();
        List<Plan> respuesta = filtrarListaPorId.filtrar(planes, a -> a.getId().equals(paqueteId));
        return respuesta;
    }
    
    public double promedioActividadesComponentesPorPlanDePaqueteContratado(Integer paqueteId){
        double respuesta = 0.0;
        List<Plan> planes = getPlanesByPaqueteContratadoId(paqueteId);
        for(Plan a : planes){
            respuesta++;
        }
        respuesta /= planes.size();
        return respuesta;
    }
}
