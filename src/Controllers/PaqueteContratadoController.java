package Controllers;

import Models.PaqueteContratado;
import DataAccess.ClienteRepository;
import DataAccess.PaqueteContratadoRepository;
import DataAccess.PlanRepository;
import java.util.List;

public class PaqueteContratadoController {
    private PaqueteContratadoRepository paqueteContratadoRepository;
    private ClienteRepository clienteRepository;
    private PlanRepository planRepository;
    
    public PaqueteContratadoController() {
        this.paqueteContratadoRepository = new PaqueteContratadoRepository();
        this.clienteRepository = new ClienteRepository();
        this.planRepository = new PlanRepository();
    }
    
    // Constructor for dependency injection
    public PaqueteContratadoController(PaqueteContratadoRepository paqueteContratadoRepository, 
                                      ClienteRepository clienteRepository,
                                      PlanRepository planRepository) {
        this.paqueteContratadoRepository = paqueteContratadoRepository;
        this.clienteRepository = clienteRepository;
        this.planRepository = planRepository;
    }
    
    public List<PaqueteContratado> getAllPaquetesContratados() {
        return paqueteContratadoRepository.getAllPaquetesContratados();
    }
    
    public PaqueteContratado getPaqueteContratadoById(Integer id) {
        return paqueteContratadoRepository.findPaqueteContratadoById(id);
    }
    
    public boolean addPaqueteContratado(String fechaInicio, String fechaFin, Integer usuarioId, Integer planId) {
        if (fechaInicio == null || fechaInicio.trim().isEmpty() ||
            fechaFin == null || fechaFin.trim().isEmpty()) {
            return false;
        }
        
        if (usuarioId != null && clienteRepository.findClienteById(usuarioId) == null) {
            return false;
        }
        
        if (planId != null && planRepository.findPlanById(planId) == null) {
            return false;
        }
        
        PaqueteContratado paqueteContratado = new PaqueteContratado();
        paqueteContratado.setFechaInicio(fechaInicio.trim());
        paqueteContratado.setFechaFin(fechaFin.trim());
        if (usuarioId != null) {
            paqueteContratado.setUsuarioId(usuarioId);
        }
        if (planId != null) {
            paqueteContratado.setPlanId(planId);
        }
        
        paqueteContratadoRepository.savePaqueteContratado(paqueteContratado);
        return true;
    }
    
    public boolean updatePaqueteContratado(Integer id, String fechaInicio, String fechaFin, Integer usuarioId, Integer planId) {
        PaqueteContratado paqueteContratado = paqueteContratadoRepository.findPaqueteContratadoById(id);
        if (paqueteContratado == null) {
            return false;
        }
        
        if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
            paqueteContratado.setFechaInicio(fechaInicio.trim());
        }
        
        if (fechaFin != null && !fechaFin.trim().isEmpty()) {
            paqueteContratado.setFechaFin(fechaFin.trim());
        }
        
        if (usuarioId != null) {
            if (clienteRepository.findClienteById(usuarioId) == null) {
                return false;
            }
            paqueteContratado.setUsuarioId(usuarioId);
        }
        
        if (planId != null) {
            if (planRepository.findPlanById(planId) == null) {
                return false;
            }
            paqueteContratado.setPlanId(planId);
        }
        
        paqueteContratadoRepository.savePaqueteContratado(paqueteContratado);
        return true;
    }
    
    public boolean deletePaqueteContratado(Integer id) {
        paqueteContratadoRepository.deletePaqueteContratado(id);
        return true;
    }
}

