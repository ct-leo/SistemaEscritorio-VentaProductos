// Paquete que almacena nuestro javaclass
package Modelo;

// Librerías necesarias para la conexión a base de datos y manipulación de tablas
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que maneja las operaciones CRUD para la entidad Producto.
 */
public class ConsultasProducto extends Conexion {

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param pro Objeto Producto con los datos a guardar.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean Guardar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO producto (cod_producto, nombre, descripcion, precio, stock, categoria, marca, proveedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCod_producto());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setDouble(4, pro.getPrecio());
            ps.setInt(5, pro.getStock());
            ps.setString(6, pro.getCategoria());
            ps.setString(7, pro.getMarca());
            ps.setString(8, pro.getProveedor());
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
     * Modifica los datos de un producto existente.
     *
     * @param pro Objeto Producto con los nuevos datos.
     * @return true si se modificó correctamente, false si ocurrió un error.
     */
    public boolean Modificar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE producto SET cod_producto = ?, nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ?, marca = ?, proveedor = ? WHERE id_producto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCod_producto());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setDouble(4, pro.getPrecio());
            ps.setInt(5, pro.getStock());
            ps.setString(6, pro.getCategoria());
            ps.setString(7, pro.getMarca());
            ps.setString(8, pro.getProveedor());
            ps.setInt(9, pro.getId_producto());
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
     * Elimina un producto de la base de datos.
     *
     * @param pro Producto a eliminar, usando su ID.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean Eliminar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId_producto());
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
     * Busca un producto en la base de datos por su código.
     *
     * @param pro Producto con el código a buscar.
     * @return true si se encontró el producto, false en caso contrario.
     */
    public boolean Buscar(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String sql = "SELECT * FROM producto WHERE cod_producto = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCod_producto());
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId_producto(rs.getInt("id_producto"));
                pro.setCod_producto(rs.getString("cod_producto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setStock(rs.getInt("stock"));
                pro.setCategoria(rs.getString("categoria"));
                pro.setMarca(rs.getString("marca"));
                pro.setProveedor(rs.getString("proveedor"));
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
     * Lista todos los productos almacenados en la base de datos en un modelo de
     * tabla.
     *
     * @return DefaultTableModel con los datos de todos los productos.
     */
    public DefaultTableModel listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;

        String[] columnas = {"ID_PRODUCTO", "COD_PRODUCTO", "NOMBRE", "DESCRIPCION", "PRECIO", "STOCK", "CATEGORIA", "MARCA", "PROVEEDOR"};
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM producto";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[9];
                fila[0] = rs.getInt("id_producto");
                fila[1] = rs.getString("cod_producto");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("descripcion");
                fila[4] = rs.getDouble("precio");
                fila[5] = rs.getInt("stock");
                fila[6] = rs.getString("categoria");
                fila[7] = rs.getString("marca");
                fila[8] = rs.getString("proveedor");

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
