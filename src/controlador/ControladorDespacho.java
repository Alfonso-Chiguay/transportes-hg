
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.Despacho;

public class ControladorDespacho {
    
    public void ingresarDespacho(JFrame ventana,Despacho despacho){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "INSERT INTO DESPACHO VALUES "
                    + "(concat(DATE_FORMAT('"+despacho.getFecha()+"','%d%m%y'),(SELECT CODIGO_VENDEDOR FROM vendedor WHERE NOMBRE = '"+despacho.getVendedor().getNombre()+"'),(SELECT ID_CHOFER FROM chofer WHERE NOMBRE = '"+despacho.getChofer().getNombre()+"'),'"+despacho.getHoraSalida()+"')"
                    + ",(SELECT CODIGO_VENDEDOR FROM vendedor WHERE NOMBRE = '"+despacho.getVendedor().getNombre()+"')"
                    + ",(SELECT ID_CHOFER FROM chofer WHERE NOMBRE = '"+despacho.getChofer().getNombre()+"')"
                    + ",'"+despacho.getCentroCosto()+"'"
                    + ",'"+despacho.getVehiculo()+"'"
                    + ",'"+despacho.getHoraSalida()+"'"
                    + ",UPPER('"+despacho.getDestino()+"')"
                    + ",STR_TO_DATE('"+despacho.getFecha()+"','%d-%m-%y')"
                    + ","+despacho.isRealizado()
                    +","+despacho.getKm_ini()
                    +","+despacho.getKm_fin()
                    +","+despacho.isDocEntregado()
                    + ");";
            System.out.println("Query: "+query);
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(ventana, "Despacho correctamente ingresado");
            ventana.dispose();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
        }
    }
    
    public void buscarPorFechaLibre(JFrame ventana, String fecha, JTable tabla){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT D.ID_DESPACHO, D.HORA_SALIDA, C.NOMBRE, V.NOMBRE, D.VEHICULO, D.DESTINO "
                    + "FROM DESPACHO D JOIN CHOFER C ON D.ID_CHOFER = C.ID_CHOFER "
                    + "JOIN VENDEDOR V ON D.CODIGO_VENDEDOR = V.CODIGO_VENDEDOR "
                    + "WHERE D.FECHA = STR_TO_DATE('"+fecha+"','%d-%m-%y') AND REALIZADO = 0;";
            System.out.println(query);
            ResultSet r=st.executeQuery(query);
            DefaultTableModel table = (DefaultTableModel) tabla.getModel();
            table.setRowCount(0);
            while (r.next()){
                Object[] fila = { r.getString(1),
                                  r.getString(2),
                                  r.getString(3),
                                  r.getString(4),
                                  r.getString(5),
                                  r.getString(6)                       
                                
                };
                table.addRow(fila);
            }
            
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, e.getMessage());
                    
        }        
    }
    
    public void buscarPorFecha(JFrame ventana, String fecha, JTable tabla){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT LPAD(D.HORA_SALIDA,5,'0'), C.NOMBRE, V.NOMBRE, D.VEHICULO, D.ID_DESPACHO "
                    + "FROM DESPACHO D JOIN CHOFER C ON D.ID_CHOFER = C.ID_CHOFER "
                    + "JOIN VENDEDOR V ON D.CODIGO_VENDEDOR = V.CODIGO_VENDEDOR "
                    + "WHERE D.FECHA = STR_TO_DATE('"+fecha+"','%d-%m-%y') ORDER BY LPAD(D.HORA_SALIDA,5,'0')";
            System.out.println(query);
            ResultSet r=st.executeQuery(query);
            DefaultTableModel table = (DefaultTableModel) tabla.getModel();
            
            table.setRowCount(0);
            while (r.next()){
                Object[] fila = { r.getString(1),
                                  r.getString(2),
                                  r.getString(3),
                                  r.getString(4),
                                  r.getString(5)                                                        
                                
                };
                table.addRow(fila);
            }
            
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, e.getMessage());
                    
        }        
    }
    
    public void buscarDespachoSinDocumento(JFrame ventana,JTable tabla){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT D.ID_DESPACHO, DATE_FORMAT(D.FECHA,'%d-%m-%Y'), C.NOMBRE, V.NOMBRE, D.VEHICULO, D.DESTINO, D.DOC_ENTREGADO "
                    + "FROM DESPACHO D JOIN CHOFER C ON D.ID_CHOFER = C.ID_CHOFER "
                    + "JOIN VENDEDOR V ON D.CODIGO_VENDEDOR = V.CODIGO_VENDEDOR "
                    + "WHERE DOC_ENTREGADO = 0 ORDER BY DATE_FORMAT(D.FECHA,'%d-%m-%Y');";
            System.out.println(query);
            ResultSet r=st.executeQuery(query);
            DefaultTableModel table = (DefaultTableModel) tabla.getModel();
            table.setRowCount(0);
            while (r.next()){
                Object[] fila = { r.getString(1),
                                  r.getString(2),
                                  r.getString(3),
                                  r.getString(4),
                                  r.getString(5),
                                  r.getString(6),
                                  r.getBoolean(7)
                                
                };
                table.addRow(fila);
            }
            
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, e.getMessage());
                    
        }        
    }
    
    public void actualizarEntregaDocumento(JFrame ventana, ArrayList<String> id){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query;
            for(String iD: id){
                query = "UPDATE DESPACHO SET DOC_ENTREGADO = 1 WHERE ID_DESPACHO = '"+iD+"';";
                st.executeUpdate(query);
            }
            JOptionPane.showMessageDialog(ventana, "DATOS ACTUALIZADOS");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "ERROR: "+e.getMessage());
        }
    }
    
    
    
    public void ingresarKilometraje(JFrame ventana, String id, int km_salida, int km_llegada){
        if(km_llegada > km_salida){
        
            Conexion conexion = new Conexion();
            try {
                Connection con = conexion.getConnection();
                Statement st = con.createStatement();
                String query = "UPDATE DESPACHO SET REALIZADO = 1, KM_INI ="+km_salida+", KM_FIN="+km_llegada
                        +" WHERE ID_DESPACHO = '"+id+"';";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(ventana, "KILOMETRAJE ACTUALIZADO");
                

            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(ventana, e.getMessage());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(ventana, "EL KILOMETRAJE DE SALIDA DEBE SER MENOR AL DE LLEGADA");
        }
    }
    
    public ArrayList<String> verInforDespacho(String hora, String fecha, String chofer, String vendedor){
        Conexion conexion = new Conexion();
        ArrayList<String> info = new ArrayList<>();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT D.HORA_SALIDA, C.NOMBRE, D.VEHICULO, V.NOMBRE, D.DESTINO, D.ID_DESPACHO "
                         + "FROM DESPACHO D JOIN CHOFER C ON D.ID_CHOFER = C.ID_CHOFER "
                         + "JOIN VENDEDOR V ON V.CODIGO_VENDEDOR = D.CODIGO_VENDEDOR "
                         + "WHERE LPAD(D.HORA_SALIDA,5,'0') = LPAD('"+hora+"',5,'0') "
                         + "AND D.FECHA = STR_TO_DATE('"+fecha+"','%d-%m-%y') "
                         + "AND C.NOMBRE = '"+chofer+"' AND V.NOMBRE = '"+vendedor+"';";
            ResultSet rs =st.executeQuery(query);
            while(rs.next()){
                info.add(rs.getString(1));
                info.add(rs.getString(2));
                info.add(rs.getString(3));
                info.add(rs.getString(4));
                info.add(rs.getString(5));
                info.add(rs.getString(6));
                
            }
            return info;
            
        }
        catch (Exception e) {
                                
            return info;
        }
    }
    
    public void actualizarDestinoVehiculo(JFrame ventana, String id, String destino, String vehiculo, int chofer){
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "UPDATE DESPACHO SET DESTINO = UPPER('"+destino+"'), VEHICULO = '"+vehiculo+"', ID_CHOFER = "+chofer
                    + " WHERE ID_DESPACHO = '"+id+"'";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(ventana, "DATOS ACTUALIZADOS");
            
            ventana.dispose();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: "+e.getMessage());
        }
    }
    
    public void listadoKilometraje(JFrame ventana,String vehiculo, String fechaDesde, String fechaHasta, JTable tabla){
  
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.getConnection();
            Statement st = con.createStatement();
            String query = "SELECT DATE_FORMAT(D.FECHA,'%d/%m/%Y'), LPAD(d.hora_salida,5,'0'),c.nombre, d.km_ini,d.km_fin, d.vehiculo "
                            + "FROM despacho d JOIN chofer c ON d.id_chofer = c.id_chofer "
                            + "WHERE FECHA >= STR_TO_DATE('"+fechaDesde+"','%d-%m-%y') AND FECHA <= STR_TO_DATE('"+fechaHasta+"','%d-%m-%y') AND "
                            + "vehiculo='"+vehiculo+"' "
                            + "ORDER BY  FECHA ASC, LPAD(HORA_SALIDA,5,'0'),KM_INI ASC, KM_FIN ASC;";           
            System.out.println(query);
            DefaultTableModel table = (DefaultTableModel) tabla.getModel();
            table.setRowCount(0);
            ResultSet r = st.executeQuery(query);
            while (r.next()){
                Object fila[]={
                            r.getString(1),
                            r.getString(2),
                            r.getString(3),
                            r.getInt(4),
                            r.getInt(5)
                };
                table.addRow(fila);
                               
            }
                    
                      
            
            
        } 
        catch (Exception e) {
            
        }
    }
}
