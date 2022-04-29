package udl.manuel.gastos.entidades;

public class Gasto {
    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;

    public Gasto(int id, String nombre, int cantidad, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString(){
        return "Categoria: "+categoria + " Nombre: "+ nombre + " $ "+ cantidad;
    }
}
