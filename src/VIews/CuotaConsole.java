package VIews;

import Controllers.CuotaController;
import Models.Cuota;
import java.util.List;
import java.util.Scanner;

public class CuotaConsole {
    private CuotaController cuotaController;
    private Scanner scanner;

    public CuotaConsole() {
        this.cuotaController = new CuotaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE CUOTAS ===");
            System.out.println("1. Listar todas las cuotas");
            System.out.println("2. Agregar nueva cuota");
            System.out.println("3. Actualizar cuota");
            System.out.println("4. Eliminar cuota");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllCuotas();
                    break;
                case 2:
                    addCuota();
                    break;
                case 3:
                    updateCuota();
                    break;
                case 4:
                    deleteCuota();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllCuotas() {
        System.out.println("\n--- TODAS LAS CUOTAS ---");
        List<Cuota> cuotas = cuotaController.getAllCuotas();
        if (cuotas.isEmpty()) {
            System.out.println("No se encontraron cuotas.");
        } else {
            for (Cuota cuota : cuotas) {
                System.out.println("ID: " + cuota.getId() + " - Monto: " + cuota.getMonto() + 
                    (cuota.getViajeId() != null ? " - Viaje ID: " + cuota.getViajeId() : ""));
            }
        }
    }

    private void addCuota() {
        System.out.println("\n--- AGREGAR NUEVA CUOTA ---");
        System.out.print("Ingrese el monto: ");
        double monto = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el ID del viaje (opcional, presione Enter para omitir): ");
        String viajeIdInput = scanner.nextLine();
        Integer viajeId = viajeIdInput.isEmpty() ? null : Integer.parseInt(viajeIdInput);

        boolean success = cuotaController.addCuota(monto, viajeId);
        if (success) {
            System.out.println("Cuota agregada exitosamente.");
        } else {
            System.out.println("Error al agregar cuota. Por favor verifique los datos.");
        }
    }

    private void updateCuota() {
        System.out.println("\n--- ACTUALIZAR CUOTA ---");
        System.out.print("Ingrese el ID de la cuota: ");
        int id = readIntInput();

        Cuota cuota = cuotaController.getCuotaById(id);
        if (cuota == null) {
            System.out.println("Cuota no encontrada.");
            return;
        }

        System.out.println("Datos actuales: ID: " + cuota.getId() + " - Monto: " + cuota.getMonto() + 
            (cuota.getViajeId() != null ? " - Viaje ID: " + cuota.getViajeId() : ""));
        
        System.out.print("Ingrese nuevo monto (presione Enter para mantener el actual): ");
        String montoInput = scanner.nextLine();
        Double monto = montoInput.isEmpty() ? null : Double.parseDouble(montoInput);

        System.out.print("Ingrese nuevo ID de viaje (presione Enter para mantener el actual): ");
        String viajeIdInput = scanner.nextLine();
        Integer viajeId = viajeIdInput.isEmpty() ? null : Integer.parseInt(viajeIdInput);

        boolean success = cuotaController.updateCuota(id, monto, viajeId);
        if (success) {
            System.out.println("Cuota actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar cuota.");
        }
    }

    private void deleteCuota() {
        System.out.println("\n--- ELIMINAR CUOTA ---");
        System.out.print("Ingrese el ID de la cuota: ");
        int id = readIntInput();

        boolean success = cuotaController.deleteCuota(id);
        if (success) {
            System.out.println("Cuota eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar cuota. La cuota puede no existir.");
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

