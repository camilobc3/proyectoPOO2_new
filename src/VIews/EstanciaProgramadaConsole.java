package VIews;

import Controllers.EstanciaProgramadaController;
import Models.EstanciaProgramada;
import java.util.List;
import java.util.Scanner;

public class EstanciaProgramadaConsole {
    private EstanciaProgramadaController estanciaProgramadaController;
    private Scanner scanner;

    public EstanciaProgramadaConsole() {
        this.estanciaProgramadaController = new EstanciaProgramadaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE ESTANCIAS PROGRAMADAS ===");
            System.out.println("1. Listar todas las estancias programadas");
            System.out.println("2. Agregar nueva estancia programada");
            System.out.println("3. Actualizar estancia programada");
            System.out.println("4. Eliminar estancia programada");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllEstanciasProgramadas();
                    break;
                case 2:
                    addEstanciaProgramada();
                    break;
                case 3:
                    updateEstanciaProgramada();
                    break;
                case 4:
                    deleteEstanciaProgramada();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllEstanciasProgramadas() {
        System.out.println("\n--- TODAS LAS ESTANCIAS PROGRAMADAS ---");
        List<EstanciaProgramada> estancias = estanciaProgramadaController.getAllEstanciasProgramadas();
        if (estancias.isEmpty()) {
            System.out.println("No se encontraron estancias programadas.");
        } else {
            for (EstanciaProgramada estancia : estancias) {
                System.out.println("ID: " + estancia.getId() + " - Habitación ID: " + estancia.getHabitacionId() + 
                    " - Usuario ID: " + estancia.getItinerarioTransporteId());
            }
        }
    }

    private void addEstanciaProgramada() {
        System.out.println("\n--- AGREGAR NUEVA ESTANCIA PROGRAMADA ---");
        System.out.print("Ingrese el ID de la habitación: ");
        scanner.nextLine();
        int habitacionId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID del usuario: ");
        int usuarioId = Integer.parseInt(scanner.nextLine());

        boolean success = estanciaProgramadaController.addEstanciaProgramada(habitacionId, usuarioId);
        if (success) {
            System.out.println("Estancia programada agregada exitosamente.");
        } else {
            System.out.println("Error al agregar estancia programada. Por favor verifique los datos.");
        }
    }

    private void updateEstanciaProgramada() {
        System.out.println("\n--- ACTUALIZAR ESTANCIA PROGRAMADA ---");
        System.out.print("Ingrese el ID de la estancia: ");
        int id = readIntInput();

        EstanciaProgramada estancia = estanciaProgramadaController.getEstanciaProgramadaById(id);
        if (estancia == null) {
            System.out.println("Estancia programada no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + estancia.getId() + " - Habitación ID: " + estancia.getHabitacionId() + 
            " - Usuario ID: " + estancia.getItinerarioTransporteId());
        
        System.out.print("Ingrese nuevo ID de habitación (presione Enter para mantener el actual): ");
        scanner.nextLine();
        String habitacionIdInput = scanner.nextLine();
        Integer habitacionId = habitacionIdInput.isEmpty() ? null : Integer.parseInt(habitacionIdInput);

        System.out.print("Ingrese nuevo ID de usuario (presione Enter para mantener el actual): ");
        String usuarioIdInput = scanner.nextLine();
        Integer usuarioId = usuarioIdInput.isEmpty() ? null : Integer.parseInt(usuarioIdInput);

        boolean success = estanciaProgramadaController.updateEstanciaProgramada(id, habitacionId, usuarioId);
        if (success) {
            System.out.println("Estancia programada actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar estancia programada.");
        }
    }

    private void deleteEstanciaProgramada() {
        System.out.println("\n--- ELIMINAR ESTANCIA PROGRAMADA ---");
        System.out.print("Ingrese el ID de la estancia: ");
        int id = readIntInput();

        boolean success = estanciaProgramadaController.deleteEstanciaProgramada(id);
        if (success) {
            System.out.println("Estancia programada eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar estancia programada. La estancia puede no existir.");
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

