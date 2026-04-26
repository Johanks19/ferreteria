public class HerramientaElectrica extends Herramienta{

    private double potenciaMotor;
    private boolean inalambrico;

    public HerramientaElectrica(String nombreHerramienta, String marca, String id, int cantidad, String tipo, String funcion, long precioVenta, long precioAlquilerDia, boolean alquilado, double potenciaMotor, boolean inalambrico) {
        super(nombreHerramienta, marca, id, cantidad, tipo, funcion, precioVenta, precioAlquilerDia, alquilado);
        this.potenciaMotor = potenciaMotor;
        this.inalambrico = inalambrico;
    }

    public double getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(double potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    @Override

    public void actualizarInventario(int cantidad) {
        this.cantidad += cantidad;
    }

    @Override
    public String toString() {
        return super.toString() +
                "potenciaMotor=" + potenciaMotor +
                ", inalambrico=" + inalambrico +
                "} ";
    }
}
