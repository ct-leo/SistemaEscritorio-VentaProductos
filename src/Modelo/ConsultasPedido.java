// Paquete que almacena nuestro javaclass
package Modelo;
// Librerias importadas

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que maneja las operaciones CRUD para la entidad Pedido.
 */
public class ConsultasPedido extends Conexion {

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param ped Objeto Pedido con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Pedido ped) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO pedido (cod_pedido, nombre_cliente, fecha_pedido, monto_total, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ped.getCod_pedido());
            ps.setString(2, ped.getNombre_cliente());
            ps.setDate(3, ped.getFecha_pedido());
            ps.setDouble(4, ped.getMonto_total());
            ps.setString(5, ped.getEstado());
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
     * Modifica los datos de un pedido existente en la base de datos.
     *
     * @param ped Objeto Pedido con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Pedido ped) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE pedido SET cod_pedido = ?, nombre_cliente = ?, fecha_pedido = ?, monto_total = ?, estado = ? WHERE id_pedido = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ped.getCod_pedido());
            ps.setString(2, ped.getNombre_cliente());
            ps.setDate(3, ped.getFecha_pedido());
            ps.setDouble(4, ped.getMonto_total());
            ps.setString(5, ped.getEstado());
            ps.setInt(6, ped.getId_pedido());
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
     * Elimina un pedido de la base de datos usando su ID.
     *
     * @param ped Objeto Pedido con el ID del pedido a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Pedido ped) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM pedido WHERE id_pedido = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ped.getId_pedido());
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
     * Busca un pedido en la base de datos por su código.
     *
     * @param ped Objeto Pedido con el código a buscar.
     * @return true si se encontró el pedido, false en caso contrario.
     */
    public boolean Buscar(Pedido ped) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM pedido WHERE cod_pedido = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ped.getCod_pedido());
            rs = ps.executeQuery();
            if (rs.next()) {
                ped.setId_pedido(rs.getInt("id_pedido"));
                ped.setCod_pedido(rs.getString("cod_pedido"));
                ped.setNombre_cliente(rs.getString("nombre_cliente"));
                ped.setFecha_pedido(rs.getDate("fecha_pedido"));
                ped.setMonto_total(rs.getDouble("monto_total"));
                ped.setEstado(rs.getString("estado"));
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
     * Lista todos los pedidos almacenados en la base de datos.
     *
     * @return DefaultTableModel con los datos de todos los pedidos.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_PEDIDO", "COD_PEDIDO", "NOMBRE_CLIENTE", "FECHA_PEDIDO", "MONTO_TOTAL", "ESTADO"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM pedido";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_pedido");
                fila[1] = rs.getString("cod_pedido");
                fila[2] = rs.getString("nombre_cliente");
                fila[3] = rs.getDate("fecha_pedido");
                fila[4] = rs.getDouble("monto_total");
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
