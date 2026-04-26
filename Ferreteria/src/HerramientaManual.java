public class HerramientaManual extends Herramienta{

    public HerramientaManual(String nombreHerramienta, String marca, String id, int cantidad, String tipo, String funcion, double precioVenta, double precioAlquilerDia, boolean alquilado) {
        super(nombreHerramienta, marca, id, cantidad, tipo, funcion, precioVenta, precioAlquilerDia, alquilado);
    }
    public void actualizarInventario(int cantidad) {
        this.cantidad += cantidad;
    }


}
