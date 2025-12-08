package Controllers;

import Models.*;
import DataAccess.*;
import Controllers.*;
import java.util.ArrayList;
import java.util.List;
import Utils.*;
import java.util.AbstractList;

public class ViajeController {
    private ViajeRepository viajeRepository;
    private ItinerarioTransporteRepository itinerarioTransporteRepository;
    private  ItinerarioTransporteController itinerarioTransporteController;
    private ParticipacionController participacionController; // ❗ Ya NO es final
    private HotelRepository hotelRepository;

    //Constructor por defecto
    public ViajeController() {
        this.viajeRepository = new ViajeRepository();
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        //this.itinerarioTransporteController = new ItinerarioTransporteController();
        //this.participacionController = new ParticipacionController(); // crear instancia
        // inyectar la referencia hacia este ViajeController para romper la recursividad
        //this.participacionController.setViajeController(this);
        this.hotelRepository = new HotelRepository();
    }


    // Constructor for dependency injection
    public ViajeController(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
        this.itinerarioTransporteRepository = new ItinerarioTransporteRepository();
        this.itinerarioTransporteController = new ItinerarioTransporteController();
        this.participacionController = new ParticipacionController();
        this.participacionController.setViajeController(this);
        this.hotelRepository = new HotelRepository();
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
        List<ItinerarioTransporte> itinerarios = itinerarioTransporteController.getAllItinerariosTransporte();
        if(itinerarios==null)return new ArrayList<>();
        
        List<ItinerarioTransporte> result = filtrarListaPorId.filtrar(itinerarios,a -> a.getViajeId().equals(viajeId));
        if(result==null)return new ArrayList<>();
        
        return result;
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
        // también asegurar que la referencia inversa esté establecida
        if (this.participacionController != null) {
            this.participacionController.setViajeController(this);
        }
    }
    
    public List<Habitacion> getHabitacionesByViajeId(Integer viajeId){
        List<ItinerarioTransporte> misItinerarios = getItinerariosDeViaje(viajeId);
        if(misItinerarios == null) return new ArrayList<>();
        List<Habitacion> result = new ArrayList<>();
        
        for(ItinerarioTransporte actual : misItinerarios){
            List<Habitacion> temp = itinerarioTransporteController.getHabitacionesByItinerarioId(actual.getId());
            for(Habitacion habitacionActual : temp){
                if(!result.contains(habitacionActual)){
                    result.add(habitacionActual);
                }
            }
        }
        return result;
    }
    
    public List<Hotel> getHotelesPorViajeId(Integer viajeId, List<Habitacion> habitaciones){
        if(habitaciones==null)return new ArrayList<>();
        
        List<Hotel> result = new ArrayList<>();
        
        for(Habitacion actual : habitaciones){
            Hotel hotelActual = hotelRepository.findHotelById(actual.getHotelId());
            if(hotelActual != null && !result.contains(hotelActual)){
                result.add(hotelActual);
            }
        }
        return result;
    }


    public double promedioHabitacionesReservadasPorHotelConTrayectoAereoYTerrestre() {
        List<Viaje> viajes = getAllViajes();
        if (viajes == null || viajes.isEmpty()) return 0.0;

        double totalHabitaciones = 0;
        double totalHoteles = 0;

        for (Viaje viaje : viajes) {
            int idViaje = viaje.getId();

            if (isViajeConTrayectoAereo(idViaje) && isViajeConTrayectoTerrestre(idViaje)) {
                List<Habitacion> habitaciones = getHabitacionesByViajeId(idViaje);
                List<Hotel> hoteles = getHotelesPorViajeId(idViaje, habitaciones);

                totalHabitaciones += habitaciones.size();
                totalHoteles += hoteles.size();
            }
        }
        if(totalHoteles>0){
            return totalHabitaciones/totalHoteles;
        }
        return 0.0;
    }
    
    //Método E
    
    public boolean viajeConAlmenosTresActividades(Integer viajeId){
        
        PaqueteContratadoController paqueteContratadoController = new PaqueteContratadoController();
        PlanController planController = new PlanController();
        
        boolean respuesta = false;
        
        List<PaqueteContratado> paquetesViaje = paqueteContratadoController.getPaquetesContratadosByViajeId(viajeId);
        
        for(PaqueteContratado a : paquetesViaje){
            if(planController.planConMasDeTresActividades(a.getViajeId())){
                respuesta = true;
                break;
            }
        }
        
        return respuesta;
    }
    
    //Método A
    public boolean isViajeConTrayectoAereo(Integer viajeId){
        boolean respuesta = false;
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        List<ItinerarioTransporte> misItinerarios = itinerarioTransporteController.getItinerariosTransporteByViajeId(viajeId);

        for(ItinerarioTransporte actual : misItinerarios){
            if(itinerarioTransporteController.isItinerarioConAlgunTrayectoAereo(actual.getId())){
                respuesta = true;
                break;
            }
        }

        return respuesta;
    }

    public boolean esViajeConTrayectoTerrestre(Integer viajeId){
        boolean respuesta = false;
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        List<ItinerarioTransporte> itinerarios = itinerarioTransporteController.getItinerariosTransporteByViajeId(viajeId);
        
        for(ItinerarioTransporte a : itinerarios){
            if(itinerarioTransporteController.esItinerarioConAlgunTrayectoTerrestre(a.getId())){
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }
    
    public List<Double> promedioActividadesViajesQueTienenTrayectoAereoTerrestre(Integer viajeId){
        PaqueteContratadoController paqueteContratadoController = new PaqueteContratadoController();
        PlanController planController = new PlanController();
        List<Double> respuesta = new ArrayList<>();
        if(esViajeConTrayectoTerrestre(viajeId)&&isViajeConTrayectoAereo(viajeId)){
            List<PaqueteContratado> paquetesViaje = paqueteContratadoController.getPaquetesContratadosByViajeId(viajeId);
            for(PaqueteContratado a : paquetesViaje){
                respuesta.add(planController.promedioActividadesComponentesPorPlanDePaqueteContratado(a.getId()));
            }
        }
        return respuesta;
    }
    
    //Método D
    public double sumatoriaTrayectosAereos(Integer viajeId){
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        TrayectoController trayectoController = new TrayectoController();
        
        double respuesta = 0.0;
        
        if(isViajeConTrayectoAereo(viajeId)){
            List<ItinerarioTransporte> itinerarios = itinerarioTransporteController.getItinerariosTransporteByViajeId(viajeId);
            for(ItinerarioTransporte a : itinerarios){
                respuesta+= trayectoController.getTrayectoById(a.getTrayectoId()).getCosto();
            }
        }
        
        return respuesta;
    }
    
    //Método C
    public boolean viajeContieneActividad(Integer viajeId, String nombreActividad){
        PaqueteContratadoController paqueteContratadoController = new PaqueteContratadoController();
        PlanController planController = new PlanController();
        
        boolean respuesta = false;
        List<PaqueteContratado> paquetesContratados = paqueteContratadoController.getPaquetesContratadosByViajeId(viajeId);
        
        for(PaqueteContratado paquete : paquetesContratados){
            if(planController.planContieneActividad(paquete.getPlanId(), nombreActividad)){
                respuesta = true;
                break;
            }
        }
        
        return respuesta;
    }
    
    public List<Viaje> viajesQueIncluyenAlmenosUnPlanConUnaActividadEspecifica(String nombreActividad){
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        List<Viaje> viajes = getAllViajes();
        List<Viaje> respuesta = new ArrayList<>();
        
        for(Viaje a : viajes){
            if(viajeContieneActividad(a.getId(), nombreActividad) && viajeItinerarioConUsoDeVehiculoHotelMenosHabitaciones(a.getId())){
                respuesta.add(a);
            }
        }
        return respuesta;
    }
    
    public boolean viajeItinerarioConUsoDeVehiculoHotelMenosHabitaciones(Integer viajeId){
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        
        boolean respuesta = false;
        List<ItinerarioTransporte> itinerarios = itinerarioTransporteController.getItinerariosTransporteByViajeId(viajeId);
        
        for(ItinerarioTransporte itinerario : itinerarios){
            if(itinerarioTransporteController.ItinerarioConUsoDeVehiculoHotelMenosHabitaciones(itinerario.getId())){
                respuesta = true;
                break;
            }
        }
        
        return respuesta;
    }
    
    
}