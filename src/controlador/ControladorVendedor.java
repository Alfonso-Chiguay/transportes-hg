
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Vendedor;

public class ControladorVendedor {

    public int ingresarVendedor(JFrame ventana, Vendedor vendedor){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            
            String query = "INSERT INTO VENDEDOR VALUES ("
                    + vendedor.getCodigoVendedor()
                    + ",UPPER('"+vendedor.getNombre()+"')"
                    + ",'"+vendedor.getCentroCosto()+"'"
                    + ");";
            System.out.println("Query: "+query);
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(ventana, "VENDEDOR CREADO");
            return 1;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return 0;
        }
    }
    
    public ArrayList<String> listarVendedor(JFrame ventana){
        Conexion conexion = new Conexion();
        ArrayList<String> vendedores = new ArrayList<>();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT NOMBRE FROM VENDEDOR ORDER BY NOMBRE;";
            ResultSet r = st.executeQuery(query);
            
            while(r.next()){
                vendedores.add(r.getString(1));
            }
            return vendedores;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return vendedores;
        }      
        
    }
    
    public ArrayList<String> listarCC(JFrame ventana){
        Conexion conexion = new Conexion();
        ArrayList<String> ccs = new ArrayList<>();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT DISTINCT CENTRO_COSTO FROM VENDEDOR;";
            ResultSet r = st.executeQuery(query);
            
            while(r.next()){
                ccs.add(r.getString(1));
            }
            return ccs;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return ccs;
        }  
    }
    
    public String centroCostoPorVendedor(JFrame ventana,String vendedor){
        String cc = "";
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT CENTRO_COSTO FROM VENDEDOR WHERE NOMBRE = '"+vendedor+"';";
            ResultSet r = st.executeQuery(query);
            while(r.next())
                cc=r.getString(1);
            return cc;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return cc;
        }
    }
}
