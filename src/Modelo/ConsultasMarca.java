// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que gestiona las operaciones CRUD para la entidad Marca.
 */
public class ConsultasMarca extends Conexion {

    /**
     * Guarda una nueva Marca en la base de datos.
     *
     * @param mar Objeto Marca con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Marca mar) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO marca (nombre, pais, cod_proveedor) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mar.getNombre());
            ps.setString(2, mar.getPais());
            ps.setString(3, mar.getCod_proveedor());
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
     * Modifica los datos de un marca existente.
     *
     * @param mar Objeto Marca con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Marca mar) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE marca SET nombre = ?, pais = ?, cod_proveedor = ? WHERE id_marca = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mar.getNombre());
            ps.setString(2, mar.getPais());
            ps.setString(3, mar.getCod_proveedor());
            ps.setInt(4, mar.getId_marca());
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
     * Elimina una Marca de la base de datos.
     *
     * @param mar Marca a eliminar, usando su ID.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Marca mar) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM Marca WHERE id_marca = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, mar.getId_marca());
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
     * Busca una marca en la base de datos por su código.
     *
     * @param mar Marca con el código a buscar.
     * @return true si se encontró la Marca, false en caso contrario.
     */
    public boolean Buscar(Marca mar) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM marca WHERE nombre = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mar.getNombre());
            rs = ps.executeQuery();
            if (rs.next()) {
                mar.setId_marca(rs.getInt("id_marca"));
                mar.setNombre(rs.getString("nombre"));
                mar.setPais(rs.getString("pais"));
                mar.setCod_proveedor(rs.getString("cod_proveedor"));
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
     * Lista todos las Marcas almacenados en la base de datos en un modelo de
     * tabla.
     *
     * @return DefaultTableModel con los datos de todos las Marcas.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_MARCA", "NOMBRE", "PAIS", "COD_PROVEEDOR"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM marca";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("id_marca");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("pais");
                fila[3] = rs.getString("cod_proveedor");

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
