package bodexa;
import java.math.BigDecimal;
public class Venta {
    private final int codigoProducto;
    private final String nombreProducto;
    private final int cantidad;
    private final BigDecimal precioUnitario;
    public Venta(int codigoProducto, String nombreProducto, int cantidad, BigDecimal precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    public BigDecimal total() { return precioUnitario.multiply(BigDecimal.valueOf(cantidad)); }
    @Override public String toString() { return nombreProducto + " x" + cantidad + ": S/ " + total(); }
}
