package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlFotografo;
import vista.jfrFotografo;


public class ctrlFotografo implements MouseListener, KeyListener {
    
    private mdlFotografo modelo;
    private jfrFotografo vista;
    
    public ctrlFotografo(mdlFotografo modelo, jfrFotografo vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbFotografo.addMouseListener(this);

        modelo.Mostrar(vista.tbFotografo);
} 

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo(vista.txtCorreo.getText());
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbFotografo);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbFotografo);
                modelo.Mostrar(vista.tbFotografo);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    modelo.setNombre(vista.txtNombre.getText());
                    modelo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso(Double.parseDouble(vista.txtPeso.getText()));
                    modelo.setCorreo(vista.txtCorreo.getText());
                    modelo.Actualizar(vista.tbFotografo);
                    modelo.Mostrar(vista.tbFotografo);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbFotografo) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbFotografo, vista.txtBuscar);
        }
    }
}
