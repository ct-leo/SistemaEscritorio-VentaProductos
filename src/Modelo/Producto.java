// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Producto que representa la entidad Producto con sus atributos y métodos.
public class Producto {

    // Atributos privados de la clase Producto
    private int id_producto; // Identificador único del producto
    private String cod_producto, nombre, descripcion, categoria, marca, proveedor; // Datos descriptivos del producto
    private int stock; // Cantidad disponible en inventario
    private double precio; // Precio unitario del producto

    // Método para obtener el ID del producto
    public int getId_producto() {
        return id_producto;
    }

    // Método para establecer el ID del producto
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    // Método para obtener el código del producto
    public String getCod_producto() {
        return cod_producto;
    }

    // Método para establecer el código del producto
    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    // Método para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener la descripción del producto
    public String getDescripcion() {
        return descripcion;
    }

    // Método para establecer la descripción del producto
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la categoría del producto
    public String getCategoria() {
        return categoria;
    }

    // Método para establecer la categoría del producto
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método para obtener el stock del producto
    public int getStock() {
        return stock;
    }

    // Método para establecer el stock del producto
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método para obtener el precio del producto
    public double getPrecio() {
        return precio;
    }

    // Método para establecer el precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para obtener la marca del producto
    public String getMarca() {
        return marca;
    }

    // Método para establecer la marca del producto
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Método para obtener el proveedor del producto
    public String getProveedor() {
        return proveedor;
    }

    // Método para establecer el proveedor del producto
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
