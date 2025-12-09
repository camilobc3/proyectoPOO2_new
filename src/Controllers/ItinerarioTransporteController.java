package Controllers;

import Models.*;
import DataAccess.*;
import Controllers.*;
import java.util.ArrayList;
import java.util.List;
import Utils.*;

public class ItinerarioTransporteController {
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    private ViajeRepository viajeRepository;
    private TrayectoRepository trayectoRepository;
    private  TrayectoController trayectoController;
    private  EstanciaProgramadaController estanciaProgramadaController;
    
    //Constructor por defecto
    public ItinerarioTransporteController() {
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        this.viajeRepository = new ViajeRepository();
        this.trayectoRepository = new TrayectoRepository();
//        this.trayectoController = new TrayectoController(); // ok, pero NO debe crear este controller otro ItinerarioController
//        this.estanciaProgramadaController = new EstanciaProgramadaController();
    }
    
    public ItinerarioTransporteController(ItinerarioTransporteRepository itinerarioTransporteRepository, 
                                          ViajeRepository viajeRepository,
                                          TrayectoRepository trayectoRepository) {
        this.itinerarioTransporteRepository = itinerarioTransporteRepository;
        this.viajeRepository = viajeRepository;
        this.trayectoRepository = trayectoRepository;
        this.trayectoController = new TrayectoController();
        this.estanciaProgramadaController = new EstanciaProgramadaController();
    }
    
    public List<ItinerarioTransporte> getAllItinerariosTransporte() {
        return itinerarioTransporteRepository.getAllItinerariosTransporte();
    }
    
    public ItinerarioTransporte getItinerarioTransporteById(Integer id) {
        return itinerarioTransporteRepository.findItinerarioTransporteById(id);
    }
    
    public boolean addItinerarioTransporte(Integer orden, Integer viajeId, Integer trayectoId) {
        if (orden == null || orden < 0) {
            return false;
        }
        
        if (viajeId != null && viajeRepository.findViajeById(viajeId) == null) {
            return false;
        }
        
        if (trayectoId != null && trayectoRepository.findTrayectoById(trayectoId) == null) {
            return false;
        }
        
        ItinerarioTransporte itinerarioTransporte = new ItinerarioTransporte();
        itinerarioTransporte.setOrden(orden);
        if (viajeId != null) {
            itinerarioTransporte.setViajeId(viajeId);
        }
        if (trayectoId != null) {
            itinerarioTransporte.setTrayectoId(trayectoId);
        }
        
        itinerarioTransporteRepository.saveItinerarioTransporte(itinerarioTransporte);
        return true;
    }
    
    public boolean updateItinerarioTransporte(Integer id, Integer orden, Integer viajeId, Integer trayectoId) {
        ItinerarioTransporte itinerarioTransporte = itinerarioTransporteRepository.findItinerarioTransporteById(id);
        if (itinerarioTransporte == null) {
            return false;
        }
        
        if (orden != null && orden >= 0) {
            itinerarioTransporte.setOrden(orden);
        }
        
        if (viajeId != null) {
            if (viajeRepository.findViajeById(viajeId) == null) {
                return false;
            }
            itinerarioTransporte.setViajeId(viajeId);
        }
        
        if (trayectoId != null) {
            if (trayectoRepository.findTrayectoById(trayectoId) == null) {
                return false;
            }
            itinerarioTransporte.setTrayectoId(trayectoId);
        }
        
        itinerarioTransporteRepository.saveItinerarioTransporte(itinerarioTransporte);
        return true;
    }
    
    public boolean deleteItinerarioTransporte(Integer id) {
        itinerarioTransporteRepository.deleteItinerarioTransporte(id);
        return true;
    }
    
    public Trayecto getMiTrayecto(Integer itinerarioId){
        ItinerarioTransporte itinerario = itinerarioTransporteRepository.findItinerarioTransporteById(itinerarioId);
        if (itinerario == null) return null;
        return trayectoRepository.findTrayectoById(itinerario.getTrayectoId());
    }

    public List<ServicioTransporte> getServiciosTransporteByItinerarioTransporte(Integer itinerarioId){
        Trayecto miTrayecto = getMiTrayecto(itinerarioId);
        if (miTrayecto == null) return new ArrayList<>(); // Lista vacía segura
        return new TrayectoController().getServiciosTransportePorIdTrayecto(miTrayecto.getId());
    }

    public boolean isItinerarioConAlgunTrayectoTerrestre(Integer itinerarioId){
        Trayecto miTrayecto = getMiTrayecto(itinerarioId);
        if (miTrayecto == null) return false;
        return new TrayectoController().isTrayectoConAlgunServicioTerrestre(miTrayecto.getId());
    }

    public List<Integer> getAerolineasByItinerarioId(Integer itinerarioId){
        TrayectoController trayectoController = new TrayectoController();
        Trayecto miTrayecto = getMiTrayecto(itinerarioId);
        // Validación importante
        if (miTrayecto == null) return new ArrayList<>();
        return trayectoController.getAerolineasIdByTrayectoId(miTrayecto.getId());
    }
    
    public Integer getMunicipioIdByItinerarioId(Integer itinerarioId){
        Trayecto miTrayecto = getMiTrayecto(itinerarioId);
        Integer municipioId = miTrayecto.getMunicipioId();
        return municipioId;
    }
    
    public double getCostosTrayectosByItinerarioId(Integer itinerarioId){
        TrayectoController trayectoController = new TrayectoController();
        Trayecto miTrayecto = getMiTrayecto(itinerarioId);
        if(miTrayecto==null) return 0.0;
        return trayectoController.getCostoTrayectoByTrayectoId(miTrayecto.getId());
    }
    
    public List<EstanciaProgramada> getEstanciasByItinerarioId(Integer itinerarioId){
        EstanciaProgramadaController estanciaProgramadaController = new EstanciaProgramadaController();
        List<EstanciaProgramada> estancias = estanciaProgramadaController.getAllEstanciasProgramadas();
        if(estancias==null)return new ArrayList<>();
        
        List<EstanciaProgramada> result = filtrarListaPorId.filtrar(estancias,a -> a.getItinerarioTransporteId().equals(itinerarioId));
        if(result==null)return new ArrayList<>();
        
        return result;
    }
    
    public List<Habitacion> getHabitacionesByItinerarioId(Integer itinerarioId){
        EstanciaProgramadaController estanciaProgramadaController = new EstanciaProgramadaController();
        List<EstanciaProgramada> misEstancias = getEstanciasByItinerarioId(itinerarioId);
        if(misEstancias==null)return new ArrayList<>();
        
        List<Habitacion> result = new ArrayList<>();
        
        for(EstanciaProgramada actual : misEstancias){
            Habitacion temp = estanciaProgramadaController.getHabitacionByEstanciaId(actual.getId());
            if(!result.contains(temp)){
                result.add(temp);
            }
        }
        return result;
    }
    
    public boolean isItinerarioConAlgunTrayectoAereo(Integer itinerarioId){
        TrayectoController trayectoController = new TrayectoController();
        boolean respuesta = false;

        if (trayectoController.isTrayectoConAlgunServicioAereo(itinerarioId)){
            respuesta = true;
        }

        return respuesta;
    }

    
    //Método A
    public boolean esItinerarioConAlgunTrayectoTerrestre(Integer itinerarioId){
        TrayectoController trayectoController = new TrayectoController();
        boolean respuesta = false;
        if(trayectoController.esTrayectoConAlgunServicioTerrestre(itinerarioId)){
            respuesta = true;
        }
        return respuesta;
    }
    
    public List<ItinerarioTransporte> getItinerariosTransporteByViajeId(Integer viajeId){
        List<ItinerarioTransporte> itinerarios = getAllItinerariosTransporte();
        List<ItinerarioTransporte> respuesta = filtrarListaPorId.filtrar(itinerarios, a -> a.getViajeId().equals(viajeId));
        return respuesta;
    }
    
    //Metodo E
    public boolean ItinerarioConMasDeTresActividades(Integer itinerarioId){
        ViajeController viajeController = new ViajeController();
        
        boolean respuesta = false;
        ItinerarioTransporte itinerario = getItinerarioTransporteById(itinerarioId);
        
        if(viajeController.viajeConAlmenosTresActividades(itinerario.getViajeId())){
            respuesta = true;
        }
        
        return respuesta;
    }
    
    //Método C
    public boolean ItinerarioConUsoDeVehiculoHotelMenosHabitaciones(Integer itinerarioId){
        EstanciaProgramadaController estanciaProgramadaController = new EstanciaProgramadaController();
        boolean respuesta = false;
        List<EstanciaProgramada> estancias = estanciaProgramadaController.getEstanciasByItinerarioTransporteId(itinerarioId);
        for(EstanciaProgramada a : estancias){
            if(estanciaProgramadaController.estanciaHabitacionHotelMenosHabitacionesUsoDeCarro(a.getId())){
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }
}

