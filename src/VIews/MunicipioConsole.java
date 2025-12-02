package VIews;

import Controllers.MunicipioController;
import Models.Municipio;
import java.util.List;
import java.util.Scanner;

public class MunicipioConsole {
    private MunicipioController municipioController;
    private Scanner scanner;

    public MunicipioConsole() {
        this.municipioController = new MunicipioController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE MUNICIPIOS ===");
            System.out.println("1. Listar todos los municipios");
            System.out.println("2. Agregar nuevo municipio");
            System.out.println("3. Actualizar municipio");
            System.out.println("4. Eliminar municipio");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllMunicipios();
                    break;
                case 2:
                    addMunicipio();
                    break;
                case 3:
                    updateMunicipio();
                    break;
                case 4:
                    deleteMunicipio();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    public void listAllMunicipios() {
        System.out.println("\n--- TODOS LOS MUNICIPIOS ---");
        List<Municipio> municipios = municipioController.getAllMunicipios();
        if (municipios.isEmpty()) {
            System.out.println("No se encontraron municipios.");
        } else {
            for (Municipio municipio : municipios) {
                System.out.println("ID: " + municipio.getId() + " - Nombre: " + municipio.getNombre());
            }
        }
    }

    private void addMunicipio() {
        System.out.println("\n--- AGREGAR NUEVO MUNICIPIO ---");
        System.out.print("Ingrese el nombre del municipio: ");
        String nombre = scanner.nextLine();

        boolean success = municipioController.addMunicipio(nombre);
        if (success) {
            System.out.println("Municipio agregado exitosamente.");
        } else {
            System.out.println("Error al agregar municipio. Por favor verifique los datos.");
        }
    }

    private void updateMunicipio() {
        System.out.println("\n--- ACTUALIZAR MUNICIPIO ---");
        System.out.print("Ingrese el ID del municipio: ");
        int id = readIntInput();

        Municipio municipio = municipioController.getMunicipioById(id);
        if (municipio == null) {
            System.out.println("Municipio no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + municipio.getId() + " - Nombre: " + municipio.getNombre());
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        boolean success = municipioController.updateMunicipio(id, nombre.isEmpty() ? null : nombre);
        if (success) {
            System.out.println("Municipio actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar municipio.");
        }
    }

    private void deleteMunicipio() {
        System.out.println("\n--- ELIMINAR MUNICIPIO ---");
        System.out.print("Ingrese el ID del municipio: ");
        int id = readIntInput();

        boolean success = municipioController.deleteMunicipio(id);
        if (success) {
            System.out.println("Municipio eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar municipio. El municipio puede no existir.");
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

