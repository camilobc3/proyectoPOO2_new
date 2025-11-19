package Controllers;

import Models.Habitacion;
import DataAccess.HabitacionRepository;
import DataAccess.HotelRepository;
import java.util.List;

public class HabitacionController {
    private HabitacionRepository habitacionRepository;
    private HotelRepository hotelRepository;
    
    public HabitacionController() {
        this.habitacionRepository = new HabitacionRepository();
        this.hotelRepository = new HotelRepository();
    }
    
    // Constructor for dependency injection
    public HabitacionController(HabitacionRepository habitacionRepository, HotelRepository hotelRepository) {
        this.habitacionRepository = habitacionRepository;
        this.hotelRepository = hotelRepository;
    }
    
    public List<Habitacion> getAllHabitaciones() {
        return habitacionRepository.getAllHabitaciones();
    }
    
    public Habitacion getHabitacionById(Integer id) {
        return habitacionRepository.findHabitacionById(id);
    }
    
    public boolean addHabitacion(Integer capacidad, Double costo, Integer hotelId) {
        if (capacidad == null || capacidad <= 0 || costo == null || costo < 0) {
            return false;
        }
        
        if (hotelId != null && hotelRepository.findHotelById(hotelId) == null) {
            return false;
        }
        
        Habitacion habitacion = new Habitacion();
        habitacion.setCapacidad(capacidad);
        habitacion.setCosto(costo);
        if (hotelId != null) {
            habitacion.setHotelId(hotelId);
        }
        
        habitacionRepository.saveHabitacion(habitacion);
        return true;
    }
    
    public boolean updateHabitacion(Integer id, Integer capacidad, Double costo, Integer hotelId) {
        Habitacion habitacion = habitacionRepository.findHabitacionById(id);
        if (habitacion == null) {
            return false;
        }
        
        if (capacidad != null && capacidad > 0) {
            habitacion.setCapacidad(capacidad);
        }
        
        if (costo != null && costo >= 0) {
            habitacion.setCosto(costo);
        }
        
        if (hotelId != null) {
            if (hotelRepository.findHotelById(hotelId) == null) {
                return false;
            }
            habitacion.setHotelId(hotelId);
        }
        
        habitacionRepository.saveHabitacion(habitacion);
        return true;
    }
    
    public boolean deleteHabitacion(Integer id) {
        habitacionRepository.deleteHabitacion(id);
        return true;
    }
}

