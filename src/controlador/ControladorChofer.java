
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ControladorChofer {
    
    public int ingresarChofer(JFrame ventana,String nombre){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "INSERT INTO CHOFER VALUES (NULL, UPPER('"+nombre+"'));";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(ventana, "CHOFER INGRESADO");
            
            return 1;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return 0;
        }
    }
    
    public ArrayList<String> listarChofer(JFrame ventana){
        Conexion conexion = new Conexion();
        ArrayList<String> choferes = new ArrayList<>();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT NOMBRE, ID_CHOFER FROM CHOFER ORDER BY ID_CHOFER;";
            ResultSet r = st.executeQuery(query);
            
            while(r.next()){
                choferes.add(r.getString(1));
            }
            return choferes;
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
            return choferes;
        }  
    }
    
}
