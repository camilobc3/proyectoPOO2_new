package VIews;

import Controllers.PaqueteContratadoController;
import Models.PaqueteContratado;
import java.util.List;
import java.util.Scanner;

public class PaqueteContratadoConsole {
    private PaqueteContratadoController paqueteContratadoController;
    private Scanner scanner;

    public PaqueteContratadoConsole() {
        this.paqueteContratadoController = new PaqueteContratadoController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE PAQUETES CONTRATADOS ===");
            System.out.println("1. Listar todos los paquetes contratados");
            System.out.println("2. Agregar nuevo paquete contratado");
            System.out.println("3. Actualizar paquete contratado");
            System.out.println("4. Eliminar paquete contratado");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllPaquetesContratados();
                    break;
                case 2:
                    addPaqueteContratado();
                    break;
                case 3:
                    updatePaqueteContratado();
                    break;
                case 4:
                    deletePaqueteContratado();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllPaquetesContratados() {
        System.out.println("\n--- TODOS LOS PAQUETES CONTRATADOS ---");
        List<PaqueteContratado> paquetes = paqueteContratadoController.getAllPaquetesContratados();
        if (paquetes.isEmpty()) {
            System.out.println("No se encontraron paquetes contratados.");
        } else {
            for (PaqueteContratado paquete : paquetes) {
                System.out.println("ID: " + paquete.getId() + " - Fecha Inicio: " + paquete.getFechaInicio() + 
                    " - Fecha Fin: " + paquete.getFechaFin() + 
                    (paquete.getUsuarioId() != null ? " - Usuario ID: " + paquete.getUsuarioId() : "") + 
                    (paquete.getPlanId() != null ? " - Plan ID: " + paquete.getPlanId() : ""));
            }
        }
    }

    private void addPaqueteContratado() {
        System.out.println("\n--- AGREGAR NUEVO PAQUETE CONTRATADO ---");
        System.out.print("Ingrese la fecha de inicio: ");
        scanner.nextLine();
        String fechaInicio = scanner.nextLine();

        System.out.print("Ingrese la fecha de fin: ");
        String fechaFin = scanner.nextLine();

        System.out.print("Ingrese el ID del usuario (opcional, presione Enter para omitir): ");
        String usuarioIdInput = scanner.nextLine();
        Integer usuarioId = usuarioIdInput.isEmpty() ? null : Integer.parseInt(usuarioIdInput);

        System.out.print("Ingrese el ID del plan (opcional, presione Enter para omitir): ");
        String planIdInput = scanner.nextLine();
        Integer planId = planIdInput.isEmpty() ? null : Integer.parseInt(planIdInput);

        boolean success = paqueteContratadoController.addPaqueteContratado(fechaInicio, fechaFin, usuarioId, planId);
        if (success) {
            System.out.println("Paquete contratado agregado exitosamente.");
        } else {
            System.out.println("Error al agregar paquete contratado. Por favor verifique los datos.");
        }
    }

    private void updatePaqueteContratado() {
        System.out.println("\n--- ACTUALIZAR PAQUETE CONTRATADO ---");
        System.out.print("Ingrese el ID del paquete: ");
        int id = readIntInput();

        PaqueteContratado paquete = paqueteContratadoController.getPaqueteContratadoById(id);
        if (paquete == null) {
            System.out.println("Paquete contratado no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + paquete.getId() + " - Fecha Inicio: " + paquete.getFechaInicio() + 
            " - Fecha Fin: " + paquete.getFechaFin());
        
        System.out.print("Ingrese nueva fecha de inicio (presione Enter para mantener la actual): ");
        scanner.nextLine();
        String fechaInicio = scanner.nextLine();

        System.out.print("Ingrese nueva fecha de fin (presione Enter para mantener la actual): ");
        String fechaFin = scanner.nextLine();

        System.out.print("Ingrese nuevo ID de usuario (presione Enter para mantener el actual): ");
        String usuarioIdInput = scanner.nextLine();
        Integer usuarioId = usuarioIdInput.isEmpty() ? null : Integer.parseInt(usuarioIdInput);

        System.out.print("Ingrese nuevo ID de plan (presione Enter para mantener el actual): ");
        String planIdInput = scanner.nextLine();
        Integer planId = planIdInput.isEmpty() ? null : Integer.parseInt(planIdInput);

        boolean success = paqueteContratadoController.updatePaqueteContratado(id, fechaInicio.isEmpty() ? null : fechaInicio, 
            fechaFin.isEmpty() ? null : fechaFin, usuarioId, planId);
        if (success) {
            System.out.println("Paquete contratado actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar paquete contratado.");
        }
    }

    private void deletePaqueteContratado() {
        System.out.println("\n--- ELIMINAR PAQUETE CONTRATADO ---");
        System.out.print("Ingrese el ID del paquete: ");
        int id = readIntInput();

        boolean success = paqueteContratadoController.deletePaqueteContratado(id);
        if (success) {
            System.out.println("Paquete contratado eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar paquete contratado. El paquete puede no existir.");
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

