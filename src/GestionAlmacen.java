import java.util.HashMap;
import java.util.Scanner;

// Clase Producto
class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;

    public Producto(String codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void reducirCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Cantidad disponible: " + cantidad;
    }
}

// Clase Almacen que agrupa las categorías y productos
class Almacen {
    private HashMap<String, Producto[]> categorias;

    public Almacen() {
        categorias = new HashMap<>();

        // Cargando las categorías con productos específicos
        categorias.put("Explosivos", new Producto[]{
                new Producto("EXP001", "Dinamita", 50),
                new Producto("EXP002", "C4", 20),
                new Producto("EXP003", "Nitroglicerina", 30)
        });

        categorias.put("Aceros", new Producto[]{
                new Producto("ACR001", "Placa de acero", 100),
                new Producto("ACR002", "Tubo de acero", 75)
        });

        categorias.put("EPPs", new Producto[]{
                new Producto("EPP001", "Casco de seguridad", 200),
                new Producto("EPP002", "Botas de seguridad", 150),
                new Producto("EPP003", "Chaleco reflectante", 100)
        });

        categorias.put("Maderas", new Producto[]{
                new Producto("MAD001", "Tablón de madera", 50),
                new Producto("MAD002", "Viga de madera", 30)
        });

        categorias.put("Artículos de Oficina", new Producto[]{
                new Producto("OFI001", "Resma de papel", 100),
                new Producto("OFI002", "Bolígrafos", 200),
                new Producto("OFI003", "Carpetas", 150)
        });

        categorias.put("Artículos de Limpieza", new Producto[]{
                new Producto("LIM001", "Detergente", 80),
                new Producto("LIM002", "Escobas", 40)
        });

        categorias.put("Repuestos de Perforadoras", new Producto[]{
                new Producto("REP001", "Broca de perforadora", 50),
                new Producto("REP002", "Filtro de perforadora", 20)
        });
    }

    // Mostrar productos de una categoría
    public void mostrarProductos(String categoria) {
        if (categorias.containsKey(categoria)) {
            Producto[] productos = categorias.get(categoria);
            System.out.println("\n--- Productos en " + categoria + " ---");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    // Mostrar categorías disponibles
    public void mostrarCategorias() {
        System.out.println("\n--- Categorías Disponibles ---");
        for (String categoria : categorias.keySet()) {
            System.out.println(categoria);
        }
    }

    // Añadir un nuevo producto a una categoría
    public void agregarProducto(String categoria, Producto nuevoProducto) {
        if (categorias.containsKey(categoria)) {
            Producto[] productosExistentes = categorias.get(categoria);
            Producto[] nuevosProductos = new Producto[productosExistentes.length + 1];

            System.arraycopy(productosExistentes, 0, nuevosProductos, 0, productosExistentes.length);
            nuevosProductos[productosExistentes.length] = nuevoProducto;

            categorias.put(categoria, nuevosProductos);
            System.out.println("Producto agregado exitosamente a la categoría " + categoria);
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    // Realizar salida de un producto
    public void realizarSalida(String categoria, String codigo, int cantidadSalida) {
        if (categorias.containsKey(categoria)) {
            Producto[] productos = categorias.get(categoria);
            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    if (producto.getCantidad() >= cantidadSalida) {
                        producto.reducirCantidad(cantidadSalida);
                        System.out.println("Salida realizada: " + cantidadSalida + " unidades de " + producto.getNombre());
                    } else {
                        System.out.println("No hay suficiente cantidad disponible para realizar la salida.");
                    }
                    return;
                }
            }
            System.out.println("Producto no encontrado en la categoría.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }
}

// Clase principal para la simulación
public class GestionAlmacen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Almacen almacen = new Almacen();
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Mostrar categorías");
            System.out.println("2. Mostrar productos por categoría");
            System.out.println("3. Ingresar nuevo producto");
            System.out.println("4. Realizar salida de almacén");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    almacen.mostrarCategorias();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la categoría: ");
                    String categoria = scanner.nextLine();
                    almacen.mostrarProductos(categoria);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de la categoría donde quiere agregar el producto: ");
                    categoria = scanner.nextLine();
                    System.out.print("Ingrese el código del producto: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    Producto nuevoProducto = new Producto(codigo, nombre, cantidad);
                    almacen.agregarProducto(categoria, nuevoProducto);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de la categoría donde quiere realizar la salida: ");
                    categoria = scanner.nextLine();
                    System.out.print("Ingrese el código del producto que desea retirar: ");
                    codigo = scanner.nextLine();
                    System.out.print("Ingrese la cantidad que desea retirar: ");
                    int cantidadSalida = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    almacen.realizarSalida(categoria, codigo, cantidadSalida);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
