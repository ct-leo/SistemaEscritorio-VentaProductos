// Paquete que contiene esta clase
package Controlador;

// Importación de clases modelo y vista necesarias para el controlador
import Modelo.Pedido;
import Modelo.ConsultasPedido;
import Vista.frmPedido;
import Vista.frmMenu;

// Importación de librerías para manejo de eventos y componentes Swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Librería para ejecutar los botones de nuestro formulario
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

// Definición de la clase ControlPedido que implementa ActionListener para manejar eventos
public class ControlPedido implements ActionListener {

    // Declaración de variables privadas y finales para mantener referencias del modelo, consultas, vista y menú
    private final Pedido modelo;
    private final ConsultasPedido consultas;
    private final frmPedido vista;
    private final frmMenu menu;

    // Constructor que recibe los objetos necesarios para inicializar el controlador
    public ControlPedido(Pedido modelo, ConsultasPedido consultas, frmPedido vista, frmMenu menu) {
        this.modelo = modelo; // Asignación del modelo de datos Pedido
        this.consultas = consultas; // Asignación del objeto de consultas a BD
        this.vista = vista; // Asignación de la vista de pedido
        this.menu = menu; // Asignación de la vista del menú

        // Asignación de eventos a cada botón de la interfaz
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método para configurar la ventana del formulario de pedidos
    public void iniciar() {
        vista.setTitle("Pedido"); // Título de la ventana
        vista.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        cargarDatosTabla(); // Cargar los datos en la tabla al iniciar
    }

    // Método privado para cargar los datos en la tabla del formulario
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Obtener el modelo de tabla desde la base de datos
        vista.tblPedido.setModel(modeloTabla); // Asignar los datos al componente JTable
    }

    // Método para validar que el campo del código tenga exactamente 7 caracteres
    private boolean validarCampos() {
        if (vista.txtCodigo.getText().trim().length() != 7) {
            // Mostrar mensaje de error si el código no tiene 7 caracteres
            JOptionPane.showMessageDialog(null, "El código del proveedor debe contener 7 caracteres.");
            return false; // Devuelve false si no cumple
        }
        return true; // Devuelve true si la validación es correcta
    }

    // Método que se ejecuta cuando ocurre un evento de acción (click en un botón)
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción para el botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            if (!validarCampos()) {
                return; // Detiene la ejecución si la validación falla
            }

            // Asignar valores del formulario al modelo
            modelo.setCod_pedido(vista.txtCodigo.getText().trim());
            modelo.setNombre_cliente(vista.txtNombre.getText());

            // Validar que se haya seleccionado una fecha
            java.util.Date fechaSelec = vista.jDate.getDate();
            if (fechaSelec != null) {
                java.sql.Date fechaSQL = new java.sql.Date(fechaSelec.getTime());
                modelo.setFecha_pedido(fechaSQL); // Asignar fecha al modelo
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha!"); // Mostrar error
                return; // Salir si no hay fecha
            }

            // Validar y asignar el monto total
            try {
                modelo.setMonto_total(Double.parseDouble(vista.txtMonto.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }

            modelo.setEstado(vista.txtEstado.getText()); // Asignar estado al modelo

            // Intentar guardar en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                cargarDatosTabla(); // Recargar datos en la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción para el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            if (!validarCampos()) {
                return; // Salir si falla validación
            }

            // Asignar valores al modelo
            modelo.setCod_pedido(vista.txtCodigo.getText().trim());
            modelo.setNombre_cliente(vista.txtNombre.getText());

            // Validar la fecha seleccionada
            java.util.Date fechaSelec = vista.jDate.getDate();
            if (fechaSelec != null) {
                java.sql.Date fechaSQL = new java.sql.Date(fechaSelec.getTime());
                modelo.setFecha_pedido(fechaSQL);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha!");
                return;
            }

            // Validar el monto
            try {
                modelo.setMonto_total(Double.parseDouble(vista.txtMonto.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }

            modelo.setEstado(vista.txtEstado.getText());

            // Ejecutar la modificación en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
                cargarDatosTabla(); // Recargar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción para el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            // Obtener ID del pedido desde el campo de texto
            modelo.setId_pedido(Integer.parseInt(vista.txtId1.getText()));

            // Ejecutar eliminación en la base de datos
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                cargarDatosTabla(); // Recargar datos
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación.");
            }
        }

        // Acción para el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            modelo.setCod_pedido(vista.txtCodigo.getText()); // Asignar código para buscar

            // Si se encuentra el registro
            if (consultas.Buscar(modelo)) {
                // Mostrar los datos del modelo en la interfaz
                vista.txtId1.setText(String.valueOf(modelo.getId_pedido()));
                vista.txtCodigo.setText(modelo.getCod_pedido());
                vista.txtNombre.setText(modelo.getNombre_cliente());

                // Mostrar la fecha
                if (modelo.getFecha_pedido() != null) {
                    vista.jDate.setDate(new java.util.Date(modelo.getFecha_pedido().getTime()));
                } else {
                    vista.jDate.setDate(null);
                }

                vista.txtMonto.setText(String.valueOf(modelo.getMonto_total()));
                vista.txtEstado.setText(modelo.getEstado());

                cargarDatosTabla(); // Actualizar tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!"); // Si no se encuentra
                cargarDatosTabla(); // Refrescar tabla
            }
        }

        // Acción para el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Vaciar todos los campos del formulario
            vista.txtId1.setText(null);
            vista.txtCodigo.setText(null);
            vista.txtNombre.setText(null);
            vista.jDate.setDate(null);
            vista.txtMonto.setText(null);
            vista.txtEstado.setText(null);

            // Limpiar los datos mostrados en la tabla
            DefaultTableModel modelo = (DefaultTableModel) vista.tblPedido.getModel();
            modelo.setRowCount(0);
        }

        // Acción para el botón Menu
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cerrar la vista actual
            menu.setVisible(true); // Mostrar el menú principal
        }
    }
}
