package modelo;

public class Despacho {
    private String idDespacho;
    private Vendedor vendedor;
    private String centroCosto;
    private String fecha;
    private Chofer chofer;
    private String vehiculo;
    private String horaSalida;
    private String destino;
    private boolean realizado;
    private int km_ini;
    private int km_fin;
    private boolean docEntregado;
    
    public Despacho(){
        this.idDespacho="";
        this.vendedor= new Vendedor();
        this.centroCosto="";
        this.fecha="";
        this.chofer=new Chofer();
        this.vehiculo="";
        this.horaSalida="";
        this.destino="";
        this.realizado = false;
        this.km_ini=0;
        this.km_fin=0;
        this.docEntregado=false;
    }

    public String getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(String idDespacho) {
        this.idDespacho = idDespacho;
    }
    
    

    public boolean isDocEntregado() {
        return docEntregado;
    }

    public void setDocEntregado(boolean docEntregado) {
        this.docEntregado = docEntregado;
    }
    
    

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getKm_ini() {
        return km_ini;
    }

    public void setKm_ini(int km_ini) {
        this.km_ini = km_ini;
    }

    public int getKm_fin() {
        return km_fin;
    }

    public void setKm_fin(int km_fin) {
        this.km_fin = km_fin;
    }

    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
       
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
}
