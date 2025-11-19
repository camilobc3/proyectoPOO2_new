package VIews;

import Controllers.AeronaveController;
import Models.Aeronave;
import java.util.List;
import java.util.Scanner;

public class AeronaveConsole {
    private AeronaveController aeronaveController;
    private Scanner scanner;

    public AeronaveConsole() {
        this.aeronaveController = new AeronaveController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE AERONAVES ===");
            System.out.println("1. Listar todas las aeronaves");
            System.out.println("2. Agregar nueva aeronave");
            System.out.println("3. Actualizar aeronave");
            System.out.println("4. Eliminar aeronave");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllAeronaves();
                    break;
                case 2:
                    addAeronave();
                    break;
                case 3:
                    updateAeronave();
                    break;
                case 4:
                    deleteAeronave();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllAeronaves() {
        System.out.println("\n--- TODAS LAS AERONAVES ---");
        List<Aeronave> aeronaves = aeronaveController.getAllAeronaves();
        if (aeronaves.isEmpty()) {
            System.out.println("No se encontraron aeronaves.");
        } else {
            for (Aeronave aeronave : aeronaves) {
                System.out.println("ID: " + aeronave.getId() + " - Marca GPS: " + aeronave.getMarcaGps() + 
                    (aeronave.getAerolineaId() != null ? " - Aerolínea ID: " + aeronave.getAerolineaId() : ""));
            }
        }
    }

    private void addAeronave() {
        System.out.println("\n--- AGREGAR NUEVA AERONAVE ---");
        System.out.print("Ingrese la marca del GPS: ");
        scanner.nextLine();
        String marcaGps = scanner.nextLine();

        System.out.print("Ingrese el ID de la aerolínea (opcional, presione Enter para omitir): ");
        String aerolineaIdInput = scanner.nextLine();
        Integer aerolineaId = aerolineaIdInput.isEmpty() ? null : Integer.parseInt(aerolineaIdInput);

        boolean success = aeronaveController.addAeronave(marcaGps, aerolineaId);
        if (success) {
            System.out.println("Aeronave agregada exitosamente.");
        } else {
            System.out.println("Error al agregar aeronave. Por favor verifique los datos.");
        }
    }

    private void updateAeronave() {
        System.out.println("\n--- ACTUALIZAR AERONAVE ---");
        System.out.print("Ingrese el ID de la aeronave: ");
        int id = readIntInput();

        Aeronave aeronave = aeronaveController.getAeronaveById(id);
        if (aeronave == null) {
            System.out.println("Aeronave no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + aeronave.getId() + " - Marca GPS: " + aeronave.getMarcaGps() + 
            (aeronave.getAerolineaId() != null ? " - Aerolínea ID: " + aeronave.getAerolineaId() : ""));
        
        System.out.print("Ingrese nueva marca de GPS (presione Enter para mantener la actual): ");
        scanner.nextLine();
        String marcaGps = scanner.nextLine();

        System.out.print("Ingrese nuevo ID de aerolínea (presione Enter para mantener el actual): ");
        String aerolineaIdInput = scanner.nextLine();
        Integer aerolineaId = aerolineaIdInput.isEmpty() ? null : Integer.parseInt(aerolineaIdInput);

        boolean success = aeronaveController.updateAeronave(id, marcaGps.isEmpty() ? null : marcaGps, aerolineaId);
        if (success) {
            System.out.println("Aeronave actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar aeronave.");
        }
    }

    private void deleteAeronave() {
        System.out.println("\n--- ELIMINAR AERONAVE ---");
        System.out.print("Ingrese el ID de la aeronave: ");
        int id = readIntInput();

        boolean success = aeronaveController.deleteAeronave(id);
        if (success) {
            System.out.println("Aeronave eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar aeronave. La aeronave puede no existir.");
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

