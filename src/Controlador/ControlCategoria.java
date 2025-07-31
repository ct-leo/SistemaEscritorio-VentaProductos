// Paquete donde se encuentra esta clase
package Controlador;

// Importación de las clases del modelo y la vista
import Modelo.Categoria;
import Modelo.ConsultasCategoria;
import Vista.frmCategoria;
import Vista.frmMenu;

// Importación para manejar eventos de botones
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Libreria para ejecutar los botones de nuestro formulario

// Importación para mostrar cuadros de diálogo
import javax.swing.JOptionPane;

// Importación para manejar tablas
import javax.swing.table.DefaultTableModel;

// Clase ControlCategoria que implementa la interfaz ActionListener para manejar eventos
public class ControlCategoria implements ActionListener {

    // Atributos privados de la clase para acceder al modelo, consultas y vistas
    private final Categoria modelo;
    private final ConsultasCategoria consultas;
    private final frmCategoria vista;
    private final frmMenu menu;

    // Constructor que inicializa las variables y agrega los listeners a los botones
    public ControlCategoria(Categoria modelo, ConsultasCategoria consultas, frmCategoria vista, frmMenu menu) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.menu = menu;

        // Asignación del listener a cada botón de la vista
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnMenu.addActionListener(this);
    }

    // Método para modificar la ventana
    public void iniciar() {
        vista.setTitle("Categoria"); // Titulo de la ventana
        vista.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        cargarDatosTabla(); // Cargar los datos existentes en la tabla
    }

    // Método para cargar los datos en la tabla desde la base de datos
    public void cargarDatosTabla() {
        DefaultTableModel modeloTabla = consultas.listar(); // Se obtiene el modelo de la tabla con los datos
        vista.tblCategoria.setModel(modeloTabla); // Se asigna el modelo a la tabla de la vista
    }

    // Método sobreescrito que se ejecuta al presionar cualquier botón con ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        // Acción para el botón Guardar
        if (e.getSource() == vista.btnGuardar) {
            // Se capturan los datos del formulario y se asignan al modelo
            modelo.setNombre(vista.txtNombre.getText());
            try {
                modelo.setCantidad_total(Integer.parseInt(vista.txtCantidad.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }
            modelo.setDescripcion(vista.txtDescripcion.getText());

            // Guardar en la base de datos
            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro guardado exitosamente.");
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción para el botón Modificar
        if (e.getSource() == vista.btnModificar) {
            // Se actualizan los datos del modelo desde el formulario
            modelo.setNombre(vista.txtNombre.getText());
            try {
                modelo.setCantidad_total(Integer.parseInt(vista.txtCantidad.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Cantidad o precio inválido. Ingrese valores numéricos.");
                return;
            }
            modelo.setDescripcion(vista.txtDescripcion.getText());

            // Modificar en la base de datos
            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación");
            }
        }

        // Acción para el botón Eliminar
        if (e.getSource() == vista.btnEliminar) {
            // Se obtiene el ID del registro a eliminar
            modelo.setId_categoria(Integer.parseInt(vista.txtId1.getText()));
            // Eliminar de la base de datos
            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Error! No se realizó la operación.");
            }
        }

        // Acción para el botón Buscar
        if (e.getSource() == vista.btnBuscar) {
            // Se usa el nombre como criterio de búsqueda
            modelo.setNombre(vista.txtNombre.getText());
            // Buscar en la base de datos
            if (consultas.Buscar(modelo)) {
                // Si se encuentra, se llenan los campos con la información del modelo
                vista.txtId1.setText(String.valueOf(modelo.getId_categoria()));
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtCantidad.setText(String.valueOf(modelo.getCantidad_total()));
                vista.txtDescripcion.setText(modelo.getDescripcion());

                cargarDatosTabla(); // Actualiza la tabla
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro no encontrado!");
                cargarDatosTabla(); // Refresca la tabla
            }
        }

        // Acción para el botón Limpiar
        if (e.getSource() == vista.btnLimpiar) {
            // Se limpian los campos del formulario
            vista.txtId1.setText(null);
            vista.txtNombre.setText(null);
            vista.txtCantidad.setText(null);
            vista.txtDescripcion.setText(null);

            // Se limpia la tabla también
            DefaultTableModel modelo = (DefaultTableModel) vista.tblCategoria.getModel();
            modelo.setRowCount(0); // Elimina todas las filas
        }

        // Acción para el botón Menu
        if (e.getSource() == vista.btnMenu) {
            vista.dispose(); // Cierra el formulario actual
            menu.setVisible(true); // Muestra el formulario del menú principal
        }
    }
}
