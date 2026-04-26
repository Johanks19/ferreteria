import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static List<Herramienta> inventario = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Alquiler> alquileres = new ArrayList<>();
    private static List<Compra> compras = new ArrayList<>();

    public static void main(String[] args) {
        inicializarInventario();
        mostrarMenuPrincipal();
    }

    private static void inicializarInventario() {
        inventario.add(new HerramientaManual("Martillo", "Truper", "H001", 10, "Manual", "Golpe", 150000, 50000, false));
        inventario.add(new HerramientaManual("Destornillador", "Stanley", "H002", 20, "Manual", "Sujeción", 15000, 5000, false));
        inventario.add(new HerramientaManual("Cinta Métrica", "Stanley", "H003", 15, "Manual", "Medición", 8000, 5000, false));
        inventario.add(new HerramientaElectrica("Taladro", "Bosch", "H004", 5, "Mecánica", "Perforación", 120000, 50000, false, 500, true));
        inventario.add(new HerramientaElectrica("Sierra Eléctrica", "DeWalt", "H005", 3, "Mecánica", "Corte", 250000, 100000, false, 1500, false));
    }

    private static void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Tienda de Herramientas ---");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    registrarCliente(scanner);
                    break;
                case 3:
                    System.out.println("Gracias por usar la tienda. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.print("Ingrese su cédula: ");
        String cedula = scanner.next();
        Cliente cliente = buscarClientePorCedula(cedula);

        if (cliente != null) {
            mostrarMenuCliente(scanner, cliente);
        } else {
            System.out.println("Cliente no encontrado. Debe registrarse primero.");
        }
    }

    private static void registrarCliente(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");

        String nombre = scanner.next();

        System.out.print("Ingrese su cédula: ");

        String cedula = scanner.next();

        System.out.print("Ingrese su dirección: ");

        String direccion = scanner.next();

        System.out.print("Ingrese su teléfono: ");

        String telefono = scanner.next();


        Cliente cliente = new Cliente(nombre, cedula, telefono, direccion);
        clientes.add(cliente);
        System.out.println("Registro exitoso. Ahora puede iniciar sesión.");
    }

    private static void mostrarMenuCliente(Scanner scanner, Cliente cliente) {
        while (true) {
            System.out.println("\n--- Menú Cliente ---");
            System.out.println("1. Comprar Herramienta");
            System.out.println("2. Alquilar Herramienta");
            System.out.println("3. Devolver Herramienta");
            System.out.println("4. Ver Herramientas Disponibles");
            System.out.println("5. Ver Información del Cliente");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    realizarCompra(scanner, cliente);
                    break;
                case 2:
                    realizarAlquiler(scanner, cliente);
                    break;
                case 3:
                    devolverHerramienta(scanner, cliente);
                    break;
                case 4:
                    mostrarInventarioDisponible();
                    break;
                case 5:
                    verInformacionCliente(cliente);
                    break;
                case 6:
                    System.out.println("Cerrando sesión...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static Cliente buscarClientePorCedula(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    private static Herramienta buscarHerramientaPorId(String id) {
        for (Herramienta herramienta : inventario) {
            if (herramienta.getId().equals(id)) {
                return herramienta;
            }
        }
        return null;
    }

    private static void realizarCompra(Scanner scanner, Cliente cliente) {
        System.out.println("Herramientas disponibles para compra:");
        mostrarInventarioDisponible();
        System.out.print("Ingrese el ID de la herramienta que desea comprar: ");
        String id = scanner.next();
        Herramienta herramienta = buscarHerramientaPorId(id);

        if (herramienta != null && herramienta.getCantidad() > 0) {
            System.out.print("Ingrese la cantidad a comprar: ");
            int cantidad = scanner.nextInt();
            if (cantidad <= herramienta.getCantidad()) {
                double total = herramienta.getPrecioVenta() * cantidad;
                Compra compra = new Compra(herramienta, cliente, total,cantidad);
                total = compra.aplicarDescuentoCompra(total, cantidad);
                herramienta.actualizarInventario(-cantidad);
                compras.add(compra);
                System.out.println("Compra realizada exitosamente. Total: $" + total);
            } else {
                System.out.println("Cantidad no disponible en inventario.");
            }
        } else {
            System.out.println("Herramienta no disponible.");
        }
    }

    private static void realizarAlquiler(Scanner scanner, Cliente cliente) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("              ALQUILAR HERRAMIENTA");
        System.out.println("--------------------------------------------------------------");
        mostrarInventarioDisponible();

        System.out.print("Ingrese el ID de la herramienta que desea alquilar: ");
        String idHerramienta = scanner.next();
        Herramienta herramientaSeleccionada = buscarHerramientaPorId(idHerramienta);

        if (herramientaSeleccionada == null) {
            System.out.println("Herramienta no encontrada.");
            return;
        }

        System.out.print("Ingrese la cantidad que desea alquilar: ");
        int cantidad = scanner.nextInt();

        if (herramientaSeleccionada.getCantidad() < cantidad) {
            System.out.println("No hay suficientes unidades disponibles en el inventario.");
            return;
        }

        System.out.print("Ingrese la cantidad de días que desea alquilar la herramienta: ");
        int diasAlquiler = scanner.nextInt();

        double precioTotal = herramientaSeleccionada.getPrecioAlquilerDia() * cantidad * diasAlquiler;

        long herramientasAlquiladas = alquileres.stream()
                .filter(a -> a.getCliente().equals(cliente) && a.isActivo())
                .mapToInt(Alquiler::getCantidad)
                .sum();

        Alquiler tempAlquiler = new Alquiler(herramientaSeleccionada, cliente, diasAlquiler, cantidad, precioTotal, true);
        precioTotal = tempAlquiler.aplicarDescuento(precioTotal, herramientasAlquiladas, cantidad);

        Alquiler nuevoAlquiler = new Alquiler(herramientaSeleccionada, cliente, diasAlquiler, cantidad, precioTotal, true);
        alquileres.add(nuevoAlquiler);

        herramientaSeleccionada.actualizarInventario(-cantidad);

        System.out.println("¡Alquiler realizado con éxito!");
        System.out.printf("Herramienta: %s, Cantidad: %d, Días: %d, Costo Total (con descuento si aplica): %.2f%n",
                herramientaSeleccionada.getNombreHerramienta(), cantidad, diasAlquiler, precioTotal);
        System.out.println("--------------------------------------------------------------");
    }

    private static void devolverHerramienta(Scanner scanner, Cliente cliente) {

        System.out.println("--------------------------------------------------------------");
        System.out.println("              DEVOLVER HERRAMIENTA");
        System.out.println("--------------------------------------------------------------");

        boolean tieneAlquileres = false;
        System.out.println("Herramientas alquiladas activas:");
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente().equals(cliente) && alquiler.isActivo()) {
                tieneAlquileres = true;
                System.out.printf("ID: %s, Herramienta: %s, Cantidad: %d, Días alquilados: %d%n",
                        alquiler.getHerramienta().getId(),
                        alquiler.getHerramienta().getNombreHerramienta(),
                        alquiler.getCantidad(),
                        alquiler.getDiasAlquiler());
            }
        }

        if (!tieneAlquileres) {
            System.out.println("No tienes herramientas alquiladas activas para devolver.");
            return;
        }

        System.out.print("Ingrese el ID de la herramienta que desea devolver: ");
        String idHerramienta = scanner.next();
        Alquiler alquilerEncontrado = null;

        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente().equals(cliente) && alquiler.isActivo() &&
                    alquiler.getHerramienta().getId().equals(idHerramienta)) {
                alquilerEncontrado = alquiler;
                break;
            }
        }

        if (alquilerEncontrado == null) {
            System.out.println("No se encontró un alquiler activo con el ID proporcionado.");
            return;
        }

        Herramienta herramienta = alquilerEncontrado.getHerramienta();
        herramienta.actualizarInventario(alquilerEncontrado.getCantidad());

        alquilerEncontrado.setActivo(false);

        System.out.printf("Has devuelto la herramienta: %s, Cantidad: %d.%n",
                herramienta.getNombreHerramienta(), alquilerEncontrado.getCantidad());
        System.out.println("¡Gracias por devolver la herramienta!");
        System.out.println("--------------------------------------------------------------");
    }

    private static void mostrarInventarioDisponible() {

        System.out.println("--------------------------------------------------------------");
        System.out.println("              HERRAMIENTAS DISPONIBLES PARA ALQUILER/VENTA     ");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-15s %-10s %-10s %-10s %-15s %-15s %-10s %-10s %-10s%n",
                "NOMBRE", "ID", "TIPO", "CANTIDAD", "FUNCIÓN", "PRECIO VENTA",
                "PRECIO ALQUILER", "POTENCIA", "INALÁMBRICO");

        for (Herramienta herramienta : inventario) {
            if (herramienta.getCantidad() > 0 ) {
                if (herramienta instanceof HerramientaElectrica) {
                    HerramientaElectrica electrica = (HerramientaElectrica) herramienta;
                    System.out.printf("%-15s %-10s %-10s %-10d %-15s %-15.2f %-10.2f %-10.1f %-10s%n",
                            herramienta.getNombreHerramienta(), herramienta.getId(), herramienta.getTipo(),
                            herramienta.getCantidad(), herramienta.getFuncion(), herramienta.getPrecioVenta(),
                            herramienta.getPrecioAlquilerDia(), electrica.getPotenciaMotor(),
                            electrica.isInalambrico() ? "Sí" : "No");
                } else {
                    System.out.printf("%-15s %-10s %-10s %-10d %-15s %-15.2f %-10.2f %-10s %-10s%n",
                            herramienta.getNombreHerramienta(), herramienta.getId(), herramienta.getTipo(),
                            herramienta.getCantidad(), herramienta.getFuncion(), herramienta.getPrecioVenta(),
                            herramienta.getPrecioAlquilerDia(), "-", "-");
                }
            }
        }
        System.out.println("--------------------------------------------------------------");
    }


    private static int contarHerramientasAlquiladas(Cliente cliente) {
        int count = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente().equals(cliente) && alquiler.isActivo()) {
                count++;
            }
        }
        return count;
    }

    private static void verInformacionCliente(Cliente cliente) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Cédula: " + cliente.getCedula());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("Dirección: " + cliente.getDireccion());
        System.out.println();

        System.out.println("Herramientas alquiladas:");
        boolean tieneAlquileres = false;
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente().equals(cliente)) {
                Herramienta herramienta = alquiler.getHerramienta();
                System.out.printf("- %s (ID: %s, Marca: %s, Cantidad: %d, Costo total del alquiler: %.2f, Estado: %s)%n",
                        herramienta.getNombreHerramienta(),
                        herramienta.getId(),
                        herramienta.getMarca(),
                        alquiler.getCantidad(),
                        alquiler.getPrecioTotal(),
                        alquiler.isActivo() ? "Activo" : "Finalizado");
                tieneAlquileres = true;
            }
        }
        if (!tieneAlquileres) {
            System.out.println("No tiene herramientas alquiladas.");
        }

        System.out.println();


        System.out.println("Herramientas compradas:");
        boolean tieneCompras = false;
        for (Compra compra : compras) {
            if (compra.getCliente().equals(cliente)) {
                Herramienta herramienta = compra.getHerramienta();
                System.out.printf("- %s (ID: %s, Marca: %s, Cantidad: %d, Costo total de la compra: %.2f)%n",
                        herramienta.getNombreHerramienta(),
                        herramienta.getId(),
                        herramienta.getMarca(),
                        compra.getCantidad(),
                        compra.getPrecioTotalCompra());
                tieneCompras = true;
            }
        }
        if (!tieneCompras) {
            System.out.println("No tiene herramientas compradas.");
        }

        System.out.println("--------------------------------------------------------------");
    }
}