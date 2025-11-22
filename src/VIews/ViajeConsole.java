package VIews;

import Controllers.ViajeController;
import Models.Viaje;
import java.util.List;
import java.util.Scanner;

public class ViajeConsole {
    private ViajeController viajeController;
    private Scanner scanner;

    public ViajeConsole() {
        this.viajeController = new ViajeController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE VIAJES ===");
            System.out.println("1. Listar todos los viajes");
            System.out.println("2. Agregar nuevo viaje");
            System.out.println("3. Actualizar viaje");
            System.out.println("4. Eliminar viaje");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllViajes();
                    break;
                case 2:
                    addViaje();
                    break;
                case 3:
                    updateViaje();
                    break;
                case 4:
                    deleteViaje();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllViajes() {
        System.out.println("\n--- TODOS LOS VIAJES ---");
        List<Viaje> viajes = viajeController.getAllViajes();
        if (viajes.isEmpty()) {
            System.out.println("No se encontraron viajes.");
        } else {
            for (Viaje viaje : viajes) {
                System.out.println("ID: " + viaje.getId() + " - Nombre: " + viaje.getNombre());
            }
        }
    }

    private void addViaje() {
        System.out.println("\n--- AGREGAR NUEVO VIAJE ---");
        System.out.print("Ingrese el nombre del viaje: ");
        String nombre = scanner.nextLine();

        boolean success = viajeController.addViaje(nombre);
        if (success) {
            System.out.println("Viaje agregado exitosamente.");
        } else {
            System.out.println("Error al agregar viaje. Por favor verifique los datos.");
        }
    }

    private void updateViaje() {
        System.out.println("\n--- ACTUALIZAR VIAJE ---");
        System.out.print("Ingrese el ID del viaje: ");
        int id = readIntInput();

        Viaje viaje = viajeController.getViajeById(id);
        if (viaje == null) {
            System.out.println("Viaje no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + viaje.getId() + " - Nombre: " + viaje.getNombre());
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        boolean success = viajeController.updateViaje(id, nombre.isEmpty() ? null : nombre);
        if (success) {
            System.out.println("Viaje actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar viaje.");
        }
    }

    private void deleteViaje() {
        System.out.println("\n--- ELIMINAR VIAJE ---");
        System.out.print("Ingrese el ID del viaje: ");
        int id = readIntInput();

        boolean success = viajeController.deleteViaje(id);
        if (success) {
            System.out.println("Viaje eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar viaje. El viaje puede no existir.");
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

