import java.util.ArrayList;
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

// Clase Categoria que agrupa productos
class Categoria {
    private String nombre;
    private ArrayList<Producto> productos;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

// Clase Almacen que gestiona las categorías
class Almacen {
    private ArrayList<Categoria> categorias;

    public Almacen() {
        categorias = new ArrayList<>();

        // Agregando categorías y productos
        Categoria explosivos = new Categoria("Explosivos");
        explosivos.agregarProducto(new Producto("EXP001", "Dinamita", 50));
        explosivos.agregarProducto(new Producto("EXP002", "C4", 20));
        explosivos.agregarProducto(new Producto("EXP003", "Nitroglicerina", 30));
        categorias.add(explosivos);

        Categoria aceros = new Categoria("Aceros");
        aceros.agregarProducto(new Producto("ACR001", "Placa de acero", 100));
        aceros.agregarProducto(new Producto("ACR002", "Tubo de acero", 75));
        categorias.add(aceros);

        Categoria epps = new Categoria("EPPs");
        epps.agregarProducto(new Producto("EPP001", "Casco de seguridad", 200));
        epps.agregarProducto(new Producto("EPP002", "Botas de seguridad", 150));
        epps.agregarProducto(new Producto("EPP003", "Chaleco reflectante", 100));
        categorias.add(epps);

        // Otras categorías...
    }

    public void mostrarCategorias() {
        System.out.println("\n--- Categorías Disponibles ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    public Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        return null;
    }

    public void mostrarProductos(String nombreCategoria) {
        Categoria categoria = buscarCategoriaPorNombre(nombreCategoria);
        if (categoria != null) {
            System.out.println("\n--- Productos en " + nombreCategoria + " ---");
            for (Producto producto : categoria.getProductos()) {
                System.out.println(producto);
            }
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    public void agregarProducto(String nombreCategoria, Producto nuevoProducto) {
        Categoria categoria = buscarCategoriaPorNombre(nombreCategoria);
        if (categoria != null) {
            categoria.agregarProducto(nuevoProducto);
            System.out.println("Producto agregado exitosamente a la categoría " + nombreCategoria);
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    public void realizarSalida(String nombreCategoria, String codigoProducto, int cantidadSalida) {
        Categoria categoria = buscarCategoriaPorNombre(nombreCategoria);
        if (categoria != null) {
            Producto producto = categoria.buscarProductoPorCodigo(codigoProducto);
            if (producto != null) {
                if (producto.getCantidad() >= cantidadSalida) {
                    producto.reducirCantidad(cantidadSalida);
                    System.out.println("Salida realizada: " + cantidadSalida + " unidades de " + producto.getNombre());
                } else {
                    System.out.println("No hay suficiente cantidad disponible para realizar la salida.");
                }
            } else {
                System.out.println("Producto no encontrado en la categoría.");
            }
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }
}

// Clase principal
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
