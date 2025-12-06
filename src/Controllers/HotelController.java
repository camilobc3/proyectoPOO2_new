package Controllers;

import Models.*;
import DataAccess.HotelRepository;
import DataAccess.MunicipioRepository;
import java.util.ArrayList;
import java.util.List;

public class HotelController {
    private HotelRepository hotelRepository;
    private MunicipioRepository municipioRepository;
    
    //Constructor por defecto
    public HotelController() {
        this.hotelRepository = new HotelRepository();
        this.municipioRepository = new MunicipioRepository();
    }
    
    // Constructor for dependency injection
    public HotelController(HotelRepository hotelRepository, MunicipioRepository municipioRepository) {
        this.hotelRepository = hotelRepository;
        this.municipioRepository = municipioRepository;
    }
    
    public List<Hotel> getAllHoteles() {
        return hotelRepository.getAllHoteles();
    }
    
    public Hotel getHotelById(Integer id) {
        return hotelRepository.findHotelById(id);
    }
    
    public boolean addHotel(String nombre, Integer municipioId) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        
        if (municipioId != null && municipioRepository.findMunicipioById(municipioId) == null) {
            return false;
        }
        
        Hotel hotel = new Hotel();
        hotel.setNombre(nombre.trim());
        if (municipioId != null) {
            hotel.setMunicipioId(municipioId);
        }
        
        hotelRepository.saveHotel(hotel);
        return true;
    }
    
    public boolean updateHotel(Integer id, String nombre, Integer municipioId) {
        Hotel hotel = hotelRepository.findHotelById(id);
        if (hotel == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            hotel.setNombre(nombre.trim());
        }
        
        if (municipioId != null) {
            if (municipioRepository.findMunicipioById(municipioId) == null) {
                return false;
            }
            hotel.setMunicipioId(municipioId);
        }
        
        hotelRepository.saveHotel(hotel);
        return true;
    }
    
    public boolean deleteHotel(Integer id) {
        hotelRepository.deleteHotel(id);
        return true;
    }
    
    //Método E
    public boolean hotelConMasDeTresActividades(Integer habitacionId){
        HabitacionController habitacionController = new HabitacionController();
        
        boolean respuesta = false;
        if(habitacionController.habitacionConAlmenosTresActividades(habitacionId));
        return respuesta;
    }
    
    public List<Hotel> hotelesConMasDeTresActividades(){
        HabitacionController habitacionController = new HabitacionController();
        List<Hotel> hoteles = getAllHoteles();
        List<Hotel> respuesta = new ArrayList<>();
        for(Hotel a : hoteles){
            List<Habitacion> habitaciones = habitacionController.getHabitacionesByhotelId(a.getId());
            
            for(Habitacion b : habitaciones){
                if(habitacionController.habitacionConAlmenosTresActividades(b.getId())){
                    respuesta.add(a);
                    break;
                }
            }
        }
        
        return respuesta;
    }
}

