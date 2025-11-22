package VIews;

import Controllers.GuiaController;
import Models.Guia;
import java.util.List;
import java.util.Scanner;

public class GuiaConsole {
    private GuiaController guiaController;
    private Scanner scanner;

    public GuiaConsole() {
        this.guiaController = new GuiaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE GUÍAS ===");
            System.out.println("1. Listar todos los guías");
            System.out.println("2. Agregar nuevo guía");
            System.out.println("3. Actualizar guía");
            System.out.println("4. Eliminar guía");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllGuias();
                    break;
                case 2:
                    addGuia();
                    break;
                case 3:
                    updateGuia();
                    break;
                case 4:
                    deleteGuia();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllGuias() {
        System.out.println("\n--- TODOS LOS GUÍAS ---");
        List<Guia> guias = guiaController.getAllGuias();
        if (guias.isEmpty()) {
            System.out.println("No se encontraron guías.");
        } else {
            for (Guia guia : guias) {
                System.out.println("ID: " + guia.getId() + " - Nombre: " + guia.getNombre() + 
                    " - Teléfono: " + guia.getTelefono() + " - Correo: " + guia.getCorreo());
            }
        }
    }

    private void addGuia() {
        System.out.println("\n--- AGREGAR NUEVO GUÍA ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();

        boolean success = guiaController.addGuia(nombre, telefono, correo);
        if (success) {
            System.out.println("Guía agregado exitosamente.");
        } else {
            System.out.println("Error al agregar guía. Por favor verifique los datos.");
        }
    }

    private void updateGuia() {
        System.out.println("\n--- ACTUALIZAR GUÍA ---");
        System.out.print("Ingrese el ID del guía: ");
        int id = readIntInput();

        Guia guia = guiaController.getGuiaById(id);
        if (guia == null) {
            System.out.println("Guía no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + guia.getId() + " - Nombre: " + guia.getNombre() + 
            " - Teléfono: " + guia.getTelefono() + " - Correo: " + guia.getCorreo());
        
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese nuevo teléfono (presione Enter para mantener el actual): ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese nuevo correo (presione Enter para mantener el actual): ");
        String correo = scanner.nextLine();

        boolean success = guiaController.updateGuia(id, nombre.isEmpty() ? null : nombre, 
            telefono.isEmpty() ? null : telefono, correo.isEmpty() ? null : correo);
        if (success) {
            System.out.println("Guía actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar guía.");
        }
    }

    private void deleteGuia() {
        System.out.println("\n--- ELIMINAR GUÍA ---");
        System.out.print("Ingrese el ID del guía: ");
        int id = readIntInput();

        boolean success = guiaController.deleteGuia(id);
        if (success) {
            System.out.println("Guía eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar guía. El guía puede no existir.");
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

