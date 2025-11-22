package VIews;

import Controllers.AsignacionGuiaController;
import Models.AsignacionGuia;
import java.util.List;
import java.util.Scanner;

public class AsignacionGuiaConsole {
    private AsignacionGuiaController asignacionGuiaController;
    private Scanner scanner;

    public AsignacionGuiaConsole() {
        this.asignacionGuiaController = new AsignacionGuiaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE ASIGNACIONES DE GUÍA ===");
            System.out.println("1. Listar todas las asignaciones de guía");
            System.out.println("2. Agregar nueva asignación de guía");
            System.out.println("3. Actualizar asignación de guía");
            System.out.println("4. Eliminar asignación de guía");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllAsignacionesGuia();
                    break;
                case 2:
                    addAsignacionGuia();
                    break;
                case 3:
                    updateAsignacionGuia();
                    break;
                case 4:
                    deleteAsignacionGuia();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllAsignacionesGuia() {
        System.out.println("\n--- TODAS LAS ASIGNACIONES DE GUÍA ---");
        List<AsignacionGuia> asignaciones = asignacionGuiaController.getAllAsignacionesGuia();
        if (asignaciones.isEmpty()) {
            System.out.println("No se encontraron asignaciones de guía.");
        } else {
            for (AsignacionGuia asignacion : asignaciones) {
                System.out.println("ID: " + asignacion.getId() + " - Guía ID: " + asignacion.getGuiaId() + 
                    " - Actividad Turística ID: " + asignacion.getActividadTuristicaId());
            }
        }
    }

    private void addAsignacionGuia() {
        System.out.println("\n--- AGREGAR NUEVA ASIGNACIÓN DE GUÍA ---");
        System.out.print("Ingrese el ID del guía: ");
        int guiaId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID de la actividad turística: ");
        int actividadTuristicaId = Integer.parseInt(scanner.nextLine());

        boolean success = asignacionGuiaController.addAsignacionGuia(guiaId, actividadTuristicaId);
        if (success) {
            System.out.println("Asignación de guía agregada exitosamente.");
        } else {
            System.out.println("Error al agregar asignación de guía. Por favor verifique los datos.");
        }
    }

    private void updateAsignacionGuia() {
        System.out.println("\n--- ACTUALIZAR ASIGNACIÓN DE GUÍA ---");
        System.out.print("Ingrese el ID de la asignación: ");
        int id = readIntInput();

        AsignacionGuia asignacion = asignacionGuiaController.getAsignacionGuiaById(id);
        if (asignacion == null) {
            System.out.println("Asignación de guía no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + asignacion.getId() + " - Guía ID: " + asignacion.getGuiaId() + 
            " - Actividad Turística ID: " + asignacion.getActividadTuristicaId());
        
        System.out.print("Ingrese nuevo ID de guía (presione Enter para mantener el actual): ");
        String guiaIdInput = scanner.nextLine();
        Integer guiaId = guiaIdInput.isEmpty() ? null : Integer.parseInt(guiaIdInput);

        System.out.print("Ingrese nuevo ID de actividad turística (presione Enter para mantener el actual): ");
        String actividadTuristicaIdInput = scanner.nextLine();
        Integer actividadTuristicaId = actividadTuristicaIdInput.isEmpty() ? null : Integer.parseInt(actividadTuristicaIdInput);

        boolean success = asignacionGuiaController.updateAsignacionGuia(id, guiaId, actividadTuristicaId);
        if (success) {
            System.out.println("Asignación de guía actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar asignación de guía.");
        }
    }

    private void deleteAsignacionGuia() {
        System.out.println("\n--- ELIMINAR ASIGNACIÓN DE GUÍA ---");
        System.out.print("Ingrese el ID de la asignación: ");
        int id = readIntInput();

        boolean success = asignacionGuiaController.deleteAsignacionGuia(id);
        if (success) {
            System.out.println("Asignación de guía eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar asignación de guía. La asignación puede no existir.");
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

