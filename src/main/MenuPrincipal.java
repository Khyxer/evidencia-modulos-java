package main;

import dao.*;
import modelo.*;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static ProductoDAO productoDAO = new ProductoDAO();
    private static VentaDAO ventaDAO = new VentaDAO();
    private static DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

    public static void main(String[] args) {
        System.out.println("🏪 === SISTEMA PUNTO DE VENTA SENA === 🏪");
        System.out.println("📚 Proyecto: GA7-220501096-AA2-EV01 - GRUPO NOVA348");
        System.out.println("🔗 Tecnología: Java + JDBC + MySQL");

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuProductos();
                    break;
                case 3:
                    menuVentas();
                    break;
                case 4:
                    System.out.println("👋 ¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            MENÚ PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. 👤 Gestión de Usuarios");
        System.out.println("2. 📦 Gestión de Productos");
        System.out.println("3. 💰 Gestión de Ventas");
        System.out.println("4. 🚪 Salir");
        System.out.println("=".repeat(50));
        System.out.print("Seleccione una opción: ");
    }

    // === MENÚ USUARIOS ===
    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        GESTIÓN DE USUARIOS");
            System.out.println("-".repeat(40));
            System.out.println("1. ➕ Agregar usuario");
            System.out.println("2. 📋 Listar usuarios");
            System.out.println("3. ✏️ Actualizar usuario");
            System.out.println("4. 🗑️ Eliminar usuario");
            System.out.println("5. ⬅️ Volver al menú principal");
            System.out.print("Opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1: agregarUsuario(); break;
                case 2: listarUsuarios(); break;
                case 3: actualizarUsuario(); break;
                case 4: eliminarUsuario(); break;
                case 5: break;
                default: System.out.println("⚠️ Opción inválida");
            }
        } while (opcion != 5);
    }

    // === MENÚ PRODUCTOS ===
    private static void menuProductos() {
        int opcion;
        do {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("       GESTIÓN DE PRODUCTOS");
            System.out.println("-".repeat(40));
            System.out.println("1. ➕ Agregar producto");
            System.out.println("2. 📋 Listar productos");
            System.out.println("3. ✏️ Actualizar producto");
            System.out.println("4. 🗑️ Eliminar producto");
            System.out.println("5. ⬅️ Volver al menú principal");
            System.out.print("Opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1: agregarProducto(); break;
                case 2: listarProductos(); break;
                case 3: actualizarProducto(); break;
                case 4: eliminarProducto(); break;
                case 5: break;
                default: System.out.println("⚠️ Opción inválida");
            }
        } while (opcion != 5);
    }

    // === MENÚ VENTAS ===
    private static void menuVentas() {
        int opcion;
        do {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        GESTIÓN DE VENTAS");
            System.out.println("-".repeat(40));
            System.out.println("1. 🛒 Nueva venta");
            System.out.println("2. 📋 Listar ventas");
            System.out.println("3. 🔍 Ver detalle de venta");
            System.out.println("4. ⬅️ Volver al menú principal");
            System.out.print("Opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1: nuevaVenta(); break;
                case 2: listarVentas(); break;
                case 3: verDetalleVenta(); break;
                case 4: break;
                default: System.out.println("⚠️ Opción inválida");
            }
        } while (opcion != 4);
    }

    // === MÉTODOS DE USUARIOS ===
    private static void agregarUsuario() {
        System.out.println("\n--- AGREGAR USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Rol (cajero/supervisor/admin): ");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, email, telefono, rol);
        usuarioDAO.insertar(usuario);
    }

    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        List<Usuario> usuarios = usuarioDAO.consultarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("📭 No hay usuarios registrados");
        } else {
            System.out.printf("%-4s | %-20s | %-25s | %-12s | %s%n", "ID", "NOMBRE", "EMAIL", "TELÉFONO", "ROL");
            System.out.println("-".repeat(80));
            for (Usuario u : usuarios) {
                System.out.printf("%-4d | %-20s | %-25s | %-12s | %s%n",
                        u.getId(), u.getNombre(), u.getEmail(), u.getTelefono(), u.getRol());
            }
        }
    }

    private static void actualizarUsuario() {
        listarUsuarios();
        System.out.print("\nIngrese ID del usuario a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Nuevo rol: ");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, email, telefono, rol);
        usuario.setId(id);
        usuarioDAO.actualizar(usuario);
    }

    private static void eliminarUsuario() {
        listarUsuarios();
        System.out.print("\nIngrese ID del usuario a eliminar: ");
        int id = leerEntero();
        usuarioDAO.eliminar(id);
    }

    // === MÉTODOS DE PRODUCTOS ===
    private static void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();

        Producto producto = new Producto(nombre, precio, stock);
        productoDAO.insertar(producto);
    }

    private static void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = productoDAO.consultarTodos();

        if (productos.isEmpty()) {
            System.out.println("📭 No hay productos registrados");
        } else {
            System.out.printf("%-4s | %-25s | %-10s | %s%n", "ID", "NOMBRE", "PRECIO", "STOCK");
            System.out.println("-".repeat(50));
            for (Producto p : productos) {
                System.out.printf("%-4d | %-25s | $%-9.2f | %d%n",
                        p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
            }
        }
    }

    private static void actualizarProducto() {
        listarProductos();
        System.out.print("\nIngrese ID del producto a actualizar: ");
        int id = leerEntero();

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = leerDouble();
        System.out.print("Nuevo stock: ");
        int stock = leerEntero();

        Producto producto = new Producto(nombre, precio, stock);
        producto.setId(id);
        productoDAO.actualizar(producto);
    }

    private static void eliminarProducto() {
        listarProductos();
        System.out.print("\nIngrese ID del producto a eliminar: ");
        int id = leerEntero();
        productoDAO.eliminar(id);
    }

    // === MÉTODOS DE VENTAS ===
    private static void nuevaVenta() {
        System.out.println("\n--- NUEVA VENTA ---");

        // En el proyecto real seria con autenticación en lugar de andar preguntando quien hace la venta
        System.out.println("Usuarios disponibles:");
        listarUsuarios();
        System.out.print("ID del cajero: ");
        int usuarioId = leerEntero();

        // Crear la venta (inicialmente con total 0)
        Venta venta = new Venta(usuarioId, 0.0);
        int ventaId = ventaDAO.insertar(venta);

        if (ventaId == -1) { //el -1 es el codigo que retorna error
            System.out.println("❌ Error al crear la venta");
            return;
        }

        double totalVenta = 0.0;
        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\nProductos disponibles:");
            listarProductos();

            System.out.print("ID del producto: ");
            int productoId = leerEntero();

            Producto producto = productoDAO.buscarPorId(productoId);
            if (producto == null) {
                System.out.println("❌ Producto no encontrado");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = leerEntero();

            if (cantidad > producto.getStock()) {
                System.out.println("❌ Stock insuficiente. Stock actual: " + producto.getStock());
                continue;
            }

            // Crear detalle de venta
            DetalleVenta detalle = new DetalleVenta(ventaId, productoId, cantidad, producto.getPrecio());
            detalleDAO.insertar(detalle);

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - cantidad);
            productoDAO.actualizar(producto);

            totalVenta += detalle.getSubtotal();
            System.out.printf("✅ Agregado: %s x%d = $%.2f%n",
                    producto.getNombre(), cantidad, detalle.getSubtotal());

            System.out.print("¿Agregar otro producto? (s/n): ");
            continuar = scanner.nextLine();
        }

        // Actualizar el total de la venta
        venta.setId(ventaId);
        venta.setTotal(totalVenta);
        ventaDAO.actualizar(venta);

        System.out.printf("🎉 Venta completada. Total: $%.2f%n", totalVenta);
    }

    private static void listarVentas() {
        System.out.println("\n--- LISTA DE VENTAS ---");
        List<Venta> ventas = ventaDAO.consultarTodos();

        if (ventas.isEmpty()) {
            System.out.println("📭 No hay ventas registradas");
        } else {
            System.out.printf("%-4s | %-10s | %-20s | %s%n", "ID", "CAJERO", "FECHA", "TOTAL");
            System.out.println("-".repeat(60));
            for (Venta v : ventas) {
                System.out.printf("%-4d | %-10d | %-20s | $%.2f%n",
                        v.getId(), v.getUsuarioId(), v.getFecha().toString().substring(0, 16), v.getTotal());
            }
        }
    }

    private static void verDetalleVenta() {
        listarVentas();
        System.out.print("\nIngrese ID de la venta: ");
        int ventaId = leerEntero();

        List<DetalleVenta> detalles = detalleDAO.consultarPorVenta(ventaId);

        if (detalles.isEmpty()) {
            System.out.println("❌ No se encontraron detalles para esa venta");
        } else {
            System.out.println("\n--- DETALLE DE VENTA ---");
            System.out.printf("%-10s | %-4s | %-10s | %s%n", "PRODUCTO", "CANT", "PRECIO", "SUBTOTAL");
            System.out.println("-".repeat(45));

            for (DetalleVenta d : detalles) {
                Producto p = productoDAO.buscarPorId(d.getProductoId());
                String nombreProducto = (p != null) ? p.getNombre() : "Producto " + d.getProductoId();
                System.out.printf("%-10s | %-4d | $%-9.2f | $%.2f%n",
                        nombreProducto, d.getCantidad(), d.getPrecioUnitario(), d.getSubtotal());
            }
        }
    }

    // === MÉTODOS AUXILIARES ===
    private static int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    private static double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("❌ Ingrese un número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }
}