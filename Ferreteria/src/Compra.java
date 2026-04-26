public class Compra implements DescuentoCompra{

    private Herramienta herramienta;
    private Cliente cliente;
    private double precioTotalCompra;
    private int cantidad;

    public Compra(Herramienta herramienta, Cliente cliente, double precioTotalCompra, int cantidad) {
        this.herramienta = herramienta;
        this.cliente = cliente;
        this.precioTotalCompra = precioTotalCompra;
        this.cantidad = cantidad;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrecioTotalCompra() {
        return precioTotalCompra;
    }

    public void setPrecioTotalCompra(double precioTotalCompra) {
        this.precioTotalCompra = precioTotalCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "herramienta=" + herramienta +
                ", cliente=" + cliente +
                ", precioTotalCompra=" + precioTotalCompra +
                ", cantidad=" + cantidad +
                '}';
    }

    public double aplicarDescuentoCompra(double total, int cantidadProductos) {
        if (cantidadProductos >= 3) {
            total -= herramienta.getPrecioVenta() * 0.40;  // Descuento de 40% en el tercer producto
        }
        return total;
    }

}
