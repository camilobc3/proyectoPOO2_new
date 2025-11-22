/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Models.Habitacion;
import java.util.List;

public class HabitacionRepository {
    private IDataAccess<Habitacion> dataAccess;
    
    public HabitacionRepository() {
        this.dataAccess = new JsonRepository<>("Data/habitaciones.json", Habitacion.class);
    }
    
    // Constructor for dependency injection
    public HabitacionRepository(IDataAccess<Habitacion> dataAccess) {
        this.dataAccess = dataAccess;
    }
    
    public List<Habitacion> getAllHabitaciones() {
        return dataAccess.findAll();
    }
    
    public Habitacion findHabitacionById(Integer id) {
        return dataAccess.findById(id);
    }
    
    public void saveHabitacion(Habitacion habitacion) {
        dataAccess.save(habitacion);
    }
    
    public void deleteHabitacion(Integer id) {
        dataAccess.delete(id);
    }
}

