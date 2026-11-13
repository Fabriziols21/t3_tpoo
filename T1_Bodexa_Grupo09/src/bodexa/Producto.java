package bodexa;
import java.math.BigDecimal;
public class Producto {
    private final int codigo;
    private final String nombre;
    private final BigDecimal precio;
    private int stock;
    public Producto(int codigo, String nombre, BigDecimal precio, int stock) {
        if (codigo <= 0) throw new IllegalArgumentException("Codigo debe ser positivo");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre obligatorio");
        if (precio == null || precio.signum() <= 0 || precio.scale() > 2)
            throw new IllegalArgumentException("Precio invalido");
        if (stock < 0) throw new IllegalArgumentException("Stock no puede ser negativo");
        this.codigo = codigo; this.nombre = nombre.trim(); this.precio = precio; this.stock = stock;
    }
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void descontar(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser positiva");
        if (cantidad > stock) throw new IllegalArgumentException("Stock insuficiente");
        stock -= cantidad;
    }
    @Override public String toString() {
        return codigo + " | " + nombre + " | S/ " + precio + " | stock: " + stock;
    }
}
