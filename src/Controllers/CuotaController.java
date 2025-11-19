package Controllers;

import Models.Cuota;
import DataAccess.CuotaRepository;
import DataAccess.ViajeRepository;
import java.util.List;

public class CuotaController {
    private CuotaRepository cuotaRepository;
    private ViajeRepository viajeRepository;
    
    public CuotaController() {
        this.cuotaRepository = new CuotaRepository();
        this.viajeRepository = new ViajeRepository();
    }
    
    // Constructor for dependency injection
    public CuotaController(CuotaRepository cuotaRepository, ViajeRepository viajeRepository) {
        this.cuotaRepository = cuotaRepository;
        this.viajeRepository = viajeRepository;
    }
    
    public List<Cuota> getAllCuotas() {
        return cuotaRepository.getAllCuotas();
    }
    
    public Cuota getCuotaById(Integer id) {
        return cuotaRepository.findCuotaById(id);
    }
    
    public boolean addCuota(Double monto, Integer viajeId) {
        if (monto == null || monto < 0) {
            return false;
        }
        
        if (viajeId != null && viajeRepository.findViajeById(viajeId) == null) {
            return false;
        }
        
        Cuota cuota = new Cuota();
        cuota.setMonto(monto);
        if (viajeId != null) {
            cuota.setViajeId(viajeId);
        }
        
        cuotaRepository.saveCuota(cuota);
        return true;
    }
    
    public boolean updateCuota(Integer id, Double monto, Integer viajeId) {
        Cuota cuota = cuotaRepository.findCuotaById(id);
        if (cuota == null) {
            return false;
        }
        
        if (monto != null && monto >= 0) {
            cuota.setMonto(monto);
        }
        
        if (viajeId != null) {
            if (viajeRepository.findViajeById(viajeId) == null) {
                return false;
            }
            cuota.setViajeId(viajeId);
        }
        
        cuotaRepository.saveCuota(cuota);
        return true;
    }
    
    public boolean deleteCuota(Integer id) {
        cuotaRepository.deleteCuota(id);
        return true;
    }
}

