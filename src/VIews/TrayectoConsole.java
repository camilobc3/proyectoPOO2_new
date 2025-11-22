package VIews;

import Controllers.TrayectoController;
import Models.Trayecto;
import java.util.List;
import java.util.Scanner;

public class TrayectoConsole {
    private TrayectoController trayectoController;
    private Scanner scanner;

    public TrayectoConsole() {
        this.trayectoController = new TrayectoController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE TRAYECTOS ===");
            System.out.println("1. Listar todos los trayectos");
            System.out.println("2. Agregar nuevo trayecto");
            System.out.println("3. Actualizar trayecto");
            System.out.println("4. Eliminar trayecto");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllTrayectos();
                    break;
                case 2:
                    addTrayecto();
                    break;
                case 3:
                    updateTrayecto();
                    break;
                case 4:
                    deleteTrayecto();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllTrayectos() {
        System.out.println("\n--- TODOS LOS TRAYECTOS ---");
        List<Trayecto> trayectos = trayectoController.getAllTrayectos();
        if (trayectos.isEmpty()) {
            System.out.println("No se encontraron trayectos.");
        } else {
            for (Trayecto trayecto : trayectos) {
                System.out.println("ID: " + trayecto.getId() + " - Costo: " + trayecto.getCosto() + 
                    (trayecto.getMunicipioId() != null ? " - Municipio ID: " + trayecto.getMunicipioId() : ""));
            }
        }
    }

    private void addTrayecto() {
        System.out.println("\n--- AGREGAR NUEVO TRAYECTO ---");
        System.out.print("Ingrese el costo: ");
        String costoInput = scanner.nextLine();
        Double costo = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        System.out.print("Ingrese el ID del municipio (opcional, presione Enter para omitir): ");
        String municipioIdInput = scanner.nextLine();
        Integer municipioId = municipioIdInput.isEmpty() ? null : Integer.parseInt(municipioIdInput);

        boolean success = trayectoController.addTrayecto(costo, municipioId);
        if (success) {
            System.out.println("Trayecto agregado exitosamente.");
        } else {
            System.out.println("Error al agregar trayecto. Por favor verifique los datos.");
        }
    }

    private void updateTrayecto() {
        System.out.println("\n--- ACTUALIZAR TRAYECTO ---");
        System.out.print("Ingrese el ID del trayecto: ");
        int id = readIntInput();

        Trayecto trayecto = trayectoController.getTrayectoById(id);
        if (trayecto == null) {
            System.out.println("Trayecto no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + trayecto.getId() + " - Costo: " + trayecto.getCosto() + 
            (trayecto.getMunicipioId() != null ? " - Municipio ID: " + trayecto.getMunicipioId() : ""));
        
        System.out.print("Ingrese nuevo costo (presione Enter para mantener el actual): ");
        String costoInput = scanner.nextLine();
        Double costo = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        System.out.print("Ingrese nuevo ID de municipio (presione Enter para mantener el actual): ");
        String municipioIdInput = scanner.nextLine();
        Integer municipioId = municipioIdInput.isEmpty() ? null : Integer.parseInt(municipioIdInput);

        boolean success = trayectoController.updateTrayecto(id, costo, municipioId);
        if (success) {
            System.out.println("Trayecto actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar trayecto.");
        }
    }

    private void deleteTrayecto() {
        System.out.println("\n--- ELIMINAR TRAYECTO ---");
        System.out.print("Ingrese el ID del trayecto: ");
        int id = readIntInput();

        boolean success = trayectoController.deleteTrayecto(id);
        if (success) {
            System.out.println("Trayecto eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar trayecto. El trayecto puede no existir.");
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

