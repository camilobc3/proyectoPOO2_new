package Controllers;

import Models.*;
import DataAccess.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeController {
    private ViajeRepository viajeRepository;
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    
    public ViajeController() {
        this.viajeRepository = new ViajeRepository();
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
    }
    
    // Constructor for dependency injection
    public ViajeController(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
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
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
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
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();

        for (ItinerarioTransporte actual : misItinerarios) {
            List<Integer> aerolineas = itinerarioTransporteController.getAerolineasByItinerarioId(actual.getId());
            respuesta.addAll(aerolineas);
        }

        return respuesta;
    }

}

