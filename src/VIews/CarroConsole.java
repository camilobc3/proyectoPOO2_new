package VIews;

import Controllers.CarroController;
import Models.Carro;
import java.util.List;
import java.util.Scanner;

public class CarroConsole {
    private CarroController carroController;
    private Scanner scanner;

    public CarroConsole() {
        this.carroController = new CarroController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE CARROS ===");
            System.out.println("1. Listar todos los carros");
            System.out.println("2. Agregar nuevo carro");
            System.out.println("3. Actualizar carro");
            System.out.println("4. Eliminar carro");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllCarros();
                    break;
                case 2:
                    addCarro();
                    break;
                case 3:
                    updateCarro();
                    break;
                case 4:
                    deleteCarro();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllCarros() {
        System.out.println("\n--- TODOS LOS CARROS ---");
        List<Carro> carros = carroController.getAllCarros();
        if (carros.isEmpty()) {
            System.out.println("No se encontraron carros.");
        } else {
            for (Carro carro : carros) {
                System.out.println("ID: " + carro.getId() + " - Marca GPS: " + carro.getMarcaGps());
            }
        }
    }

    private void addCarro() {
        System.out.println("\n--- AGREGAR NUEVO CARRO ---");
        System.out.print("Ingrese la marca del GPS: ");
        String marcaGps = scanner.nextLine();

        boolean success = carroController.addCarro(marcaGps);
        if (success) {
            System.out.println("Carro agregado exitosamente.");
        } else {
            System.out.println("Error al agregar carro. Por favor verifique los datos.");
        }
    }

    private void updateCarro() {
        System.out.println("\n--- ACTUALIZAR CARRO ---");
        System.out.print("Ingrese el ID del carro: ");
        int id = readIntInput();

        Carro carro = carroController.getCarroById(id);
        if (carro == null) {
            System.out.println("Carro no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + carro.getId() + " - Marca GPS: " + carro.getMarcaGps());
        System.out.print("Ingrese nueva marca de GPS (presione Enter para mantener la actual): ");
        String marcaGps = scanner.nextLine();

        boolean success = carroController.updateCarro(id, marcaGps.isEmpty() ? null : marcaGps);
        if (success) {
            System.out.println("Carro actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar carro.");
        }
    }

    private void deleteCarro() {
        System.out.println("\n--- ELIMINAR CARRO ---");
        System.out.print("Ingrese el ID del carro: ");
        int id = readIntInput();

        boolean success = carroController.deleteCarro(id);
        if (success) {
            System.out.println("Carro eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar carro. El carro puede no existir.");
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

