package VIews;

import Controllers.ActividadTuristicaController;
import Models.ActividadTuristica;
import java.util.List;
import java.util.Scanner;

public class ActividadTuristicaConsole {
    private ActividadTuristicaController actividadTuristicaController;
    private Scanner scanner;

    public ActividadTuristicaConsole() {
        this.actividadTuristicaController = new ActividadTuristicaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE ACTIVIDADES TURÍSTICAS ===");
            System.out.println("1. Listar todas las actividades turísticas");
            System.out.println("2. Agregar nueva actividad turística");
            System.out.println("3. Actualizar actividad turística");
            System.out.println("4. Eliminar actividad turística");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllActividadesTuristicas();
                    break;
                case 2:
                    addActividadTuristica();
                    break;
                case 3:
                    updateActividadTuristica();
                    break;
                case 4:
                    deleteActividadTuristica();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllActividadesTuristicas() {
        System.out.println("\n--- TODAS LAS ACTIVIDADES TURÍSTICAS ---");
        List<ActividadTuristica> actividades = actividadTuristicaController.getAllActividadesTuristicas();
        if (actividades.isEmpty()) {
            System.out.println("No se encontraron actividades turísticas.");
        } else {
            for (ActividadTuristica actividad : actividades) {
                System.out.println("ID: " + actividad.getId() + " - Nombre: " + actividad.getNombre());
            }
        }
    }

    private void addActividadTuristica() {
        System.out.println("\n--- AGREGAR NUEVA ACTIVIDAD TURÍSTICA ---");
        System.out.print("Ingrese el nombre de la actividad: ");
        String nombre = scanner.nextLine();

        boolean success = actividadTuristicaController.addActividadTuristica(nombre);
        if (success) {
            System.out.println("Actividad turística agregada exitosamente.");
        } else {
            System.out.println("Error al agregar actividad turística. Por favor verifique los datos.");
        }
    }

    private void updateActividadTuristica() {
        System.out.println("\n--- ACTUALIZAR ACTIVIDAD TURÍSTICA ---");
        System.out.print("Ingrese el ID de la actividad: ");
        int id = readIntInput();

        ActividadTuristica actividad = actividadTuristicaController.getActividadTuristicaById(id);
        if (actividad == null) {
            System.out.println("Actividad turística no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + actividad.getId() + " - Nombre: " + actividad.getNombre());
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        boolean success = actividadTuristicaController.updateActividadTuristica(id, nombre.isEmpty() ? null : nombre);
        if (success) {
            System.out.println("Actividad turística actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar actividad turística.");
        }
    }

    private void deleteActividadTuristica() {
        System.out.println("\n--- ELIMINAR ACTIVIDAD TURÍSTICA ---");
        System.out.print("Ingrese el ID de la actividad: ");
        int id = readIntInput();

        boolean success = actividadTuristicaController.deleteActividadTuristica(id);
        if (success) {
            System.out.println("Actividad turística eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar actividad turística. La actividad puede no existir.");
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

