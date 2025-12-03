package Controllers;

import Models.*;
import DataAccess.*;
import java.util.ArrayList;
import java.util.List;

public class TrayectoController {
    private ServicioTransporteRepository servicioTransporteRepository;
    private TrayectoRepository trayectoRepository;
    private MunicipioRepository municipioRepository;

    // NUEVO: evitar instancias infinitas
    private ServicioTransporteController servicioTransporteController;

    public TrayectoController() {
        this.trayectoRepository = new TrayectoRepository();
        this.municipioRepository = new MunicipioRepository();
        this.servicioTransporteRepository = new ServicioTransporteRepository();

        // Instancia única
        this.servicioTransporteController = new ServicioTransporteController();
    }

    public TrayectoController(TrayectoRepository trayectoRepository, MunicipioRepository municipioRepository) {
        this.trayectoRepository = trayectoRepository;
        this.municipioRepository = municipioRepository;
        this.servicioTransporteRepository = new ServicioTransporteRepository();

        // Instancia única
        this.servicioTransporteController = new ServicioTransporteController();
    }

    public List<Trayecto> getAllTrayectos() {
        return trayectoRepository.getAllTrayectos();
    }

    public Trayecto getTrayectoById(Integer id) {
        return trayectoRepository.findTrayectoById(id);
    }

    public boolean addTrayecto(Double costo, Integer municipioId) {
        if (costo == null || costo < 0) {
            return false;
        }

        if (municipioId != null && municipioRepository.findMunicipioById(municipioId) == null) {
            return false;
        }

        Trayecto trayecto = new Trayecto();
        trayecto.setCosto(costo);
        if (municipioId != null) {
            trayecto.setMunicipioId(municipioId);
        }

        trayectoRepository.saveTrayecto(trayecto);
        return true;
    }

    public boolean updateTrayecto(Integer id, Double costo, Integer municipioId) {
        Trayecto trayecto = trayectoRepository.findTrayectoById(id);
        if (trayecto == null) {
            return false;
        }

        if (costo != null && costo >= 0) {
            trayecto.setCosto(costo);
        }

        if (municipioId != null) {
            if (municipioRepository.findMunicipioById(municipioId) == null) {
                return false;
            }
            trayecto.setMunicipioId(municipioId);
        }

        trayectoRepository.saveTrayecto(trayecto);
        return true;
    }

    public boolean deleteTrayecto(Integer id) {
        trayectoRepository.deleteTrayecto(id);
        return true;
    }

    public List<ServicioTransporte> getServiciosTransportePorIdTrayecto(Integer trayectoId){
        return servicioTransporteRepository.getServicioTransporteByTrayectoId(trayectoId);
    }

    public boolean isTrayectoConAlgunServicioTerrestre(Integer trayectoId){
        List<ServicioTransporte> misServiciosTransporte = getServiciosTransportePorIdTrayecto(trayectoId);
        for(ServicioTransporte actual : misServiciosTransporte){

            // Usar instancia única
            if(servicioTransporteController.isTerrestre(actual.getId())){
                return true;
            }
        }
        return false;
    }

    public List<Integer> getAerolineasIdByTrayectoId(Integer trayectoId){
        List<ServicioTransporte> misServicios = getServiciosTransportePorIdTrayecto(trayectoId);
        List<Integer> respuesta = new ArrayList<>();

        for(ServicioTransporte actual : misServicios){

            // Usar instancia única
            Integer aerolineaId = servicioTransporteController.getAerolineaIdFromServicioT(actual.getId());

            if(aerolineaId != null){
                respuesta.add(aerolineaId);
            }
        }
        return respuesta;
    }

    public double getCostoTrayectoByTrayectoId(Integer trayectoId){
        List<ServicioTransporte> misServicios = getServiciosTransportePorIdTrayecto(trayectoId);
        if(misServicios==null) return 0.0;
        double respuesta = 0.0;
        for(ServicioTransporte actual : misServicios){
            respuesta += actual.getCosto();
        }
        return respuesta;
    }
}
