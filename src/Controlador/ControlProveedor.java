package Controlador; // Define el paquete donde se encuentra esta clase

// Importación de clases necesarias del modelo, la vista y librerías estándar
import Modelo.Proveedor; // Importa la clase Proveedor del paquete Modelo
import Modelo.ConsultasProveedor; // Importa la clase ConsultasProveedor del paquete Modelo
import Vista.frmProveedor; // Importa la clase frmProveedor del paquete Vista
import Vista.frmMenu; // Importa la clase frmMenu del paquete Vista
import java.awt.event.ActionEvent; // Importa la clase ActionEvent para manejar eventos de botones
import java.awt.event.ActionListener; // Importa la interfaz ActionListener para escuchar eventos de acción
import javax.swing.JOptionPane; // Importa la clase JOptionPane para mostrar mensajes emergentes
import javax.swing.table.DefaultTableModel; // Importa el modelo por defecto para manejar datos de JTable

// Clase ControlProveedor que implementa la interfaz ActionListener para manejar eventos de la interfaz
public class ControlProveedor implements ActionListener {

    // Atributos privados para manejar el modelo, las consultas, y las vistas
    private final Proveedor modelo; // Objeto que representa al proveedor
    private final ConsultasProveedor consultas; // Objeto para consultar/modificar datos del proveedor
    private final frmProveedor vista; // Ventana del formulario de proveedor
    private final frmMenu menu; // Ventana del menú principal

    // Constructor que recibe las instancias necesarias para funcionar
    public ControlProveedor(Proveedor modelo, ConsultasProveedor consultas, frmProveedor vista, frmMenu menu) {
        this.modelo = modelo; // Asigna el modelo recibido al atributo local
        this.consultas = consultas; // Asigna la clase de consultas
        this.vista = vista; // Asigna la vista del formulario
        this.menu = menu; // Asigna la vista del menú

        // Agrega los botones del formulario al ActionListener (este mismo controlador)
        this.vista.btnGuardar.addActionListener(this); // Botón Guardar
        this.vista.btnModificar.addActionListener(this); // Botón Modificar
        this.vista.btnEliminar.addActionListener(this); // Botón Eliminar
        this.vista.btnBuscar.addActionListener(this); // Botón Buscar
        this.vista.btnLimpiar.addActionListener(this); // Botón Limpiar
        this.vista.btnMenu.addActionListener(this); // Botón Menú
    }

    // Método para iniciar el formulario
    public void iniciar() {
        vista.setTitle("Proveedor"); // Establece el título de la ventana
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        cargarDatosTabla(); // Carga los datos existentes en la tabla
    }

    // Método que carga los datos del proveedor en la tabla de la vista
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Obtiene el modelo de tabla desde las consultas
        vista.tblProveedor.setModel(modeloTabla); // Establece el modelo en la tabla del formulario
    }

    // Método para validar que ciertos campos cumplen con los requisitos de longitud
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) { // Verifica que el código tenga 7 caracteres
            JOptionPane.showMessageDialog(null, "El código de pedido debe contener 7 caracteres."); // Muestra error
            return false; // Retorna falso si la validación falla
        }
        if (vista.txtRuc.getText().trim().length() != 11) { // Verifica que el RUC tenga 11 caracteres
            JOptionPane.showMessageDialog(null, "El RUC debe contener 11 caracteres."); // Muestra error
            return false;
        }
        if (vista.txtTelefono.getText().trim().length() != 9) { // Verifica que el teléfono tenga 9 caracteres
            JOptionPane.showMessageDialog(null, "El telefono debe contener 9 caracteres."); // Muestra error
            return false;
        }
        return true; // Retorna verdadero si todos los campos cumplen
    }

    // Método sobreescrito que maneja todos los eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        // Evento cuando se presiona el botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return; // Sale si la validación falla
            }

            // Se asignan los valores del formulario al modelo
            modelo.setCod_proveedor(vista.txtCodigo.getText().trim());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setRuc(vista.txtRuc.getText().trim());
            modelo.setTelefono(vista.txtTelefono.getText().trim());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setPais(vista.txtPais.getText());

            // Intenta guardar el proveedor en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente."); // Éxito
                cargarDatosTabla(); // Recarga la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación"); // Falla
            }
        }

        // Evento cuando se presiona el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return; // Sale si la validación falla
            }

            // Se actualiza el modelo con los nuevos valores del formulario
            modelo.setCod_proveedor(vista.txtCodigo.getText().trim());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setRuc(vista.txtRuc.getText().trim());
            modelo.setTelefono(vista.txtTelefono.getText().trim());
            modelo.setCorreo(vista.txtCorreo.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setPais(vista.txtPais.getText());

            // Intenta modificar el proveedor en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente."); // Éxito
                cargarDatosTabla(); // Recarga la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación"); // Falla
            }
        }

        // Evento cuando se presiona el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            modelo.setId_proveedor(Integer.parseInt(vista.txtId1.getText())); // Se toma el ID del formulario
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente."); // Éxito
                cargarDatosTabla(); // Recarga la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación."); // Falla
            }
        }

        // Evento cuando se presiona el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_proveedor(vista.txtCodigo.getText()); // Se obtiene el código a buscar
            if (consultas.Buscar(modelo)) {
                // Se muestran los datos encontrados en los campos del formulario
                vista.txtId1.setText(String.valueOf(modelo.getId_proveedor()));
                vista.txtCodigo.setText(modelo.getCod_proveedor());
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtRuc.setText(modelo.getRuc());
                vista.txtTelefono.setText(modelo.getTelefono());
                vista.txtCorreo.setText(modelo.getCorreo());
                vista.txtDireccion.setText(modelo.getDireccion());
                vista.txtPais.setText(modelo.getPais());

                cargarDatosTabla(); // Recarga la tabla con todos los datos
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!"); // No se encontró
                cargarDatosTabla(); // Recarga los datos
            }
        }

        // Evento cuando se presiona el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Limpia todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtNombre.setText(null);
            vista.txtRuc.setText(null);
            vista.txtTelefono.setText(null);
            vista.txtCorreo.setText(null);
            vista.txtDireccion.setText(null);
            vista.txtPais.setText(null);

            // Limpia todas las filas de la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblProveedor.getModel();
            modelo.setRowCount(0);
        }

        // Evento cuando se presiona el botón Menú
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cierra el formulario actual
            menu.setVisible(true); // Abre la ventana del menú principal
        }
    }
}
