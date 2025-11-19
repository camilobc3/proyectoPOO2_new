package VIews;

import java.util.Scanner;

public class MainConsole {
    private Scanner scanner;

    public MainConsole() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE AGENCIA DE VIAJES ===");
            System.out.println("=== GESTIÓN BÁSICA ===");
            System.out.println("1. Gestión de Municipios");
            System.out.println("2. Gestión de Aerolíneas");
            System.out.println("3. Gestión de Trayectos");
            System.out.println("4. Gestión de Planes");
            System.out.println("5. Gestión de Actividades Turísticas");
            System.out.println("\n=== GESTIÓN DE HOSPEDAJE ===");
            System.out.println("6. Gestión de Hoteles");
            System.out.println("7. Gestión de Habitaciones");
            System.out.println("\n=== GESTIÓN DE TRANSPORTE ===");
            System.out.println("8. Gestión de Viajes");
            System.out.println("9. Gestión de Cuotas");
            System.out.println("10. Gestión de Servicios de Transporte");
            System.out.println("11. Gestión de Aeronaves");
            System.out.println("12. Gestión de Carros");
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("13. Gestión de Clientes");
            System.out.println("14. Gestión de Guías");
            System.out.println("15. Gestión de Paquetes Contratados");
            System.out.println("\n=== GESTIÓN DE ASOCIACIONES ===");
            System.out.println("16. Gestión de Asignaciones de Guía");
            System.out.println("17. Gestión de Componentes de Plan");
            System.out.println("18. Gestión de Itinerarios de Transporte");
            System.out.println("19. Gestión de Estancias Programadas");
            System.out.println("20. Gestión de Participaciones");
            System.out.println("\n0. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    MunicipioConsole municipioConsole = new MunicipioConsole();
                    municipioConsole.showMenu();
                    break;
                case 2:
                    AerolineaConsole aerolineaConsole = new AerolineaConsole();
                    aerolineaConsole.showMenu();
                    break;
                case 3:
                    TrayectoConsole trayectoConsole = new TrayectoConsole();
                    trayectoConsole.showMenu();
                    break;
                case 4:
                    PlanConsole planConsole = new PlanConsole();
                    planConsole.showMenu();
                    break;
                case 5:
                    ActividadTuristicaConsole actividadTuristicaConsole = new ActividadTuristicaConsole();
                    actividadTuristicaConsole.showMenu();
                    break;
                case 6:
                    HotelConsole hotelConsole = new HotelConsole();
                    hotelConsole.showMenu();
                    break;
                case 7:
                    HabitacionConsole habitacionConsole = new HabitacionConsole();
                    habitacionConsole.showMenu();
                    break;
                case 8:
                    ViajeConsole viajeConsole = new ViajeConsole();
                    viajeConsole.showMenu();
                    break;
                case 9:
                    CuotaConsole cuotaConsole = new CuotaConsole();
                    cuotaConsole.showMenu();
                    break;
                case 10:
                    ServicioTransporteConsole servicioTransporteConsole = new ServicioTransporteConsole();
                    servicioTransporteConsole.showMenu();
                    break;
                case 11:
                    AeronaveConsole aeronaveConsole = new AeronaveConsole();
                    aeronaveConsole.showMenu();
                    break;
                case 12:
                    CarroConsole carroConsole = new CarroConsole();
                    carroConsole.showMenu();
                    break;
                case 13:
                    ClienteConsole clienteConsole = new ClienteConsole();
                    clienteConsole.showMenu();
                    break;
                case 14:
                    GuiaConsole guiaConsole = new GuiaConsole();
                    guiaConsole.showMenu();
                    break;
                case 15:
                    PaqueteContratadoConsole paqueteContratadoConsole = new PaqueteContratadoConsole();
                    paqueteContratadoConsole.showMenu();
                    break;
                case 16:
                    AsignacionGuiaConsole asignacionGuiaConsole = new AsignacionGuiaConsole();
                    asignacionGuiaConsole.showMenu();
                    break;
                case 17:
                    ComponentePlanConsole componentePlanConsole = new ComponentePlanConsole();
                    componentePlanConsole.showMenu();
                    break;
                case 18:
                    ItinerarioTransporteConsole itinerarioTransporteConsole = new ItinerarioTransporteConsole();
                    itinerarioTransporteConsole.showMenu();
                    break;
                case 19:
                    EstanciaProgramadaConsole estanciaProgramadaConsole = new EstanciaProgramadaConsole();
                    estanciaProgramadaConsole.showMenu();
                    break;
                case 20:
                    ParticipacionConsole participacionConsole = new ParticipacionConsole();
                    participacionConsole.showMenu();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
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

    public static void main(String[] args) {
        MainConsole mainConsole = new MainConsole();
        mainConsole.showMainMenu();
    }
}

