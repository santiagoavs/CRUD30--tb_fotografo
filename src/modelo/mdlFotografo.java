package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import vista.jfrFotografo;



public class mdlFotografo {
    
    private String nombre;
    private int edad;
    private double peso;
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void Guardar() {

        Connection conexion = ClaseConexion.getConexion();
        try {

            String sql = "INSERT INTO tbFotografo(UUID, Nombre, Edad, Peso, Correo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre());
            pstmt.setInt(3, getEdad());
            pstmt.setDouble(4, getPeso());
            pstmt.setString(5, getCorreo());

            
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre", "Edad", "Peso", "Correo"});
        try {
            String query = "SELECT * FROM tbFotografo";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("UUID"), 
                    rs.getString("nombre"), 
                    rs.getInt("edad"), 
                    rs.getDouble("peso"),
                    rs.getString("correo")});
            }
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "delete from tbFotografo where UUID = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbFotografo set nombre= ?, edad = ?, peso = ?, correo = ? where UUID = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setDouble(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre", "Edad", "Peso", "Correo"});
        try {
            String sql = "SELECT * FROM tbFotografo WHERE nombre LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("UUID"), rs.getString("nombre"), rs.getInt("edad"), rs.getDouble("peso"), rs.getString("correo")});
            }

            
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
    public void limpiar(jfrFotografo vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
    
    public void cargarDatosTabla(jfrFotografo vista) {
        int filaSeleccionada = vista.tbFotografo.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbFotografo.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.tbFotografo.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.tbFotografo.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.tbFotografo.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.tbFotografo.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
    
}
