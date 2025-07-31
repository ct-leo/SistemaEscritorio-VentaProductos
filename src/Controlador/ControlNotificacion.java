// Paquete al que pertenece la clase
package Controlador;

// Importación de clases del modelo y la vista
import Modelo.Notificacion;
import Modelo.ConsultasNotificacion;
import Vista.frmNotificacion;
import Vista.frmMenu;

// Importaciones necesarias para eventos y GUI
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Librería para manejar eventos de botones
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

// Clase ControlNotificacion que implementa ActionListener para controlar eventos
public class ControlNotificacion implements ActionListener {

    // Atributos para el modelo, consultas, la vista de notificación y el menú
    private final Notificacion modelo;
    private final ConsultasNotificacion consultas;
    private final frmNotificacion vista;
    private final frmMenu menu;

    // Constructor que inicializa atributos y asocia botones a eventos
    public ControlNotificacion(Notificacion modelo, ConsultasNotificacion consultas, frmNotificacion vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Se agregan listeners para los botones del formulario
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método que configura la ventana al iniciarse
    public void iniciar() {
        vista.setTitle("Notificacion"); // Establece el título de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        cargarDatosTabla(); // Carga los datos en la tabla
    }

    // Método para cargar los registros de la base de datos en la tabla
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Obtiene el modelo de tabla con los datos
        vista.tblNotificacion.setModel(modeloTabla); // Establece el modelo en la tabla
    }

    // Método para validar que el código del proveedor tenga 7 caracteres
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código del proveedor debe contener 7 caracteres.");
            return false;
        }
        return true;
    }

    // Método que maneja las acciones realizadas sobre los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción cuando se presiona el botón Guardar
        if (e.getSource() == vista.btnGuardar) {

            if (!validarCampos()) {
                return; // Si la validación falla, se detiene la ejecución
            }

            // Se asignan los datos del formulario al modelo
            modelo.setCod_proveedor(vista.txtCodigo.getText().trim());
            modelo.setAsunto(vista.txtAsunto.getText());
            modelo.setMensaje(vista.txtMensaje.getText());

            // Se obtiene la fecha del selector de fecha
            java.util.Date fechaSelec = vista.jDateChooser1.getDate();
            if (fechaSelec != null) {
                java.sql.Date fechaSQL = new java.sql.Date(fechaSelec.getTime());
                modelo.setFecha(fechaSQL);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha!");
                return;
            }

            modelo.setEstado(vista.txtEstado.getText());

            // Se guarda el registro en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                cargarDatosTabla(); // Se actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción cuando se presiona el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return;
            }

            // Se actualizan los datos del modelo desde la vista
            modelo.setCod_proveedor(vista.txtCodigo.getText().trim());
            modelo.setAsunto(vista.txtAsunto.getText());
            modelo.setMensaje(vista.txtMensaje.getText());

            // Se obtiene la fecha del selector
            java.util.Date fechaSelec = vista.jDateChooser1.getDate();
            if (fechaSelec != null) {
                java.sql.Date fechaSQL = new java.sql.Date(fechaSelec.getTime());
                modelo.setFecha(fechaSQL);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha!");
                return;
            }

            modelo.setEstado(vista.txtEstado.getText());

            // Se actualiza el registro en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción cuando se presiona el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_noti(Integer.parseInt(vista.txtId1.getText())); // Se obtiene el ID a eliminar
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación.");
            }
        }

        // Acción cuando se presiona el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_proveedor(vista.txtCodigo.getText()); // Se asigna el código para buscar
            if (consultas.Buscar(modelo)) {
                // Si se encuentra el registro, se muestran sus datos
                vista.txtId1.setText(String.valueOf(modelo.getId_noti()));
                vista.txtCodigo.setText(modelo.getCod_proveedor());
                vista.txtAsunto.setText(modelo.getAsunto());
                vista.txtMensaje.setText(modelo.getMensaje());

                // Se carga la fecha en el selector
                if (modelo.getFecha() != null) {
                    vista.jDateChooser1.setDate(new java.util.Date(modelo.getFecha().getTime()));
                } else {
                    vista.jDateChooser1.setDate(null);
                }

                vista.txtEstado.setText(modelo.getEstado());
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!");
                cargarDatosTabla();
            }
        }

        // Acción cuando se presiona el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Se limpian todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtAsunto.setText(null);
            vista.txtMensaje.setText(null);
            vista.jDateChooser1.setDate(null);
            vista.txtEstado.setText(null);

            // Se limpia la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblNotificacion.getModel();
            modelo.setRowCount(0);
        }

        // Acción cuando se presiona el botón Menú
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cierra la ventana actual
            menu.setVisible(true); // Muestra el menú principal
        }
    }
}
