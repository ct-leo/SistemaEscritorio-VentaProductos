// Paquete que contiene nuestro javaclass
package Controlador;

// Librerias importadas
import Vista.frmMenu;
import Vista.frmCliente;
import Vista.frmProducto;
import Vista.frmCategoria;
import Vista.frmDetalle;
import Vista.frmMarca;
import Vista.frmNotificacion;
import Vista.frmPedido;
import Vista.frmProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ControlMenu implements ActionListener {

    // Declaración de variables para las vistas
    private final frmMenu vista;
    private final frmCliente cliente;
    private final frmProducto producto;
    private final frmCategoria categoria;
    private final frmDetalle detalle;
    private final frmMarca marca;
    private final frmNotificacion notificacion;
    private final frmPedido pedido;
    private final frmProveedor proveedor;

    // Constructor de la clase ControlMenu
    public ControlMenu(frmMenu vista, frmCliente cliente, frmProducto producto, frmCategoria categoria, frmDetalle detalle, frmMarca marca, frmNotificacion notificacion, frmPedido pedido, frmProveedor proveedor) {
        this.vista = vista;
        this.cliente = cliente;
        this.producto = producto;
        this.categoria = categoria;
        this.detalle = detalle;
        this.marca = marca;
        this.notificacion = notificacion;
        this.pedido = pedido;
        this.proveedor = proveedor;

        // Asignación de eventos a los botones del menú principal
        this.vista.btnProveedor.addActionListener(this);
        this.vista.btnDetalle.addActionListener(this);
        this.vista.btnCategorias.addActionListener(this);
        this.vista.btnMarca.addActionListener(this);
        this.vista.btnNotificacion.addActionListener(this);
        this.vista.btnPedido.addActionListener(this);
        this.vista.btnProducto.addActionListener(this);
        this.vista.btnCliente.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
    }

    // Método para inicializar la ventana del menú principal
    public void iniciar() {
        vista.setTitle("Sistema Venta de Productos Informaticos"); // Establece el título de la ventana principal
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verifica si el botón presionado es el de Cliente
        if (e.getSource() == vista.btnCliente) {
            vista.dispose(); // Cierra la ventana del menú principal
            cliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Cliente
            cliente.setVisible(true); // Muestra la ventana del Cliente
        }

        // Verifica si el botón presionado es el de Producto
        if (e.getSource() == vista.btnProducto) {
            vista.dispose(); // Cierra la ventana del menú Producto
            producto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Producto
            producto.setVisible(true); // Muestra la ventana del Producto
        }

        // Verifica si el botón presionado es el de Categoria
        if (e.getSource() == vista.btnCategorias) {
            vista.dispose(); // Cierra la ventana del menú Categoria
            categoria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Categoria
            categoria.setVisible(true); // Muestra la ventana del Categoria
        }

        // Verifica si el botón presionado es el de Detalle
        if (e.getSource() == vista.btnDetalle) {
            vista.dispose(); // Cierra la ventana del menú Detalle
            detalle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Detalle
            detalle.setVisible(true); // Muestra la ventana del Detalle
        }

        // Verifica si el botón presionado es el de Marca
        if (e.getSource() == vista.btnMarca) {
            vista.dispose(); // Cierra la ventana del menú Marca
            marca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Marca
            marca.setVisible(true); // Muestra la ventana del Marca
        }

        // Verifica si el botón presionado es el de Producto
        if (e.getSource() == vista.btnNotificacion) {
            vista.dispose(); // Cierra la ventana del menú principal
            notificacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Instructor
            notificacion.setVisible(true); // Muestra la ventana del Instructor
        }

        // Verifica si el botón presionado es el de Pedido
        if (e.getSource() == vista.btnPedido) {
            vista.dispose(); // Cierra la ventana del menú Pedido
            pedido.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Pedido
            pedido.setVisible(true); // Muestra la ventana del Pedido
        }

        // Verifica si el botón presionado es el de Proveedor
        if (e.getSource() == vista.btnProveedor) {
            vista.dispose(); // Cierra la ventana del menú Proveedor
            proveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Proveedor
            proveedor.setVisible(true); // Muestra la ventana del Proveedor
        }

        // Verifica si el botón presionado es el de Proveedor
        if (e.getSource() == vista.btnProveedor) {
            vista.dispose(); // Cierra la ventana del menú Proveedor
            proveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite cerrar solo la ventana del Proveedor
            proveedor.setVisible(true); // Muestra la ventana del Proveedor
        }

        // Verifica si el botón presionado es el de Salir
        if (e.getSource() == vista.btnSalir) {
            System.exit(0); // Cierra la aplicación por completo
        }
    }
}
