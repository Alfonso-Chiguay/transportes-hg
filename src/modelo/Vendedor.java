package modelo;

public class Vendedor {
    private int codigoVendedor;
    private String nombre;
    private String centroCosto;
    
    public Vendedor(){
        this.codigoVendedor=0;
        this.nombre="";
        this.centroCosto="";
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }
    
    
}
