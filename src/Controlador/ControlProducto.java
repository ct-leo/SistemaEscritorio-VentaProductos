package Controlador; // Paquete que agrupa las clases del controlador del sistema

// Importación de clases necesarias
import Modelo.Producto; // Modelo que representa un producto
import Modelo.ConsultasProducto; // Clase que contiene métodos para interactuar con la base de datos (CRUD)
import Vista.frmProducto; // Vista del formulario de productos
import Vista.frmMenu; // Vista del menú principal
import java.awt.event.ActionEvent; // Clase para manejar eventos de acciones
import java.awt.event.ActionListener; // Interfaz para escuchar acciones (por ejemplo, clics de botones)
import javax.swing.JOptionPane; // Clase para mostrar cuadros de diálogo
import javax.swing.table.DefaultTableModel; // Modelo de tabla para mostrar datos en JTable

// Clase ControlProducto que implementa ActionListener para manejar eventos
public class ControlProducto implements ActionListener {

    // Atributos privados para modelo, consultas, vista y menú
    private final Producto modelo;
    private final ConsultasProducto consultas;
    private final frmProducto vista;
    private final frmMenu menu;

    // Constructor de la clase que inicializa los atributos y asigna eventos a los botones
    public ControlProducto(Producto modelo, ConsultasProducto consultas, frmProducto vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Asignación de eventos a botones del formulario
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método que configura la vista del formulario al iniciar
    public void iniciar() {
        vista.setTitle("Producto"); // Establece el título de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        cargarDatosTabla(); // Carga los datos en la tabla
    }

    // Método para cargar los datos desde la base de datos hacia la tabla
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Se obtiene el modelo de tabla con datos
        vista.tblProducto.setModel(modeloTabla); // Se establece el modelo en la tabla del formulario
    }

    // Método para validar que el código tenga 7 caracteres
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) {
            JOptionPane.showMessageDialog(null, "El código de pedido debe contener 7 caracteres."); // Mensaje de error
            return false; // Retorna falso si no cumple la validación
        }
        return true; // Retorna verdadero si pasa la validación
    }

    // Método que se ejecuta al hacer clic en cualquier botón registrado
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción al presionar el botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return; // Si la validación falla, se detiene la ejecución
            }

            // Asignación de valores al modelo desde los campos del formulario
            modelo.setCod_producto(vista.txtCodigo.getText().trim());
            try {
                modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText())); // Convierte texto a double
                modelo.setStock(Integer.parseInt(vista.txtStock.getText())); // Convierte texto a entero
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return; // Sale del método si hay error de conversión
            }
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setCategoria(vista.txtCategoria.getText());
            modelo.setMarca(vista.txtMarca.getText());
            modelo.setProveedor(vista.txtProveedor.getText());

            // Guarda el producto en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                cargarDatosTabla(); // Recarga los datos en la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción al presionar el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return;
            }

            // Asignación de valores al modelo
            modelo.setCod_producto(vista.txtCodigo.getText().trim());
            try {
                modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
                modelo.setStock(Integer.parseInt(vista.txtStock.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setCategoria(vista.txtCategoria.getText());
            modelo.setMarca(vista.txtMarca.getText());
            modelo.setProveedor(vista.txtProveedor.getText());

            // Modifica el producto en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
                cargarDatosTabla();
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción al presionar el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_producto(Integer.parseInt(vista.txtId1.getText())); // Se obtiene el ID para eliminar
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                cargarDatosTabla(); // Recarga los datos en la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación.");
            }
        }

        // Acción al presionar el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_producto(vista.txtCodigo.getText()); // Se obtiene el código ingresado
            if (consultas.Buscar(modelo)) { // Se busca el producto en la base de datos
                // Si se encuentra, se muestran los datos en el formulario
                vista.txtId1.setText(String.valueOf(modelo.getId_producto()));
                vista.txtCodigo.setText(modelo.getCod_producto());
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtDescripcion.setText(modelo.getDescripcion());
                vista.txtPrecio.setText(String.valueOf(modelo.getPrecio()));
                vista.txtStock.setText(String.valueOf(modelo.getStock()));
                vista.txtCategoria.setText(modelo.getCategoria());
                vista.txtMarca.setText(modelo.getMarca());
                vista.txtProveedor.setText(modelo.getProveedor());

                cargarDatosTabla(); // Recarga la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!");
                cargarDatosTabla();
            }
        }

        // Acción al presionar el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Se limpian todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtNombre.setText(null);
            vista.txtDescripcion.setText(null);
            vista.txtPrecio.setText(null);
            vista.txtStock.setText(null);
            vista.txtCategoria.setText(null);
            vista.txtMarca.setText(null);
            vista.txtProveedor.setText(null);

            // Se limpia la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblProducto.getModel();
            modelo.setRowCount(0);
        }

        // Acción al presionar el botón Menú
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Se cierra la ventana actual
            menu.setVisible(true); // Se muestra el menú principal
        }
    }
}
