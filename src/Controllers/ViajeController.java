package Controllers;

import Models.*;
import DataAccess.*;
import Controllers.*;
import java.util.ArrayList;
import java.util.List;
import Utils.*;

public class ViajeController {
    private ViajeRepository viajeRepository;
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    private final ItinerarioTransporteController itinerarioTransporteController;
    private ParticipacionController participacionController; // ❗ Ya NO es final

    public ViajeController() {
        this.viajeRepository = new ViajeRepository();
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        this.itinerarioTransporteController = new ItinerarioTransporteController();
        this.participacionController = new ParticipacionController(); // ← ¡solución!
    }


    // Constructor for dependency injection
    public ViajeController(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        this.itinerarioTransporteController = new ItinerarioTransporteController();
        this.participacionController = new ParticipacionController(); // ← ¡solución!
    }

    
    public List<Viaje> getAllViajes() {
        return viajeRepository.getAllViajes();
    }
    
    public Viaje getViajeById(Integer id) {
        return viajeRepository.findViajeById(id);
    }
    
    public boolean addViaje(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        Viaje viaje = new Viaje();
        viaje.setNombre(nombre.trim());
        
        viajeRepository.saveViaje(viaje);
        return true;
    }
    
    public boolean updateViaje(Integer id, String nombre) {
        Viaje viaje = viajeRepository.findViajeById(id);
        if (viaje == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            viaje.setNombre(nombre.trim());
        }
        
        viajeRepository.saveViaje(viaje);
        return true;
    }
    
    public boolean deleteViaje(Integer id) {
        viajeRepository.deleteViaje(id);
        return true;
    }
    
    public List<ItinerarioTransporte> getItinerariosDeViaje(Integer viajeId){
        return itinerarioTransporteRepository.getParticipacionesByViajeId(viajeId);
    }
    
    public List<Integer> getMunicipiosIdByViajeId(Integer viajeId) {
        List<ItinerarioTransporte> itinerarios = getItinerariosDeViaje(viajeId);
        List<Integer> respuesta = new ArrayList<>();
        ItinerarioTransporteController itController = new ItinerarioTransporteController();

        for (ItinerarioTransporte it : itinerarios) {
            Integer municipioId = itController.getMunicipioIdByItinerarioId(it.getId());
            if (!respuesta.contains(municipioId)) {
                respuesta.add(municipioId);
            }
        }
        return respuesta;
    }

    
    public int getNumeroDeItinerariosPorViaje(Integer viajeId){
        int resultado = 0;
        List<ItinerarioTransporte> misItinerarios = this.getItinerariosDeViaje(viajeId);
        resultado = misItinerarios.size();
        return resultado;
    }
    
    public boolean isViajeConTrayectoTerrestre(Integer viajeId){
        List<ItinerarioTransporte> misItinerarios = getItinerariosDeViaje(viajeId);
        for(ItinerarioTransporte actual : misItinerarios){
            if(itinerarioTransporteController.isItinerarioConAlgunTrayectoTerrestre(actual.getId())){
                return true;
            }
        }
        return false;
    }
    
    public List<Integer> getAerolineasByViajeId(Integer viajeId){
        List<ItinerarioTransporte> misItinerarios = getItinerariosDeViaje(viajeId);
        if (misItinerarios == null) return new ArrayList<>();

        List<Integer> respuesta = new ArrayList<>();

        for (ItinerarioTransporte actual : misItinerarios) {
            List<Integer> aerolineas = itinerarioTransporteController.getAerolineasByItinerarioId(actual.getId());
            respuesta.addAll(aerolineas);
        }

        return respuesta;
    }
    
    public double getCostosServiciosByViajeId(Integer viajeId){
        List<ItinerarioTransporte> misItinerarios = getItinerariosDeViaje(viajeId);
        if(misItinerarios==null) return 0.0;
        double respuesta = 0.0;
        for(ItinerarioTransporte actual : misItinerarios){
            respuesta+=itinerarioTransporteController.getCostosTrayectosByItinerarioId(actual.getId());
        }
        return respuesta;
    }
    
    public List<Participacion> getParticipacionesByViajeId(Integer viajeId){
        List<Participacion> participaciones = participacionController.getAllParticipaciones();
        List<Participacion> result = filtrarListaPorId.filtrar(participaciones, a -> a.getViajeId().equals(viajeId));
        return result;
    }
    
    public List<Cliente> getClientesByViajeId(Integer viajeId) {
        List<Participacion> participaciones = getParticipacionesByViajeId(viajeId);
        List<Cliente> resultado = new ArrayList<>();
        List<Integer> idsAgregados = new ArrayList<>();

        for (Participacion p : participaciones) {
            Cliente cliente = participacionController.getClienteByParticipacionId(p.getId());

            if (cliente != null && !idsAgregados.contains(cliente.getId())) {
                resultado.add(cliente);
                idsAgregados.add(cliente.getId());
            }
        }

        return resultado;
    }

    public void setParticipacionController(ParticipacionController pc) {
        this.participacionController = pc;
    }

}

