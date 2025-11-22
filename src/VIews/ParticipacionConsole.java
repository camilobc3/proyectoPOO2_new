package VIews;

import Controllers.ParticipacionController;
import Models.Participacion;
import java.util.List;
import java.util.Scanner;

public class ParticipacionConsole {
    private ParticipacionController participacionController;
    private Scanner scanner;

    public ParticipacionConsole() {
        this.participacionController = new ParticipacionController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE PARTICIPACIONES ===");
            System.out.println("1. Listar todas las participaciones");
            System.out.println("2. Agregar nueva participación");
            System.out.println("3. Actualizar participación");
            System.out.println("4. Eliminar participación");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllParticipaciones();
                    break;
                case 2:
                    addParticipacion();
                    break;
                case 3:
                    updateParticipacion();
                    break;
                case 4:
                    deleteParticipacion();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllParticipaciones() {
        System.out.println("\n--- TODAS LAS PARTICIPACIONES ---");
        List<Participacion> participaciones = participacionController.getAllParticipaciones();
        if (participaciones.isEmpty()) {
            System.out.println("No se encontraron participaciones.");
        } else {
            for (Participacion participacion : participaciones) {
                System.out.println("ID: " + participacion.getId() + " - Viaje ID: " + participacion.getViajeId() + 
                    " - Cliente ID: " + participacion.getClienteId());
            }
        }
    }

    private void addParticipacion() {
        System.out.println("\n--- AGREGAR NUEVA PARTICIPACIÓN ---");
        System.out.print("Ingrese el ID del viaje: ");
        int viajeId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID del cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());

        boolean success = participacionController.addParticipacion(viajeId, clienteId);
        if (success) {
            System.out.println("Participación agregada exitosamente.");
        } else {
            System.out.println("Error al agregar participación. Por favor verifique los datos.");
        }
    }

    private void updateParticipacion() {
        System.out.println("\n--- ACTUALIZAR PARTICIPACIÓN ---");
        System.out.print("Ingrese el ID de la participación: ");
        int id = readIntInput();

        Participacion participacion = participacionController.getParticipacionById(id);
        if (participacion == null) {
            System.out.println("Participación no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + participacion.getId() + " - Viaje ID: " + participacion.getViajeId() + 
            " - Cliente ID: " + participacion.getClienteId());
        
        System.out.print("Ingrese nuevo ID de viaje (presione Enter para mantener el actual): ");
        String viajeIdInput = scanner.nextLine();
        Integer viajeId = viajeIdInput.isEmpty() ? null : Integer.parseInt(viajeIdInput);

        System.out.print("Ingrese nuevo ID de cliente (presione Enter para mantener el actual): ");
        String clienteIdInput = scanner.nextLine();
        Integer clienteId = clienteIdInput.isEmpty() ? null : Integer.parseInt(clienteIdInput);

        boolean success = participacionController.updateParticipacion(id, viajeId, clienteId);
        if (success) {
            System.out.println("Participación actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar participación.");
        }
    }

    private void deleteParticipacion() {
        System.out.println("\n--- ELIMINAR PARTICIPACIÓN ---");
        System.out.print("Ingrese el ID de la participación: ");
        int id = readIntInput();

        boolean success = participacionController.deleteParticipacion(id);
        if (success) {
            System.out.println("Participación eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar participación. La participación puede no existir.");
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

