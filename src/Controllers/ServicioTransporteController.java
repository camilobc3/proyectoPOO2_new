package Controllers;

import Models.*;
import DataAccess.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import Utils.*;

public class ServicioTransporteController {
    private ServicioTransporteRepository servicioTransporteRepository;
    private TrayectoRepository trayectoRepository;
    private AeronaveRepository aeronaveRepository;
    private CarroRepository carroRepository;
    
    //Constructor por defecto
    public ServicioTransporteController() {
        this.servicioTransporteRepository = new ServicioTransporteRepository();
        this.trayectoRepository = new TrayectoRepository();
        this.aeronaveRepository = new AeronaveRepository();
        this.carroRepository = new CarroRepository();
    }
    
    // Constructor for dependency injection
    public ServicioTransporteController(ServicioTransporteRepository servicioTransporteRepository, 
                                       TrayectoRepository trayectoRepository,
                                       AeronaveRepository aeronaveRepository,
                                       CarroRepository carroRepository) {
        this.servicioTransporteRepository = servicioTransporteRepository;
        this.trayectoRepository = trayectoRepository;
        this.aeronaveRepository = aeronaveRepository;
        this.carroRepository = carroRepository;
    }
    
    public List<ServicioTransporte> getAllServiciosTransporte() {
        return servicioTransporteRepository.getAllServiciosTransporte();
    }
    
    public ServicioTransporte getServicioTransporteById(Integer id) {
        return servicioTransporteRepository.findServicioTransporteById(id);
    }
    
    public boolean addServicioTransporte(String fechaInicio, String fechaFin, Double costo, Integer trayectoId, Integer vehiculoId) {
        if (fechaInicio == null || fechaInicio.trim().isEmpty() ||
            fechaFin == null || fechaFin.trim().isEmpty() ||
            costo == null || costo < 0) {
            return false;
        }
        
        if (trayectoId != null && trayectoRepository.findTrayectoById(trayectoId) == null) {
            return false;
        }
        
        if (vehiculoId != null) {
            if (aeronaveRepository.findAeronaveById(vehiculoId) == null && 
                carroRepository.findCarroById(vehiculoId) == null) {
                return false;
            }
        }
        
        ServicioTransporte servicioTransporte = new ServicioTransporte();
        servicioTransporte.setFechaInicio(fechaInicio.trim());
        servicioTransporte.setFechaFin(fechaFin.trim());
        servicioTransporte.setCosto(costo);
        if (trayectoId != null) {
            servicioTransporte.setTrayectoId(trayectoId);
        }
        if (vehiculoId != null) {
            servicioTransporte.setVehiculoId(vehiculoId);
        }
        
        servicioTransporteRepository.saveServicioTransporte(servicioTransporte);
        return true;
    }
    
    public boolean updateServicioTransporte(Integer id, String fechaInicio, String fechaFin, Double costo, Integer trayectoId, Integer vehiculoId) {
        ServicioTransporte servicioTransporte = servicioTransporteRepository.findServicioTransporteById(id);
        if (servicioTransporte == null) {
            return false;
        }
        
        if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
            servicioTransporte.setFechaInicio(fechaInicio.trim());
        }
        
        if (fechaFin != null && !fechaFin.trim().isEmpty()) {
            servicioTransporte.setFechaFin(fechaFin.trim());
        }
        
        if (costo != null && costo >= 0) {
            servicioTransporte.setCosto(costo);
        }
        
        if (trayectoId != null) {
            if (trayectoRepository.findTrayectoById(trayectoId) == null) {
                return false;
            }
            servicioTransporte.setTrayectoId(trayectoId);
        }
        
        if (vehiculoId != null) {
            if (aeronaveRepository.findAeronaveById(vehiculoId) == null && 
                carroRepository.findCarroById(vehiculoId) == null) {
                return false;
            }
            servicioTransporte.setVehiculoId(vehiculoId);
        }
        
        servicioTransporteRepository.saveServicioTransporte(servicioTransporte);
        return true;
    }
    
    public boolean deleteServicioTransporte(Integer id) {
        servicioTransporteRepository.deleteServicioTransporte(id);
        return true;
    }
    
    //Analizamos los servicios de cada avión para obtener el menor
    public Trayecto menorCostoTrayectoAeronave(Integer idAeronave) {
        List<ServicioTransporte> servicios = servicioTransporteRepository.getAeronavesByAeronaveId(idAeronave);

        Trayecto trayectoMenorCosto = servicios.stream()
                .map(servicioTransporteActual -> trayectoRepository.findTrayectoById(servicioTransporteActual.getTrayectoId()))
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Trayecto::getCosto))
                .orElse(null);

        return trayectoMenorCosto;
    }
    
    public boolean isTerrestre(Integer servicioId){
        Integer vehiculoId = getServicioTransporteById(servicioId).getVehiculoId();
        List<Carro> carros = carroRepository.getAllCarros();
        for(Carro actual : carros){
            if(actual.getId().equals(vehiculoId)){
                return true;
            }
        }
        return false;
    }
    
    public Aeronave getMiAeronave(Integer servicioId){

        ServicioTransporte servicio = getServicioTransporteById(servicioId);
        if(servicio == null) return null;        // No existe el servicio
        if(servicio.getVehiculoId() == null) return null; // No tiene vehículo asignado

        Aeronave miAeronave = aeronaveRepository.findAeronaveById(servicio.getVehiculoId());
        return miAeronave; // Si no existe la aeronave igual retorna null ✔
    }


    public Integer getAerolineaIdFromServicioT(Integer servicioId){
        Aeronave miAeronave = getMiAeronave(servicioId);
        if(miAeronave == null) return null;     // Retorna null si no hay aeronave
        return miAeronave.getAerolineaId();
    }
    
    public boolean isAereo(Integer servicioId){
        AeronaveController aeronaveController = new AeronaveController();

        boolean respuesta = false;
        Integer vehiculoId = getServicioTransporteById(servicioId).getVehiculoId();
        List<Aeronave> aeronaves = aeronaveController.getAllAeronaves();

        for(Aeronave actual : aeronaves){
            if(actual.getId().equals(vehiculoId)){
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    
    //Método A
    public boolean esTerrestre(Integer servicioId){
        CarroController carroController = new CarroController();
        
        boolean respuesta = false;
        Integer vehiculoId = getServicioTransporteById(servicioId).getVehiculoId();
        List<Carro> carros = carroController.getAllCarros();
        for(Carro a : carros){
            if(a.getId().equals(vehiculoId)){
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }
    
    //Método B
    public List<ServicioTransporte> getServiciosTransporteByAeronaveId(Integer aeronaveId){
        List<ServicioTransporte> servicios = getAllServiciosTransporte();
        List<ServicioTransporte> respuesta = filtrarListaPorId.filtrar(servicios, a -> a.getVehiculoId().equals(aeronaveId));
        return respuesta;
    }
    
    public Trayecto trayectoMenorValorPorAeronave(Integer AeronaveId){
        TrayectoController trayectoController = new TrayectoController();
        Trayecto respuesta = null;
        Trayecto menorTrayecto = null; 
        double menor = Double.MAX_VALUE;
        List<ServicioTransporte> serviciosTransporte = getServiciosTransporteByAeronaveId(AeronaveId);
        for(ServicioTransporte a : serviciosTransporte){
            menorTrayecto = trayectoController.getTrayectoById(a.getTrayectoId());
            if(menorTrayecto.getCosto() < menor){
                menor = menorTrayecto.getCosto();
                respuesta = menorTrayecto;
            }
        }
        return respuesta;
    }
    
    

}

