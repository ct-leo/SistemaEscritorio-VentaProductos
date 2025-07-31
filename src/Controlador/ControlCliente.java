// Paquete donde se encuentra esta clase
package Controlador;

// Importación de clases del modelo y de las vistas
import Modelo.Cliente;
import Modelo.ConsultasCliente;
import Vista.frmCliente;
import Vista.frmMenu;

// Importación de clases para manejar eventos
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Libreria para ejecutar los botones de nuestro formulario

// Importación para mostrar cuadros de diálogo
import javax.swing.JOptionPane;

// Importación para manejar tablas
import javax.swing.table.DefaultTableModel;

// Clase ControlCliente que maneja las acciones de la vista frmCliente
public class ControlCliente implements ActionListener {

    // Atributos para acceder al modelo, consultas y las vistas
    private final Cliente modelo;
    private final ConsultasCliente consultas;
    private final frmCliente vista;
    private final frmMenu menu;

    // Constructor que inicializa los atributos y registra los botones al ActionListener
    public ControlCliente(Cliente modelo, ConsultasCliente consultas, frmCliente vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Se asignan los botones a escuchar eventos
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método para configurar la ventana al iniciar
    public void iniciar() {
        vista.setTitle("Cliente"); // Titulo de la ventana
        vista.setLocationRelativeTo(null); // Centrar ventana en pantalla
        cargarDatosTabla(); // Cargar datos existentes en la tabla
    }

    // Método que carga los datos de la base en la tabla
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Obtener modelo de tabla desde consultas
        vista.tblClientes.setModel(modeloTabla); // Asignar modelo a la tabla en la vista
    }

    // Método auxiliar para validar campos de texto
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código del cliente debe contener 7 caracteres.");
            return false;
        }
        if (vista.txtTelefono.getText().trim().length() != 9) {
            JOptionPane.showMessageDialog(null, "El telefono debe contener 9 caracteres.");
            return false;
        }
        return true;
    }

    // Método que se ejecuta al presionar un botón registrado
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción del botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return;
            }
            modelo.setNombre(vista.txtNombre.getText()); // Asignar nombre
            modelo.setApellido(vista.txtApellido.getText()); // Asignar apellido
            modelo.setCorreo(vista.txtCorreo.getText()); // Asignar correo
            modelo.setDireccion(vista.txtDireccion.getText()); // Asignar dirección
            modelo.setCod_cliente(vista.txtCodigo.getText().trim()); // Asignar codigo
            modelo.setTelefono(vista.txtTelefono.getText().trim()); // Asignar telefono

            // Guardar en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción del botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return;
            }
            modelo.setNombre(vista.txtNombre.getText()); // Asignar nombre
            modelo.setApellido(vista.txtApellido.getText()); // Asignar apellido
            modelo.setCorreo(vista.txtCorreo.getText()); // Asignar correo
            modelo.setDireccion(vista.txtDireccion.getText()); // Asignar dirección
            modelo.setCod_cliente(vista.txtCodigo.getText().trim()); // Asignar codigo
            modelo.setTelefono(vista.txtTelefono.getText().trim()); // Asignar telefono

            // Modificar en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción del botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_cliente(Integer.parseInt(vista.txtId1.getText())); // Obtener ID desde el formulario
            // Eliminar en la base de datos
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación.");
            }
        }

        // Acción del botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_cliente(vista.txtCodigo.getText()); // Asignar código para buscar

            // Buscar en la base de datos
            if (consultas.Buscar(modelo)) {
                // Si se encuentra el cliente, se llenan los campos del formulario
                vista.txtId1.setText(String.valueOf(modelo.getId_cliente()));
                vista.txtCodigo.setText(modelo.getCod_cliente());
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtApellido.setText(modelo.getApellido());
                vista.txtCorreo.setText(modelo.getCorreo());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtDireccion.setText(modelo.getDireccion());

                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!");
                cargarDatosTabla(); // Refrescar tabla
            }
        }

        // Acción del botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Limpiar todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtNombre.setText(null);
            vista.txtApellido.setText(null);
            vista.txtCorreo.setText(null);
            vista.txtTelefono.setText(null);
            vista.txtDireccion.setText(null);

            // Limpiar la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblClientes.getModel();
            modelo.setRowCount(0); // Eliminar todas las filas
        }

        // Acción del botón Menu
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cerrar formulario actual
            menu.setVisible(true); // Mostrar menú principal
        }
    }
}
