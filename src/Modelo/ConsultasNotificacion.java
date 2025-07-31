// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que gestiona las operaciones CRUD para la entidad Notificacion.
 */
public class ConsultasNotificacion extends Conexion {

    /**
     * Guarda un nuevo Notificacion en la base de datos.
     *
     * @param noti Objeto Notificacion con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */

    public boolean Guardar(Notificacion noti) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO notificacion (Cod_proveedor, Asunto , Mensaje , Fecha , Estado) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, noti.getCod_proveedor());
            ps.setString(2, noti.getAsunto());
            ps.setString(3, noti.getMensaje());
            ps.setDate(4, noti.getFecha());
            ps.setString(5, noti.getEstado());
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
     * Modifica los datos de una Notificacion existente en la base de datos.
     *
     * @param noti Objeto Notificacion con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Notificacion noti) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE notificacion SET Cod_proveedor=?, Asunto=? , Mensaje=? , Fecha=? , Estado=? WHERE id_noti=? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, noti.getCod_proveedor());
            ps.setString(2, noti.getAsunto());
            ps.setString(3, noti.getMensaje());
            ps.setDate(4, noti.getFecha());
            ps.setString(5, noti.getEstado());
            ps.setInt(6, noti.getId_noti());
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
     * Elimina un Notificacion de la base de datos usando su ID.
     *
     * @param noti Objeto Notificacion con el ID del Notificacion a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Notificacion noti) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM notificacion WHERE id_noti=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, noti.getId_noti());
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
     * Busca un Notificacion en la base de datos por su código.
     *
     * @param noti Objeto Notificacion con el código a buscar.
     * @return true si se encontró el Notificacion, false en caso contrario.
     */
    public boolean Buscar(Notificacion noti) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM notificacion WHERE cod_proveedor=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, noti.getCod_proveedor());
            rs = ps.executeQuery();
            if (rs.next()) {
                noti.setId_noti(rs.getInt("id_noti"));
                noti.setCod_proveedor(rs.getString("cod_proveedor"));
                noti.setAsunto(rs.getString("asunto"));
                noti.setMensaje(rs.getString("mensaje"));
                noti.setFecha(rs.getDate("fecha"));
                noti.setEstado(rs.getString("estado"));
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
     * Lista todos los Notificacion almacenados en la base de datos.
     *
     * @return DefaultTableModel con los datos de todos los Notificacion.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_NOTI", "COD_PROVEEDOR", "ASUNTO", "MENSAJE", "FECHA", "ESTADO"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM notificacion";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_noti");
                fila[1] = rs.getString("cod_proveedor");
                fila[2] = rs.getString("asunto");
                fila[3] = rs.getString("mensaje");
                fila[4] = rs.getDate("fecha");
                fila[5] = rs.getString("estado");

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
