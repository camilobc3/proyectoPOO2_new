package VIews;

import Controllers.AerolineaController;
import Models.Aerolinea;
import java.util.List;
import java.util.Scanner;

public class AerolineaConsole {
    private AerolineaController aerolineaController;
    private Scanner scanner;

    public AerolineaConsole() {
        this.aerolineaController = new AerolineaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE AEROLÍNEAS ===");
            System.out.println("1. Listar todas las aerolíneas");
            System.out.println("2. Agregar nueva aerolínea");
            System.out.println("3. Actualizar aerolínea");
            System.out.println("4. Eliminar aerolínea");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllAerolineas();
                    break;
                case 2:
                    addAerolinea();
                    break;
                case 3:
                    updateAerolinea();
                    break;
                case 4:
                    deleteAerolinea();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllAerolineas() {
        System.out.println("\n--- TODAS LAS AEROLÍNEAS ---");
        List<Aerolinea> aerolineas = aerolineaController.getAllAerolineas();
        if (aerolineas.isEmpty()) {
            System.out.println("No se encontraron aerolíneas.");
        } else {
            for (Aerolinea aerolinea : aerolineas) {
                System.out.println("ID: " + aerolinea.getId() + " - Nombre: " + aerolinea.getNombre());
            }
        }
    }

    private void addAerolinea() {
        System.out.println("\n--- AGREGAR NUEVA AEROLÍNEA ---");
        System.out.print("Ingrese el nombre de la aerolínea: ");
        String nombre = scanner.nextLine();

        boolean success = aerolineaController.addAerolinea(nombre);
        if (success) {
            System.out.println("Aerolínea agregada exitosamente.");
        } else {
            System.out.println("Error al agregar aerolínea. Por favor verifique los datos.");
        }
    }

    private void updateAerolinea() {
        System.out.println("\n--- ACTUALIZAR AEROLÍNEA ---");
        System.out.print("Ingrese el ID de la aerolínea: ");
        int id = readIntInput();

        Aerolinea aerolinea = aerolineaController.getAerolineaById(id);
        if (aerolinea == null) {
            System.out.println("Aerolínea no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + aerolinea.getId() + " - Nombre: " + aerolinea.getNombre());
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        boolean success = aerolineaController.updateAerolinea(id, nombre.isEmpty() ? null : nombre);
        if (success) {
            System.out.println("Aerolínea actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar aerolínea.");
        }
    }

    private void deleteAerolinea() {
        System.out.println("\n--- ELIMINAR AEROLÍNEA ---");
        System.out.print("Ingrese el ID de la aerolínea: ");
        int id = readIntInput();

        boolean success = aerolineaController.deleteAerolinea(id);
        if (success) {
            System.out.println("Aerolínea eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar aerolínea. La aerolínea puede no existir.");
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

