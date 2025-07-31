// Paquete que contiene nuestro javaclass
package Controlador;

// Librerias importadas
import Modelo.Usuario;
import Vista.frmLogin;
import Vista.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControlLogin implements ActionListener {

    private Usuario modelo; // Modelo que representa a un usuario en la base de datos
    private frmLogin vista; // Vista del formulario de login
    private frmMenu menu; // Vista del menú principal

    // Constructor de la clase ControlLogin
    public ControlLogin(Usuario modelo, frmLogin vista, frmMenu menu) {
        this.modelo = modelo; // Asigna el modelo de usuario
        this.vista = vista; // Asigna la vista del login
        this.menu = menu; // Asigna la vista del menú

        // Agregar listeners a los botones de la vista de login
        this.vista.btnIngresar.addActionListener(this); // Botón para validar el login
        this.vista.btnSalir.addActionListener(this); // Botón para salir de la aplicación
    }

    // Método para modificar la ventana de login
    public void iniciar() {
        vista.setTitle("Login"); // Establece el título de la ventana de login
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        vista.setVisible(true); // Hace visible la ventana de login
    }

    // Método que maneja las acciones de los botones del login
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica si se presionó el botón de "Ingresar"
        if (e.getSource() == vista.btnIngresar) {
            String usuario = vista.txtUsuario.getText(); // Obtiene el texto del campo de usuario
            String contra = new String(vista.txtContra.getPassword()); // Obtiene la contraseña ingresada

            // Verifica si el usuario y la contraseña son correctos
            if (modelo.validarUsuario(usuario, contra)) {
                JOptionPane.showMessageDialog(vista, "Acceso concedido"); // Muestra mensaje de éxito
                vista.dispose(); // Cierra la ventana de login
                menu.setVisible(true); // Muestra la ventana del menú principal
            } else {
                // Muestra un mensaje de error si el usuario o la contraseña son incorrectos
                JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // Verifica si se presionó el botón de "Salir"
        else if (e.getSource() == vista.btnSalir) {
            System.exit(0); // Cierra la aplicación por completo
        }
    }
}
