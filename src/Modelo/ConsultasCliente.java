// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que gestiona las operaciones CRUD para la entidad Cliente.
 */
public class ConsultasCliente extends Conexion {

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param cli Objeto Cliente con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO cliente (cod_cliente, nombre, apellido, correo, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCod_cliente());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getApellido());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getDireccion());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                con.close(); // Cerrar la conexión
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return false;
    }

    /**
     * Modifica los datos de un cliente existente.
     *
     * @param cli Objeto Cliente con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE cliente SET cod_cliente = ?, nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ? WHERE id_cliente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCod_cliente());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getApellido());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getTelefono());
            ps.setString(6, cli.getDireccion());
            ps.setInt(7, cli.getId_cliente());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return false;
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param cli Cliente a eliminar, usando su ID.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cli.getId_cliente());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return false;
    }

    /**
     * Busca un cliente en la base de datos por su código.
     *
     * @param cli Cliente con el código a buscar.
     * @return true si se encontró el cliente, false en caso contrario.
     */
    public boolean Buscar(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM cliente WHERE cod_cliente = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCod_cliente());
            rs = ps.executeQuery();
            if (rs.next()) {
                cli.setId_cliente(rs.getInt("id_cliente"));
                cli.setCod_cliente(rs.getString("cod_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setCorreo(rs.getString("correo"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Lista todos los clientes almacenados en la base de datos en un modelo de
     * tabla.
     *
     * @return DefaultTableModel con los datos de todos los clientes.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_CLIENTE", "COD_CLIENTE", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "DIRECCION"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM cliente";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_cliente");
                fila[1] = rs.getString("cod_cliente");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                fila[4] = rs.getString("correo");
                fila[5] = rs.getString("telefono");
                fila[6] = rs.getString("direccion");

                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return modeloTabla;
    }
}
