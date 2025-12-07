package VIews;

import Controllers.ClienteController;
import Controllers.ParticipacionController;
import Controllers.ViajeController;
import Models.Cliente;
import Models.Participacion;
import java.util.List;
import java.util.Scanner;

public class ClienteConsole {
    private ClienteController clienteController;
    private Scanner scanner;
    private AerolineaConsole aerolineaConsole;
    private MunicipioConsole municipioConsole;

    public ClienteConsole() {
        this.clienteController = new ClienteController();
        this.aerolineaConsole = new AerolineaConsole();
        this.municipioConsole = new MunicipioConsole();
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
            System.out.println("6. Conteo de clientes que han utilizado una aerolínea específica y han realizado al menos una actividad en un municipio específico.");
            System.out.println("7. Total costo viaje trayecto por cliente");
            System.out.println("8. Promedio de actividades por plan para viajes con trayecto aéreo y terrestre");
            System.out.println("9. Volver al menú principal");
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
                    conteoClientesQueHanUsadoUnaAerolineaYActividadEnUnMunicipio();
                    break;
                case 7:
                    totalCostoViajeTrayecto();
                    break;
                case 8:
                    promedioActividadesAereoTerrestrePorCliente();
                    break;
                case 9:
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
    
    private void conteoClientesQueHanUsadoUnaAerolineaYActividadEnUnMunicipio(){
        aerolineaConsole.listAllAerolineas();
        municipioConsole.listAllMunicipios();
        System.out.println("Ingrese el id de la aerolinea: ");
        Integer aerolineaId = scanner.nextInt(); 
        System.out.println("Ingrese el id del municipio: ");
        Integer municipioId = scanner.nextInt();
        int respuesta = clienteController.conteoClientesEnUnaAerolineaYEnMunicipio(aerolineaId, municipioId);
        System.out.println("Conteo: "+respuesta);
    }

    private void totalCostoViajeTrayecto(){
        System.out.println("\n--- TOTAL COSTO VIAJE TRAYECTO POR CLIENTE ---");
        System.out.print("Ingrese el ID del cliente: ");
        int id = readIntInput();

        Cliente cliente = clienteController.getClienteById(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        double total = clienteController.totalCostoViajeTrayecto(id);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Total costo: " + total);
    }

    private void promedioActividadesAereoTerrestrePorCliente(){
        System.out.println("\n--- PROMEDIO DE ACTIVIDADES POR PLAN (VIAJES CON TRAYECTO AÉREO Y TERRESTRE) ---");
        System.out.print("Ingrese el ID del cliente: ");
        int id = readIntInput();

        Cliente cliente = clienteController.getClienteById(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        ParticipacionController participacionController = new ParticipacionController();
        ViajeController viajeController = new ViajeController();

        List<Participacion> participaciones = participacionController.participacionesByClienteId(id);
        if (participaciones == null || participaciones.isEmpty()) {
            System.out.println("El cliente no tiene participaciones.");
            return;
        }

        boolean encontrado = false;
        for (Participacion participacion : participaciones) {
            List<Double> promedios = viajeController.promedioActividadesViajesQueTienenTrayectoAereoTerrestre(participacion.getViajeId());
            if (promedios != null && !promedios.isEmpty()) {
                encontrado = true;
                System.out.println("Viaje ID: " + participacion.getViajeId());
                for (Double promedio : promedios) {
                    System.out.println("Promedio actividades por plan: " + promedio);
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay viajes con trayecto aéreo y terrestre para este cliente.");
        }
    }
}

