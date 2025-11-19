package VIews;

import Controllers.HotelController;
import Models.Hotel;
import java.util.List;
import java.util.Scanner;

public class HotelConsole {
    private HotelController hotelController;
    private Scanner scanner;

    public HotelConsole() {
        this.hotelController = new HotelController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GESTIÓN DE HOTELES ===");
            System.out.println("1. Listar todos los hoteles");
            System.out.println("2. Agregar nuevo hotel");
            System.out.println("3. Actualizar hotel");
            System.out.println("4. Eliminar hotel");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllHoteles();
                    break;
                case 2:
                    addHotel();
                    break;
                case 3:
                    updateHotel();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Por favor intente nuevamente.");
            }
        }
    }

    private void listAllHoteles() {
        System.out.println("\n--- TODOS LOS HOTELES ---");
        List<Hotel> hoteles = hotelController.getAllHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("No se encontraron hoteles.");
        } else {
            for (Hotel hotel : hoteles) {
                System.out.println("ID: " + hotel.getId() + " - Nombre: " + hotel.getNombre() + 
                    (hotel.getMunicipioId() != null ? " - Municipio ID: " + hotel.getMunicipioId() : ""));
            }
        }
    }

    private void addHotel() {
        System.out.println("\n--- AGREGAR NUEVO HOTEL ---");
        System.out.print("Ingrese el nombre del hotel: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el ID del municipio (opcional, presione Enter para omitir): ");
        String municipioIdInput = scanner.nextLine();
        Integer municipioId = municipioIdInput.isEmpty() ? null : Integer.parseInt(municipioIdInput);

        boolean success = hotelController.addHotel(nombre, municipioId);
        if (success) {
            System.out.println("Hotel agregado exitosamente.");
        } else {
            System.out.println("Error al agregar hotel. Por favor verifique los datos.");
        }
    }

    private void updateHotel() {
        System.out.println("\n--- ACTUALIZAR HOTEL ---");
        System.out.print("Ingrese el ID del hotel: ");
        int id = readIntInput();

        Hotel hotel = hotelController.getHotelById(id);
        if (hotel == null) {
            System.out.println("Hotel no encontrado.");
            return;
        }

        System.out.println("Datos actuales: ID: " + hotel.getId() + " - Nombre: " + hotel.getNombre() + 
            (hotel.getMunicipioId() != null ? " - Municipio ID: " + hotel.getMunicipioId() : ""));
        
        System.out.print("Ingrese nuevo nombre (presione Enter para mantener el actual): ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.print("Ingrese nuevo ID de municipio (presione Enter para mantener el actual): ");
        String municipioIdInput = scanner.nextLine();
        Integer municipioId = municipioIdInput.isEmpty() ? null : Integer.parseInt(municipioIdInput);

        boolean success = hotelController.updateHotel(id, nombre.isEmpty() ? null : nombre, municipioId);
        if (success) {
            System.out.println("Hotel actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar hotel.");
        }
    }

    private void deleteHotel() {
        System.out.println("\n--- ELIMINAR HOTEL ---");
        System.out.print("Ingrese el ID del hotel: ");
        int id = readIntInput();

        boolean success = hotelController.deleteHotel(id);
        if (success) {
            System.out.println("Hotel eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar hotel. El hotel puede no existir.");
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

