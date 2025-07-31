// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que gestiona las operaciones CRUD para la entidad Detalle.
 */
public class ConsultasDetalle extends Conexion {

    /**
     * Guarda un nuevo cliente en la base de datos.
     *
     * @param det Objeto Detalle con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Detalle_Pedido det) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO detalle_pedido (cod_pedido, cod_producto, cod_cliente, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, det.getCod_pedido());
            ps.setString(2, det.getCod_producto());
            ps.setString(3, det.getCod_cliente());
            ps.setInt(4, det.getCantidad());
            ps.setDouble(5, det.getPrecio_unitario());
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
     * Modifica los datos de un Detalle existente.
     *
     * @param det Objeto Detalle con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Detalle_Pedido det) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE detalle_pedido SET cod_pedido = ?, cod_producto = ?, cod_cliente = ?, cantidad = ?, precio_unitario = ? WHERE id_detalle_ped = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, det.getCod_pedido());
            ps.setString(2, det.getCod_producto());
            ps.setString(3, det.getCod_cliente());
            ps.setInt(4, det.getCantidad());
            ps.setDouble(5, det.getPrecio_unitario());
            ps.setInt(6, det.getId_detalle_ped());
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
     * Elimina un Detalle de la base de datos.
     *
     * @param det Detalle a eliminar, usando su ID.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Detalle_Pedido det) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM detalle_pedido WHERE id_detalle_ped = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, det.getId_detalle_ped());
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
     * Busca un Detalle en la base de datos por su código.
     *
     * @param de Detalle con el código a buscar.
     * @return true si se encontró el Detalle, false en caso contrario.
     */
    public boolean Buscar(Detalle_Pedido det) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM detalle_pedido WHERE cod_pedido = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, det.getCod_pedido());
            rs = ps.executeQuery();
            if (rs.next()) {
                det.setId_detalle_ped(rs.getInt("id_detalle_ped"));
                det.setCod_pedido(rs.getString("cod_pedido"));
                det.setCod_producto(rs.getString("cod_producto"));
                det.setCod_cliente(rs.getString("cod_cliente"));
                det.setCantidad(rs.getInt("cantidad"));
                det.setPrecio_unitario(rs.getDouble("precio_unitario"));
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
     * Lista todos los Detalle almacenados en la base de datos en un modelo de
     * tabla.
     *
     * @return DefaultTableModel con los datos de todos los Detalles.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_DETALLE_PED", "COD_PEDIDO", "COD_PRODUCTO", "COD_CLIENTE", "CANTIDAD", "PRECIO_UNITARIO"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM detalle_pedido";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_detalle_ped");
                fila[1] = rs.getString("cod_pedido");
                fila[2] = rs.getString("cod_producto");
                fila[3] = rs.getString("cod_cliente");
                fila[4] = rs.getInt("cantidad");
                fila[5] = rs.getDouble("precio_unitario");

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
