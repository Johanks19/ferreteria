public class Alquiler  implements  DescuentoAlquiler{

    private Herramienta herramienta;
    private Cliente cliente;
    private int diasAlquiler;
    private int cantidad;
    private double precioTotal;
    private boolean activo;

    public Alquiler(Herramienta herramienta, Cliente cliente, int diasAlquiler, int cantidad, double precioTotal, boolean activo) {
        this.herramienta = herramienta;
        this.cliente = cliente;
        this.diasAlquiler = diasAlquiler;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.activo = activo;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramientas) {
        this.herramienta = herramienta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "herramienta=" + herramienta +
                ", cliente=" + cliente +
                ", diasAlquiler=" + diasAlquiler +
                ", cantidad=" + cantidad +
                ", precioTotal=" + precioTotal +
                ", activo=" + activo +
                '}';
    }

    public double aplicarDescuento(double precioTotal, long herramientasAlquiladas, int cantidad) {
        int herramientasConDescuento = 0;

        if (herramientasAlquiladas < 3) {
            herramientasConDescuento = Math.max(0, (int) (herramientasAlquiladas + cantidad) - 3);
        } else {
            herramientasConDescuento = cantidad;
        }

        double descuento = herramientasConDescuento * 0.20 * precioTotal / cantidad;
        return precioTotal - descuento;
    }

    public void finalizarAlquiler() {
        this.activo = false;
    }


}
