package VIews;

import Controllers.ClienteController;
import Models.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteConsole {
    private ClienteController clienteController;
    private Scanner scanner;

    public ClienteConsole() {
        this.clienteController = new ClienteController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Listar todos los clientes");
            System.out.println("2. Agregar nuevo cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Promedio de trayectos por viaje para clientes que han realizado más de un viaje.");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllClientes();
                    break;
                case 2:
                    addCliente();
                    break;
                case 3:
                    updateCliente();
                    break;
                case 4:
                    deleteCliente();
                    break;
                case 5:
                    promedioTrayectosPorViajeDeClientesConMasDeUno();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllClientes() {
        System.out.println("\n--- TODOS LOS CLIENTES ---");
        List<Cliente> clientes = clienteController.getAllClientes();
        if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + " - Nombre: " + cliente.getNombre() + 
                    " - Teléfono: " + cliente.getTelefono() + " - Correo: " + cliente.getCorreo());
            }
        }
    }

    private void addCliente() {
        System.out.println("\n--- AGREGAR NUEVO CLIENTE ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();

        boolean success = clienteController.addCliente(nombre, telefono, correo);
        if (success) {
            System.out.println("Cliente agregado exitosamente.");
        } else {
            System.out.println("Error al agregar cliente. Por favor verifique los datos.");
        }
    }

    private void updateCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        System.out.print("Ingrese el ID del cliente: ");
        int id = readIntInput();

        Cliente cliente = clienteController.getClienteById(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + cliente.getId() + " - Nombre: " + cliente.getNombre() + 
            " - Teléfono: " + cliente.getTelefono() + " - Correo: " + cliente.getCorreo());
        
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese nuevo teléfono (presione Enter para mantener el actual): ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese nuevo correo (presione Enter para mantener el actual): ");
        String correo = scanner.nextLine();

        boolean success = clienteController.updateCliente(id, nombre.isEmpty() ? null : nombre, 
            telefono.isEmpty() ? null : telefono, correo.isEmpty() ? null : correo);
        if (success) {
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar cliente.");
        }
    }

    private void deleteCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese el ID del cliente: ");
        int id = readIntInput();

        boolean success = clienteController.deleteCliente(id);
        if (success) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar cliente. El cliente puede no existir.");
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
    private void promedioTrayectosPorViajeDeClientesConMasDeUno(){
        double respuesta = clienteController.promedioTrayectosPorViaje();
        System.out.println("Promedio: "+respuesta);
    }
}

