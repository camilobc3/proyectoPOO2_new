package VIews;

import Controllers.ComponentePlanController;
import Models.ComponentePlan;
import java.util.List;
import java.util.Scanner;

public class ComponentePlanConsole {
    private ComponentePlanController componentePlanController;
    private Scanner scanner;

    public ComponentePlanConsole() {
        this.componentePlanController = new ComponentePlanController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE COMPONENTES DE PLAN ===");
            System.out.println("1. Listar todos los componentes de plan");
            System.out.println("2. Agregar nuevo componente de plan");
            System.out.println("3. Actualizar componente de plan");
            System.out.println("4. Eliminar componente de plan");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllComponentesPlan();
                    break;
                case 2:
                    addComponentePlan();
                    break;
                case 3:
                    updateComponentePlan();
                    break;
                case 4:
                    deleteComponentePlan();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllComponentesPlan() {
        System.out.println("\n--- TODOS LOS COMPONENTES DE PLAN ---");
        List<ComponentePlan> componentes = componentePlanController.getAllComponentesPlan();
        if (componentes.isEmpty()) {
            System.out.println("No se encontraron componentes de plan.");
        } else {
            for (ComponentePlan componente : componentes) {
                System.out.println("ID: " + componente.getId() + " - Plan ID: " + componente.getPlanId() + 
                    " - Actividad Turística ID: " + componente.getActividadTuristicaId());
            }
        }
    }

    private void addComponentePlan() {
        System.out.println("\n--- AGREGAR NUEVO COMPONENTE DE PLAN ---");
        System.out.print("Ingrese el ID del plan: ");
        scanner.nextLine();
        int planId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID de la actividad turística: ");
        int actividadTuristicaId = Integer.parseInt(scanner.nextLine());

        boolean success = componentePlanController.addComponentePlan(planId, actividadTuristicaId);
        if (success) {
            System.out.println("Componente de plan agregado exitosamente.");
        } else {
            System.out.println("Error al agregar componente de plan. Por favor verifique los datos.");
        }
    }

    private void updateComponentePlan() {
        System.out.println("\n--- ACTUALIZAR COMPONENTE DE PLAN ---");
        System.out.print("Ingrese el ID del componente: ");
        int id = readIntInput();

        ComponentePlan componente = componentePlanController.getComponentePlanById(id);
        if (componente == null) {
            System.out.println("Componente de plan no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + componente.getId() + " - Plan ID: " + componente.getPlanId() + 
            " - Actividad Turística ID: " + componente.getActividadTuristicaId());
        
        System.out.print("Ingrese nuevo ID de plan (presione Enter para mantener el actual): ");
        scanner.nextLine();
        String planIdInput = scanner.nextLine();
        Integer planId = planIdInput.isEmpty() ? null : Integer.parseInt(planIdInput);

        System.out.print("Ingrese nuevo ID de actividad turística (presione Enter para mantener el actual): ");
        String actividadTuristicaIdInput = scanner.nextLine();
        Integer actividadTuristicaId = actividadTuristicaIdInput.isEmpty() ? null : Integer.parseInt(actividadTuristicaIdInput);

        boolean success = componentePlanController.updateComponentePlan(id, planId, actividadTuristicaId);
        if (success) {
            System.out.println("Componente de plan actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar componente de plan.");
        }
    }

    private void deleteComponentePlan() {
        System.out.println("\n--- ELIMINAR COMPONENTE DE PLAN ---");
        System.out.print("Ingrese el ID del componente: ");
        int id = readIntInput();

        boolean success = componentePlanController.deleteComponentePlan(id);
        if (success) {
            System.out.println("Componente de plan eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar componente de plan. El componente puede no existir.");
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

