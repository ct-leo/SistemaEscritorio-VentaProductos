// Paquete al que pertenece la clase
package Controlador;

// Importación de clases del modelo y la vista
import Modelo.Marca;
import Modelo.ConsultasMarca;
import Vista.frmMarca;
import Vista.frmMenu;

// Importación de eventos y utilidades para la interfaz
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

// Clase ControlMarca que implementa ActionListener para manejar eventos de botones
public class ControlMarca implements ActionListener {

    // Atributos del modelo, consultas, vista de marca y vista del menú
    private final Marca modelo;
    private final ConsultasMarca consultas;
    private final frmMarca vista;
    private final frmMenu menu;

    // Constructor que recibe los objetos necesarios e inicializa los listeners de botones
    public ControlMarca(Marca modelo, ConsultasMarca consultas, frmMarca vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Se asocian los botones del formulario con el manejador de eventos
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método para configurar e iniciar la ventana
    public void iniciar() {
        vista.setTitle("Marca"); // Establece el título de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        cargarDatosTabla(); // Carga los datos en la tabla al iniciar
    }

    // Método para cargar los datos de las marcas en la tabla de la vista
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Obtiene los datos desde la base de datos
        vista.tblMarca.setModel(modeloTabla); // Establece el modelo de la tabla con los datos
    }

    // Método auxiliar para validar campos de texto
    private boolean validarCampos() {
        if (vista.txtProveedor.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código de pedido debe contener 7 caracteres.");
            return false;
        }
        return true;
    }

    // Método que maneja las acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción cuando se presiona el botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return;
            }
            modelo.setNombre(vista.txtNombre.getText()); // Se asigna el nombre de la marca
            modelo.setPais(vista.txtPais.getText()); // Se asigna el país
            modelo.setCod_proveedor(vista.txtProveedor.getText().trim()); // Se asigna el código del proveedor

            // Se guarda la marca en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Marca guardada exitosamente."); // Mensaje de éxito
                cargarDatosTabla(); // Se actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo guardar la marca."); // Mensaje de error
            }
        }

        // Acción cuando se presiona el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return;
            }
            modelo.setNombre(vista.txtNombre.getText()); // Se asigna el nombre de la marca
            modelo.setPais(vista.txtPais.getText()); // Se asigna el país
            modelo.setCod_proveedor(vista.txtProveedor.getText().trim()); // Se asigna el código del proveedor

            // Se modifica el registro en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Marca modificada exitosamente."); // Mensaje de éxito
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo modificar la marca."); // Mensaje de error
            }
        }

        // Acción cuando se presiona el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_marca(Integer.parseInt(vista.txtId1.getText())); // Se obtiene el ID para eliminar
            // Se elimina el registro de la base de datos
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Marca eliminada exitosamente."); // Mensaje de éxito
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se pudo eliminar la marca."); // Mensaje de error
            }
        }

        // Acción cuando se presiona el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setNombre(vista.txtNombre.getText()); // Se establece el nombre para buscar
            // Si se encuentra la marca, se cargan los datos en el formulario
            if (consultas.Buscar(modelo)) {
                vista.txtId1.setText(String.valueOf(modelo.getId_marca())); // Muestra el ID
                vista.txtNombre.setText(modelo.getNombre()); // Muestra el nombre
                vista.txtPais.setText(modelo.getPais()); // Muestra el país
                vista.txtProveedor.setText(modelo.getCod_proveedor()); // Muestra el proveedor
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Marca no encontrada!"); // Mensaje de error
                cargarDatosTabla(); // Aun así se actualiza la tabla
            }
        }

        // Acción cuando se presiona el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            vista.txtId1.setText(null); // Limpia el campo ID
            vista.txtNombre.setText(null); // Limpia el campo nombre
            vista.txtPais.setText(null); // Limpia el campo país
            vista.txtProveedor.setText(null); // Limpia el campo proveedor
            // Limpia la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblMarca.getModel();
            modelo.setRowCount(0); // Establece la tabla sin filas
        }

        // Acción cuando se presiona el botón Menu
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cierra la ventana actual
            menu.setVisible(true); // Muestra el menú principal
        }
    }
}
