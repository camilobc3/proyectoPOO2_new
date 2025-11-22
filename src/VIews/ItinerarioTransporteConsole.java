package VIews;

import Controllers.ItinerarioTransporteController;
import Models.ItinerarioTransporte;
import java.util.List;
import java.util.Scanner;

public class ItinerarioTransporteConsole {
    private ItinerarioTransporteController itinerarioTransporteController;
    private Scanner scanner;

    public ItinerarioTransporteConsole() {
        this.itinerarioTransporteController = new ItinerarioTransporteController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE ITINERARIOS DE TRANSPORTE ===");
            System.out.println("1. Listar todos los itinerarios de transporte");
            System.out.println("2. Agregar nuevo itinerario de transporte");
            System.out.println("3. Actualizar itinerario de transporte");
            System.out.println("4. Eliminar itinerario de transporte");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllItinerariosTransporte();
                    break;
                case 2:
                    addItinerarioTransporte();
                    break;
                case 3:
                    updateItinerarioTransporte();
                    break;
                case 4:
                    deleteItinerarioTransporte();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllItinerariosTransporte() {
        System.out.println("\n--- TODOS LOS ITINERARIOS DE TRANSPORTE ---");
        List<ItinerarioTransporte> itinerarios = itinerarioTransporteController.getAllItinerariosTransporte();
        if (itinerarios.isEmpty()) {
            System.out.println("No se encontraron itinerarios de transporte.");
        } else {
            for (ItinerarioTransporte itinerario : itinerarios) {
                System.out.println("ID: " + itinerario.getId() + " - Orden: " + itinerario.getOrden() + 
                    " - Viaje ID: " + itinerario.getViajeId() + " - Trayecto ID: " + itinerario.getTrayectoId());
            }
        }
    }

    private void addItinerarioTransporte() {
        System.out.println("\n--- AGREGAR NUEVO ITINERARIO DE TRANSPORTE ---");
        System.out.print("Ingrese el orden: ");
        int orden = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID del viaje (opcional, presione Enter para omitir): ");
        String viajeIdInput = scanner.nextLine();
        Integer viajeId = viajeIdInput.isEmpty() ? null : Integer.parseInt(viajeIdInput);

        System.out.print("Ingrese el ID del trayecto (opcional, presione Enter para omitir): ");
        String trayectoIdInput = scanner.nextLine();
        Integer trayectoId = trayectoIdInput.isEmpty() ? null : Integer.parseInt(trayectoIdInput);

        boolean success = itinerarioTransporteController.addItinerarioTransporte(orden, viajeId, trayectoId);
        if (success) {
            System.out.println("Itinerario de transporte agregado exitosamente.");
        } else {
            System.out.println("Error al agregar itinerario de transporte. Por favor verifique los datos.");
        }
    }

    private void updateItinerarioTransporte() {
        System.out.println("\n--- ACTUALIZAR ITINERARIO DE TRANSPORTE ---");
        System.out.print("Ingrese el ID del itinerario: ");
        int id = readIntInput();

        ItinerarioTransporte itinerario = itinerarioTransporteController.getItinerarioTransporteById(id);
        if (itinerario == null) {
            System.out.println("Itinerario de transporte no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + itinerario.getId() + " - Orden: " + itinerario.getOrden() + 
            " - Viaje ID: " + itinerario.getViajeId() + " - Trayecto ID: " + itinerario.getTrayectoId());
        
        System.out.print("Ingrese nuevo orden (presione Enter para mantener el actual): ");
        String ordenInput = scanner.nextLine();
        Integer orden = ordenInput.isEmpty() ? null : Integer.parseInt(ordenInput);

        System.out.print("Ingrese nuevo ID de viaje (presione Enter para mantener el actual): ");
        String viajeIdInput = scanner.nextLine();
        Integer viajeId = viajeIdInput.isEmpty() ? null : Integer.parseInt(viajeIdInput);

        System.out.print("Ingrese nuevo ID de trayecto (presione Enter para mantener el actual): ");
        String trayectoIdInput = scanner.nextLine();
        Integer trayectoId = trayectoIdInput.isEmpty() ? null : Integer.parseInt(trayectoIdInput);

        boolean success = itinerarioTransporteController.updateItinerarioTransporte(id, orden, viajeId, trayectoId);
        if (success) {
            System.out.println("Itinerario de transporte actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar itinerario de transporte.");
        }
    }

    private void deleteItinerarioTransporte() {
        System.out.println("\n--- ELIMINAR ITINERARIO DE TRANSPORTE ---");
        System.out.print("Ingrese el ID del itinerario: ");
        int id = readIntInput();

        boolean success = itinerarioTransporteController.deleteItinerarioTransporte(id);
        if (success) {
            System.out.println("Itinerario de transporte eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar itinerario de transporte. El itinerario puede no existir.");
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

