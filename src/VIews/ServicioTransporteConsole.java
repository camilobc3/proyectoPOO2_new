package VIews;

import Controllers.ServicioTransporteController;
import Models.ServicioTransporte;
import java.util.List;
import java.util.Scanner;

public class ServicioTransporteConsole {
    private ServicioTransporteController servicioTransporteController;
    private Scanner scanner;

    public ServicioTransporteConsole() {
        this.servicioTransporteController = new ServicioTransporteController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE SERVICIOS DE TRANSPORTE ===");
            System.out.println("1. Listar todos los servicios de transporte");
            System.out.println("2. Agregar nuevo servicio de transporte");
            System.out.println("3. Actualizar servicio de transporte");
            System.out.println("4. Eliminar servicio de transporte");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllServiciosTransporte();
                    break;
                case 2:
                    addServicioTransporte();
                    break;
                case 3:
                    updateServicioTransporte();
                    break;
                case 4:
                    deleteServicioTransporte();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllServiciosTransporte() {
        System.out.println("\n--- TODOS LOS SERVICIOS DE TRANSPORTE ---");
        List<ServicioTransporte> servicios = servicioTransporteController.getAllServiciosTransporte();
        if (servicios.isEmpty()) {
            System.out.println("No se encontraron servicios de transporte.");
        } else {
            for (ServicioTransporte servicio : servicios) {
                System.out.println("ID: " + servicio.getId() + " - Fecha Inicio: " + servicio.getFechaInicio() + 
                    " - Fecha Fin: " + servicio.getFechaFin() + " - Costo: " + servicio.getCosto() + 
                    (servicio.getTrayectoId() != null ? " - Trayecto ID: " + servicio.getTrayectoId() : "") + 
                    (servicio.getVehiculoId() != null ? " - Vehículo ID: " + servicio.getVehiculoId() : ""));
            }
        }
    }

    private void addServicioTransporte() {
        System.out.println("\n--- AGREGAR NUEVO SERVICIO DE TRANSPORTE ---");
        System.out.print("Ingrese la fecha de inicio: ");
        scanner.nextLine();
        String fechaInicio = scanner.nextLine();

        System.out.print("Ingrese la fecha de fin: ");
        String fechaFin = scanner.nextLine();

        System.out.print("Ingrese el costo: ");
        double costo = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese el ID del trayecto (opcional, presione Enter para omitir): ");
        String trayectoIdInput = scanner.nextLine();
        Integer trayectoId = trayectoIdInput.isEmpty() ? null : Integer.parseInt(trayectoIdInput);

        System.out.print("Ingrese el ID del vehículo (opcional, presione Enter para omitir): ");
        String vehiculoIdInput = scanner.nextLine();
        Integer vehiculoId = vehiculoIdInput.isEmpty() ? null : Integer.parseInt(vehiculoIdInput);

        boolean success = servicioTransporteController.addServicioTransporte(fechaInicio, fechaFin, costo, trayectoId, vehiculoId);
        if (success) {
            System.out.println("Servicio de transporte agregado exitosamente.");
        } else {
            System.out.println("Error al agregar servicio de transporte. Por favor verifique los datos.");
        }
    }

    private void updateServicioTransporte() {
        System.out.println("\n--- ACTUALIZAR SERVICIO DE TRANSPORTE ---");
        System.out.print("Ingrese el ID del servicio: ");
        int id = readIntInput();

        ServicioTransporte servicio = servicioTransporteController.getServicioTransporteById(id);
        if (servicio == null) {
            System.out.println("Servicio de transporte no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + servicio.getId() + " - Fecha Inicio: " + servicio.getFechaInicio() + 
            " - Fecha Fin: " + servicio.getFechaFin() + " - Costo: " + servicio.getCosto());
        
        System.out.print("Ingrese nueva fecha de inicio (presione Enter para mantener la actual): ");
        scanner.nextLine();
        String fechaInicio = scanner.nextLine();

        System.out.print("Ingrese nueva fecha de fin (presione Enter para mantener la actual): ");
        String fechaFin = scanner.nextLine();

        System.out.print("Ingrese nuevo costo (presione Enter para mantener el actual): ");
        String costoInput = scanner.nextLine();
        Double costo = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        System.out.print("Ingrese nuevo ID de trayecto (presione Enter para mantener el actual): ");
        String trayectoIdInput = scanner.nextLine();
        Integer trayectoId = trayectoIdInput.isEmpty() ? null : Integer.parseInt(trayectoIdInput);

        System.out.print("Ingrese nuevo ID de vehículo (presione Enter para mantener el actual): ");
        String vehiculoIdInput = scanner.nextLine();
        Integer vehiculoId = vehiculoIdInput.isEmpty() ? null : Integer.parseInt(vehiculoIdInput);

        boolean success = servicioTransporteController.updateServicioTransporte(id, fechaInicio.isEmpty() ? null : fechaInicio, 
            fechaFin.isEmpty() ? null : fechaFin, costo, trayectoId, vehiculoId);
        if (success) {
            System.out.println("Servicio de transporte actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar servicio de transporte.");
        }
    }

    private void deleteServicioTransporte() {
        System.out.println("\n--- ELIMINAR SERVICIO DE TRANSPORTE ---");
        System.out.print("Ingrese el ID del servicio: ");
        int id = readIntInput();

        boolean success = servicioTransporteController.deleteServicioTransporte(id);
        if (success) {
            System.out.println("Servicio de transporte eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar servicio de transporte. El servicio puede no existir.");
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

