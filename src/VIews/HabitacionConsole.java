package VIews;

import Controllers.HabitacionController;
import Models.Habitacion;
import java.util.List;
import java.util.Scanner;

public class HabitacionConsole {
    private HabitacionController habitacionController;
    private Scanner scanner;

    public HabitacionConsole() {
        this.habitacionController = new HabitacionController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE HABITACIONES ===");
            System.out.println("1. Listar todas las habitaciones");
            System.out.println("2. Agregar nueva habitación");
            System.out.println("3. Actualizar habitación");
            System.out.println("4. Eliminar habitación");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllHabitaciones();
                    break;
                case 2:
                    addHabitacion();
                    break;
                case 3:
                    updateHabitacion();
                    break;
                case 4:
                    deleteHabitacion();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllHabitaciones() {
        System.out.println("\n--- TODAS LAS HABITACIONES ---");
        List<Habitacion> habitaciones = habitacionController.getAllHabitaciones();
        if (habitaciones.isEmpty()) {
            System.out.println("No se encontraron habitaciones.");
        } else {
            for (Habitacion habitacion : habitaciones) {
                System.out.println("ID: " + habitacion.getId() + " - Capacidad: " + habitacion.getCapacidad() + 
                    " - Costo: " + habitacion.getCosto() + 
                    (habitacion.getHotelId() != null ? " - Hotel ID: " + habitacion.getHotelId() : ""));
            }
        }
    }

    private void addHabitacion() {
        System.out.println("\n--- AGREGAR NUEVA HABITACIÓN ---");
        System.out.print("Ingrese la capacidad: ");
        scanner.nextLine();
        int capacidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el costo: ");
        double costo = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el ID del hotel (opcional, presione Enter para omitir): ");
        String hotelIdInput = scanner.nextLine();
        Integer hotelId = hotelIdInput.isEmpty() ? null : Integer.parseInt(hotelIdInput);

        boolean success = habitacionController.addHabitacion(capacidad, costo, hotelId);
        if (success) {
            System.out.println("Habitación agregada exitosamente.");
        } else {
            System.out.println("Error al agregar habitación. Por favor verifique los datos.");
        }
    }

    private void updateHabitacion() {
        System.out.println("\n--- ACTUALIZAR HABITACIÓN ---");
        System.out.print("Ingrese el ID de la habitación: ");
        int id = readIntInput();

        Habitacion habitacion = habitacionController.getHabitacionById(id);
        if (habitacion == null) {
            System.out.println("Habitación no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + habitacion.getId() + " - Capacidad: " + habitacion.getCapacidad() + 
            " - Costo: " + habitacion.getCosto() + 
            (habitacion.getHotelId() != null ? " - Hotel ID: " + habitacion.getHotelId() : ""));
        
        System.out.print("Ingrese nueva capacidad (presione Enter para mantener la actual): ");
        scanner.nextLine();
        String capacidadInput = scanner.nextLine();
        Integer capacidad = capacidadInput.isEmpty() ? null : Integer.parseInt(capacidadInput);

        System.out.print("Ingrese nuevo costo (presione Enter para mantener el actual): ");
        String costoInput = scanner.nextLine();
        Double costo = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        System.out.print("Ingrese nuevo ID de hotel (presione Enter para mantener el actual): ");
        String hotelIdInput = scanner.nextLine();
        Integer hotelId = hotelIdInput.isEmpty() ? null : Integer.parseInt(hotelIdInput);

        boolean success = habitacionController.updateHabitacion(id, capacidad, costo, hotelId);
        if (success) {
            System.out.println("Habitación actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar habitación.");
        }
    }

    private void deleteHabitacion() {
        System.out.println("\n--- ELIMINAR HABITACIÓN ---");
        System.out.print("Ingrese el ID de la habitación: ");
        int id = readIntInput();

        boolean success = habitacionController.deleteHabitacion(id);
        if (success) {
            System.out.println("Habitación eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar habitación. La habitación puede no existir.");
        }
    }

    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
            }
        }
    }
}

