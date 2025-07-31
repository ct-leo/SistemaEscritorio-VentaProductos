package mainCRUD; // Define el paquete donde se encuentra esta clase principal

// Importaciones de todas las vistas necesarias para el sistema
import Vista.frmMenu; // Importa la vista del menú principal
import Vista.frmCliente; // Importa la vista del formulario de cliente
import Vista.frmProducto; // Importa la vista del formulario de producto
import Vista.frmCategoria; // Importa la vista del formulario de categoría
import Vista.frmDetalle; // Importa la vista del formulario de detalle de pedido
import Vista.frmLogin; // Importa la vista del formulario de login
import Vista.frmMarca; // Importa la vista del formulario de marca
import Vista.frmNotificacion; // Importa la vista del formulario de notificación
import Vista.frmPedido; // Importa la vista del formulario de pedido
import Vista.frmProveedor; // Importa la vista del formulario de proveedor

// Importaciones de todos los modelos utilizados
import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Categoria;
import Modelo.Detalle_Pedido;
import Modelo.Marca;
import Modelo.Notificacion;
import Modelo.Pedido;
import Modelo.Proveedor;
import Modelo.Usuario;

// Importaciones de todas las clases de consultas (acceso a base de datos)
import Modelo.ConsultasCliente;
import Modelo.ConsultasProducto;
import Modelo.ConsultasDetalle;
import Modelo.ConsultasMarca;
import Modelo.ConsultasNotificacion;
import Modelo.ConsultasPedido;
import Modelo.ConsultasProveedor;
import Modelo.ConsultasCategoria;

// Importaciones de todos los controladores que manejan la lógica de cada entidad
import Controlador.ControlMenu;
import Controlador.ControlCliente;
import Controlador.ControlProducto;
import Controlador.ControlCategoria;
import Controlador.ControlDetalle;
import Controlador.ControlLogin;
import Controlador.ControlMarca;
import Controlador.ControlNotificacion;
import Controlador.ControlPedido;
import Controlador.ControlProveedor;

// Clase principal del sistema llamada mainCRUD
public class mainCRUD {

    // Método principal que se ejecuta al iniciar la aplicación
    public static void main(String[] args) {

        // Instanciación de todas las vistas que se van a usar
        frmLogin login = new frmLogin(); // Ventana de login
        frmMenu menu = new frmMenu(); // Ventana del menú principal
        frmCliente cliente = new frmCliente(); // Ventana de cliente
        frmProducto producto = new frmProducto(); // Ventana de producto
        frmCategoria categoria = new frmCategoria(); // Ventana de categoría
        frmDetalle detalle = new frmDetalle(); // Ventana de detalle de pedido
        frmMarca marca = new frmMarca(); // Ventana de marca
        frmNotificacion notificacion = new frmNotificacion(); // Ventana de notificaciones
        frmPedido pedido = new frmPedido(); // Ventana de pedidos
        frmProveedor proveedor = new frmProveedor(); // Ventana de proveedores

        // Creación del modelo de usuario con credenciales predefinidas (login por defecto)
        Usuario usuario = new Usuario("ADMIN", "sistemaventa");

        // Instanciación del modelo y la clase de consultas para cliente
        Cliente modelocliente = new Cliente();
        ConsultasCliente consultascliente = new ConsultasCliente();

        // Instanciación del modelo y consultas para producto
        Producto modeloproducto = new Producto();
        ConsultasProducto consultasproducto = new ConsultasProducto();

        // Instanciación del modelo y consultas para categoría
        Categoria modelocategoria = new Categoria();
        ConsultasCategoria consultascategoria = new ConsultasCategoria();

        // Instanciación del modelo y consultas para detalle de pedido
        Detalle_Pedido modelodetalle = new Detalle_Pedido();
        ConsultasDetalle consultasdetalle = new ConsultasDetalle();

        // Instanciación del modelo y consultas para marca
        Marca modelomarca = new Marca();
        ConsultasMarca consultasmarca = new ConsultasMarca();

        // Instanciación del modelo y consultas para notificación
        Notificacion modelonotificacion = new Notificacion();
        ConsultasNotificacion consultasnotificacion = new ConsultasNotificacion();

        // Instanciación del modelo y consultas para pedido
        Pedido modelopedido = new Pedido();
        ConsultasPedido consultaspedido = new ConsultasPedido();

        // Instanciación del modelo y consultas para proveedor
        Proveedor modeloproveedor = new Proveedor();
        ConsultasProveedor consultasproveedor = new ConsultasProveedor();

        // Creación del controlador de login, pasando el modelo y vistas necesarias
        ControlLogin controlLogin = new ControlLogin(usuario, login, menu);
        controlLogin.iniciar(); // Inicializa el formulario de login

        // Creación del controlador del menú principal
        ControlMenu controlMenu = new ControlMenu(menu, cliente, producto, categoria, detalle, marca, notificacion, pedido, proveedor);
        controlMenu.iniciar(); // Inicializa el menú

        // Creación del controlador para cliente
        ControlCliente controlcliente = new ControlCliente(modelocliente, consultascliente, cliente, menu);
        controlcliente.iniciar(); // Inicializa la vista cliente
        controlcliente.cargarDatosTabla(); // Carga los datos en la tabla de cliente

        // Creación del controlador para producto
        ControlProducto controlproducto = new ControlProducto(modeloproducto, consultasproducto, producto, menu);
        controlproducto.iniciar(); // Inicializa la vista producto
        controlproducto.cargarDatosTabla(); // Carga los datos en la tabla de producto

        // Creación del controlador para categoría
        ControlCategoria controlcategoria = new ControlCategoria(modelocategoria, consultascategoria, categoria, menu);
        controlcategoria.iniciar(); // Inicializa la vista categoría
        controlcategoria.cargarDatosTabla(); // Carga los datos en la tabla de categoría

        // Creación del controlador para detalle de pedido
        ControlDetalle controldetalle = new ControlDetalle(modelodetalle, consultasdetalle, detalle, menu);
        controldetalle.iniciar(); // Inicializa la vista detalle
        controldetalle.cargarDatosTabla(); // Carga los datos en la tabla de detalle

        // Creación del controlador para marca
        ControlMarca controlmarca = new ControlMarca(modelomarca, consultasmarca, marca, menu);
        controlmarca.iniciar(); // Inicializa la vista marca
        controlmarca.cargarDatosTabla(); // Carga los datos en la tabla de marca

        // Creación del controlador para notificación
        ControlNotificacion controlnotificacion = new ControlNotificacion(modelonotificacion, consultasnotificacion, notificacion, menu);
        controlnotificacion.iniciar(); // Inicializa la vista notificación
        controlnotificacion.cargarDatosTabla(); // Carga los datos en la tabla de notificación

        // Creación del controlador para pedido
        ControlPedido controlpedido = new ControlPedido(modelopedido, consultaspedido, pedido, menu);
        controlpedido.iniciar(); // Inicializa la vista pedido
        controlpedido.cargarDatosTabla(); // Carga los datos en la tabla de pedidos

        // Creación del controlador para proveedor
        ControlProveedor controlproveedor = new ControlProveedor(modeloproveedor, consultasproveedor, proveedor, menu);
        controlproveedor.iniciar(); // Inicializa la vista proveedor
        controlproveedor.cargarDatosTabla(); // Carga los datos en la tabla de proveedores

        // Muestra la ventana de login al usuario para iniciar sesión
        login.setVisible(true);
    }

}
