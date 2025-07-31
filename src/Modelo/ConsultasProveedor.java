// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que maneja las operaciones CRUD para la entidad Proveedor.
 */
public class ConsultasProveedor extends Conexion {

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param prov Objeto Proveedor con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO proveedor (cod_proveedor, nombre, ruc, telefono, correo, direccion, pais) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getCod_proveedor());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getRuc());
            ps.setString(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getDireccion());
            ps.setString(7, prov.getPais());
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
     * Modifica los datos de un Proveedor existente en la base de datos.
     *
     * @param prov Objeto Proveedor con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE proveedor SET cod_proveedor = ?, nombre = ?, ruc = ?, telefono = ?, correo = ?, direccion = ?, pais = ? WHERE id_proveedor = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getCod_proveedor());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getRuc());
            ps.setString(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getDireccion());
            ps.setString(7, prov.getPais());
            ps.setInt(8, prov.getId_proveedor());
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
     * Elimina un Proveedor de la base de datos usando su ID.
     *
     * @param prov Objeto Proveedor con el ID del Proveedor a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getId_proveedor());
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
     * Busca un Proveedor en la base de datos por su código.
     *
     * @param prov Objeto Proveedor con el código a buscar.
     * @return true si se encontró el Proveedor, false en caso contrario.
     */
    public boolean Buscar(Proveedor prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM proveedor WHERE cod_proveedor = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getCod_proveedor());
            rs = ps.executeQuery();
            if (rs.next()) {
                prov.setId_proveedor(rs.getInt("id_proveedor"));
                prov.setCod_proveedor(rs.getString("cod_proveedor"));
                prov.setNombre(rs.getString("nombre"));
                prov.setRuc(rs.getString("ruc"));
                prov.setTelefono(rs.getString("telefono"));
                prov.setCorreo(rs.getString("correo"));
                prov.setDireccion(rs.getString("direccion"));
                prov.setPais(rs.getString("pais"));
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
     * Lista todos los Proveedor almacenados en la base de datos.
     *
     * @return DefaultTableModel con los datos de todos los Proveedor.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_PROVEEDOR", "COD_PROVEEDOR", "NOMBRE", "RUC", "TELEFONO", "CORREO", "DIRECCION", "PAIS"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM proveedor";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("id_proveedor");
                fila[1] = rs.getString("cod_proveedor");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("ruc");
                fila[4] = rs.getString("telefono");
                fila[5] = rs.getString("correo");
                fila[6] = rs.getString("direccion");
                fila[7] = rs.getString("pais");

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
