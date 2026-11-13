package bodexa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Prototipo didactico de la Entrega 1, no reemplaza el proyecto final. */
public class Main {
    private final List<Producto> productos = new ArrayList<>();
    private final List<Venta> ventas = new ArrayList<>();
    public static void main(String[] args) { new Main().iniciar(); }
    private void iniciar() {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== BODEXA / PROTOTIPO T1 ===");
            System.out.println("1 Registrar producto | 2 Listar | 3 Buscar | 4 Vender | 5 Alertas | 0 Salir");
            try {
                System.out.print("Opcion: ");
                int opcion = Integer.parseInt(entrada.nextLine().trim());
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Codigo numerico: ");
                        int codigo = Integer.parseInt(entrada.nextLine().trim());
                        System.out.print("Nombre: ");
                        String nombre = entrada.nextLine();
                        System.out.print("Precio: ");
                        BigDecimal precio = new BigDecimal(entrada.nextLine().trim());
                        System.out.print("Stock: ");
                        int stock = Integer.parseInt(entrada.nextLine().trim());
                        registrar(new Producto(codigo, nombre, precio, stock));
                        System.out.println("Producto registrado correctamente.");
                    }
                    case 2 -> listar();
                    case 3 -> {
                        System.out.print("Codigo: ");
                        System.out.println(buscar(Integer.parseInt(entrada.nextLine().trim())));
                    }
                    case 4 -> {
                        System.out.print("Codigo: ");
                        int codigo = Integer.parseInt(entrada.nextLine().trim());
                        System.out.print("Unidades: ");
                        int cantidad = Integer.parseInt(entrada.nextLine().trim());
                        Venta v = vender(codigo, cantidad);
                        System.out.println("Venta registrada. Total S/ " + v.total());
                    }
                    case 5 -> {
                        for (Producto p : productos) if (p.getStock() < 5) System.out.println("ALERTA: " + p);
                    }
                    case 0 -> { System.out.println("Fin del programa."); return; }
                    default -> System.out.println("Opcion inexistente.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }
    private void registrar(Producto producto) {
        for (Producto p : productos) if (p.getCodigo() == producto.getCodigo())
            throw new IllegalArgumentException("Codigo duplicado");
        productos.add(producto);
    }
    private Producto buscar(int codigo) {
        for (Producto p : productos) if (p.getCodigo() == codigo) return p;
        throw new IllegalArgumentException("Producto no encontrado");
    }
    private List<Producto> buscar(String texto) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos)
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) encontrados.add(p);
        return encontrados;
    }
    private void listar() {
        if (productos.isEmpty()) System.out.println("Sin productos registrados.");
        else productos.forEach(System.out::println);
    }
    private Venta vender(int codigo, int cantidad) {
        Producto producto = buscar(codigo);
        producto.descontar(cantidad);
        Venta v = new Venta(producto.getCodigo(), producto.getNombre(), cantidad, producto.getPrecio());
        ventas.add(v);
        if (producto.getStock() < 5) System.out.println("AVISO: stock inferior a cinco unidades.");
        return v;
    }
}
