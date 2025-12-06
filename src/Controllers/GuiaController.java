package Controllers;

import Models.Guia;
import DataAccess.GuiaRepository;
import java.util.List;

public class GuiaController {
    private GuiaRepository guiaRepository;
    
    //Constructor por defecto
    public GuiaController() {
        this.guiaRepository = new GuiaRepository();
    }
    
    // Constructor for dependency injection
    public GuiaController(GuiaRepository guiaRepository) {
        this.guiaRepository = guiaRepository;
    }
    
    public List<Guia> getAllGuias() {
        return guiaRepository.getAllGuias();
    }
    
    public Guia getGuiaById(Integer id) {
        return guiaRepository.findGuiaById(id);
    }
    
    public boolean addGuia(String nombre, String telefono, String correo) {
        if (nombre == null || nombre.trim().isEmpty() || 
            correo == null || correo.trim().isEmpty()) {
            return false;
        }
        
        Guia guia = new Guia();
        guia.setNombre(nombre.trim());
        guia.setTelefono(telefono != null ? telefono.trim() : "");
        guia.setCorreo(correo.trim());
        
        guiaRepository.saveGuia(guia);
        return true;
    }
    
    public boolean updateGuia(Integer id, String nombre, String telefono, String correo) {
        Guia guia = guiaRepository.findGuiaById(id);
        if (guia == null) {
            return false;
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            guia.setNombre(nombre.trim());
        }
        if (telefono != null) {
            guia.setTelefono(telefono.trim());
        }
        if (correo != null && !correo.trim().isEmpty()) {
            guia.setCorreo(correo.trim());
        }
        
        guiaRepository.saveGuia(guia);
        return true;
    }
    
    public boolean deleteGuia(Integer id) {
        guiaRepository.deleteGuia(id);
        return true;
    }
}

