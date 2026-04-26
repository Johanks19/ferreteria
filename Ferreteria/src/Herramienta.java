abstract class Herramienta {

protected String nombreHerramienta;
protected String marca;
protected String id;
protected int cantidad;
protected String tipo;
protected String funcion;
protected double precioVenta;
protected double precioAlquilerDia;
protected boolean alquilado;

    public Herramienta(String nombreHerramienta, String marca, String id, int cantidad, String tipo, String funcion, double precioVenta, double precioAlquilerDia, boolean alquilado) {
        this.nombreHerramienta = nombreHerramienta;
        this.marca = marca;
        this.id = id;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.funcion = funcion;
        this.precioVenta = precioVenta;
        this.precioAlquilerDia = precioAlquilerDia;
        this.alquilado = alquilado;
    }

    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioAlquilerDia() {
        return precioAlquilerDia;
    }

    public void setPrecioAlquilerDia(double precioAlquilerDia) {
        this.precioAlquilerDia = precioAlquilerDia;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Herramienta{" +
                "nombreHerramienta='" + nombreHerramienta + '\'' +
                ", marca='" + marca + '\'' +
                ", id='" + id + '\'' +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                ", funcion='" + funcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", precioAlquilerDia=" + precioAlquilerDia +
                ", alquilado=" + alquilado +
                '}';
    }


    public abstract void actualizarInventario(int cantidad);


}
