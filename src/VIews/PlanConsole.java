package VIews;

import Controllers.PlanController;
import Models.Plan;
import java.util.List;
import java.util.Scanner;

public class PlanConsole {
    private PlanController planController;
    private Scanner scanner;

    public PlanConsole() {
        this.planController = new PlanController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE PLANES ===");
            System.out.println("1. Listar todos los planes");
            System.out.println("2. Agregar nuevo plan");
            System.out.println("3. Actualizar plan");
            System.out.println("4. Eliminar plan");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllPlanes();
                    break;
                case 2:
                    addPlan();
                    break;
                case 3:
                    updatePlan();
                    break;
                case 4:
                    deletePlan();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllPlanes() {
        System.out.println("\n--- TODOS LOS PLANES ---");
        List<Plan> planes = planController.getAllPlanes();
        if (planes.isEmpty()) {
            System.out.println("No se encontraron planes.");
        } else {
            for (Plan plan : planes) {
                System.out.println("ID: " + plan.getId() + " - Nombre: " + plan.getNombre());
            }
        }
    }

    private void addPlan() {
        System.out.println("\n--- AGREGAR NUEVO PLAN ---");
        System.out.print("Ingrese el nombre del plan: ");
        String nombre = scanner.nextLine();

        boolean success = planController.addPlan(nombre);
        if (success) {
            System.out.println("Plan agregado exitosamente.");
        } else {
            System.out.println("Error al agregar plan. Por favor verifique los datos.");
        }
    }

    private void updatePlan() {
        System.out.println("\n--- ACTUALIZAR PLAN ---");
        System.out.print("Ingrese el ID del plan: ");
        int id = readIntInput();

        Plan plan = planController.getPlanById(id);
        if (plan == null) {
            System.out.println("Plan no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + plan.getId() + " - Nombre: " + plan.getNombre());
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        boolean success = planController.updatePlan(id, nombre.isEmpty() ? null : nombre);
        if (success) {
            System.out.println("Plan actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar plan.");
        }
    }

    private void deletePlan() {
        System.out.println("\n--- ELIMINAR PLAN ---");
        System.out.print("Ingrese el ID del plan: ");
        int id = readIntInput();

        boolean success = planController.deletePlan(id);
        if (success) {
            System.out.println("Plan eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar plan. El plan puede no existir.");
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

