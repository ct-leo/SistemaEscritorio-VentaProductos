// Paquete donde se encuentra esta clase
package Controlador;

// Importación de clases del modelo y de las vistas
import Modelo.Detalle_Pedido;
import Modelo.ConsultasDetalle;
import Vista.frmDetalle;
import Vista.frmMenu;
// Importación de clases para manejar eventos
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Libreria para ejecutar los botones de nuestro formulario

// Importación para mostrar cuadros de diálogo
import javax.swing.JOptionPane;

// Importación para manejar tablas
import javax.swing.table.DefaultTableModel;

// Clase ControlCliente que maneja las acciones de la vista frmDetalle
public class ControlDetalle implements ActionListener {

    // Atributos del controlador: modelo, consultas, vista y menú principal
    private final Detalle_Pedido modelo;
    private final ConsultasDetalle consultas;
    private final frmDetalle vista;
    private final frmMenu menu;

    // Constructor que enlaza el modelo, consultas, vista y menú, y registra los listeners
    public ControlDetalle(Detalle_Pedido modelo, ConsultasDetalle consultas, frmDetalle vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Asociar eventos de los botones con el método actionPerformed
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método para inicializar la vista
    public void iniciar() {
        vista.setTitle("Detalle de pedido"); // Establece el título de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        cargarDatosTabla(); // Carga los datos en la tabla al iniciar
    }

    // Método para cargar los datos del modelo a la tabla en la vista
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Llama al método listar del modelo
        vista.tblDetalle.setModel(modeloTabla); // Establece el modelo de la tabla
    }

    // Método auxiliar para validar campos de texto
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código de pedido debe contener 7 caracteres.");
            return false;
        }
        if (vista.txtProducto.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código de producto debe contener 7 caracteres.");
            return false;
        }
        if (vista.txtCliente.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código de cliente debe contener 7 caracteres.");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return;
            }

            // Validación y conversión de campos numéricos con manejo de errores
            try {
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
                modelo.setPrecio_unitario(Double.parseDouble(vista.txtPrecio.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }

            modelo.setCod_pedido(vista.txtCodigo.getText().trim());
            modelo.setCod_producto(vista.txtProducto.getText().trim());
            modelo.setCod_cliente(vista.txtCliente.getText().trim());
            // Lógica de guardado
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Detalle guardado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo guardar el detalle.");
            }
        }

        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return;
            }

            // Validación y conversión de campos numéricos con manejo de errores
            try {
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
                modelo.setPrecio_unitario(Double.parseDouble(vista.txtPrecio.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }

            modelo.setCod_pedido(vista.txtCodigo.getText().trim());
            modelo.setCod_producto(vista.txtProducto.getText().trim());
            modelo.setCod_cliente(vista.txtCliente.getText().trim());

            // Lógica de modificación
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Detalle modificado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo modificar el detalle.");
            }
        }

        // Acción del botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_detalle_ped(Integer.parseInt(vista.txtId1.getText())); // Obtener el ID del registro a eliminar
            // Eliminar en la base de datos
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Detalle eliminado exitosamente.");
                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo eliminar el detalle.");
            }
        }

        // Acción del botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_pedido(vista.txtCodigo.getText()); // Asignar código para buscar
            // Buscar en la base de datos
            if (consultas.Buscar(modelo)) {
                // Mostrar datos en los campos de la vista
                vista.txtId1.setText(String.valueOf(modelo.getId_detalle_ped()));
                vista.txtCodigo.setText(modelo.getCod_pedido());
                vista.txtProducto.setText(modelo.getCod_producto());
                vista.txtCliente.setText(modelo.getCod_cliente());
                vista.txtCantidad.setText(String.valueOf(modelo.getCantidad()));
                vista.txtPrecio.setText(String.valueOf(modelo.getPrecio_unitario()));

                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Detalle no encontrado!");
                cargarDatosTabla(); // Actualizar tabla
            }
        }

        // Acción del botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Limpiar todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtProducto.setText(null);
            vista.txtCliente.setText(null);
            vista.txtCantidad.setText(null);
            vista.txtPrecio.setText(null);
            DefaultTableModel modelo = (DefaultTableModel) vista.tblDetalle.getModel();
            modelo.setRowCount(0); // Limpia la tabla
        }

        // Acción del botón Menu
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cierra la ventana actual
            menu.setVisible(true); // Muestra el menú principal
        }
    }
}
