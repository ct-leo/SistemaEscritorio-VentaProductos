// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que maneja las operaciones CRUD para la entidad Categoria.
 */
public class ConsultasCategoria extends Conexion {

    /**
     * Guarda una nueva categoría en la base de datos.
     *
     * @param cat Objeto Categoria con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Categoria cat) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO categoria (nombre, cantidad_total, descripcion) VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            ps.setInt(2, cat.getCantidad_total());
            ps.setString(3, cat.getDescripcion());
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
     * Modifica los datos de una categoría existente en la base de datos.
     *
     * @param cat Objeto Categoria con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Categoria cat) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE categoria SET nombre = ?, cantidad_total = ?, descripcion = ? WHERE id_categoria = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            ps.setInt(2, cat.getCantidad_total());
            ps.setString(3, cat.getDescripcion());
            ps.setInt(4, cat.getId_categoria());
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
     * Elimina una categoría de la base de datos usando su ID.
     *
     * @param cat Objeto Categoria con el ID de la categoría a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Categoria cat) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cat.getId_categoria());
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
     * Busca una categoría en la base de datos por su nombre.
     *
     * @param cat Objeto Categoria con el nombre a buscar.
     * @return true si se encontró la categoría, false en caso contrario.
     */
    public boolean Buscar(Categoria cat) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM categoria WHERE nombre = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cat.getNombre());
            rs = ps.executeQuery();
            if (rs.next()) {
                cat.setId_categoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre"));
                cat.setCantidad_total(rs.getInt("cantidad_total"));
                cat.setDescripcion(rs.getString("descripcion"));
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
     * Lista todas las categorías almacenadas en la base de datos.
     *
     * @return DefaultTableModel con los datos de todas las categorías.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_CATEGORIA", "NOMBRE", "CANTIDAD_TOTAL", "DESCRIPCION"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM categoria";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("id_categoria");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getInt("cantidad_total");
                fila[3] = rs.getString("descripcion");

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
