package Controllers;

import DataAccess.ClienteRepository;
import DataAccess.EstanciaProgramadaRepository;
import DataAccess.HabitacionRepository;
import Models.*;
import Utils.filtrarListaPorId;
import java.util.List;

public class EstanciaProgramadaController {
    private EstanciaProgramadaRepository estanciaProgramadaRepository;
    private HabitacionRepository habitacionRepository;
    private ClienteRepository clienteRepository;
    
    //Constructor por defecto
    public EstanciaProgramadaController() {
        this.estanciaProgramadaRepository = new EstanciaProgramadaRepository();
        this.habitacionRepository = new HabitacionRepository();
        this.clienteRepository = new ClienteRepository();
    }
    
    // Constructor for dependency injection
    public EstanciaProgramadaController(EstanciaProgramadaRepository estanciaProgramadaRepository, 
                                       HabitacionRepository habitacionRepository,
                                       ClienteRepository clienteRepository) {
        this.estanciaProgramadaRepository = estanciaProgramadaRepository;
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
    }
    
    public List<EstanciaProgramada> getAllEstanciasProgramadas() {
        return estanciaProgramadaRepository.getAllEstanciasProgramadas();
    }
    
    public EstanciaProgramada getEstanciaProgramadaById(Integer id) {
        return estanciaProgramadaRepository.findEstanciaProgramadaById(id);
    }
    
    public boolean addEstanciaProgramada(Integer habitacionId, Integer usuarioId) {
        if (habitacionId == null || usuarioId == null) {
            return false;
        }
        
        if (habitacionRepository.findHabitacionById(habitacionId) == null) {
            return false;
        }
        
        if (clienteRepository.findClienteById(usuarioId) == null) {
            return false;
        }
        
        EstanciaProgramada estanciaProgramada = new EstanciaProgramada();
        estanciaProgramada.setHabitacionId(habitacionId);
        estanciaProgramada.setItinerarioTransporteId(usuarioId);
        
        estanciaProgramadaRepository.saveEstanciaProgramada(estanciaProgramada);
        return true;
    }
    
    public boolean updateEstanciaProgramada(Integer id, Integer habitacionId, Integer usuarioId) {
        EstanciaProgramada estanciaProgramada = estanciaProgramadaRepository.findEstanciaProgramadaById(id);
        if (estanciaProgramada == null) {
            return false;
        }
        
        if (habitacionId != null) {
            if (habitacionRepository.findHabitacionById(habitacionId) == null) {
                return false;
            }
            estanciaProgramada.setHabitacionId(habitacionId);
        }
        
        if (usuarioId != null) {
            if (clienteRepository.findClienteById(usuarioId) == null) {
                return false;
            }
            estanciaProgramada.setItinerarioTransporteId(usuarioId);
        }
        
        estanciaProgramadaRepository.saveEstanciaProgramada(estanciaProgramada);
        return true;
    }
    
    public boolean deleteEstanciaProgramada(Integer id) {
        estanciaProgramadaRepository.deleteEstanciaProgramada(id);
        return true;
    }
    
    public Habitacion getHabitacionByEstanciaId(Integer estanciaId){
        EstanciaProgramada estancia = getEstanciaProgramadaById(estanciaId);
        if(estancia==null) return null;
        
        Habitacion miHabitacion = habitacionRepository.findHabitacionById(estancia.getHabitacionId());
        if(miHabitacion==null) return null;
        
        return miHabitacion;
    }
    
    //Método E
    public boolean estanciaConAlmenosTresActividades(Integer Estanciaid){
        ItinerarioTransporteController itinerarioTransporteController = new ItinerarioTransporteController();
        
        boolean respuesta = false;
        EstanciaProgramada estancia = getEstanciaProgramadaById(Estanciaid);
        
        if(itinerarioTransporteController.ItinerarioConMasDeTresActividades(estancia.getItinerarioTransporteId())){
            respuesta = true;
        }
        
        return respuesta;
    }
    
    public List<EstanciaProgramada> getEstanciasByHabitacionId(Integer habitacionId){
        List<EstanciaProgramada> estancias = getAllEstanciasProgramadas();
        List<EstanciaProgramada> respuesta = filtrarListaPorId.filtrar(estancias, a -> a.getHabitacionId().equals(habitacionId));
        return respuesta;
    }

    public List<EstanciaProgramada> getEstanciasByItinerarioTransporteId(Integer itinerarioTransporteId){
        List<EstanciaProgramada> estancias = getAllEstanciasProgramadas();
        List<EstanciaProgramada> respuesta = filtrarListaPorId.filtrar(estancias, a -> a.getItinerarioTransporteId().equals(itinerarioTransporteId));
        return respuesta;
    }
    
    //Método C
    public boolean estanciaHabitacionHotelMenosHabitacionesUsoDeCarro(Integer estanciaId){
        HabitacionController habitacionController = new HabitacionController();
        boolean respuesta = false;
        EstanciaProgramada estancia = getEstanciaProgramadaById(estanciaId);
        if(habitacionController.habitacionHotelConAlmenosUnServicioTerrestre(estancia.getHabitacionId())){
            respuesta = true;
        }
        return respuesta;
    }
}

