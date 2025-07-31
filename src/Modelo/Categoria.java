// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Categoria que representa la entidad Categoría con sus atributos y métodos.
public class Categoria {

    // Atributos privados de la clase Categoria
    private int id_categoria; // Identificador único de la categoría (nota: posible error de escritura en "categoia")
    private String nombre, descripcion; // Nombre y descripción de la categoría
    private int cantidad_total; // Cantidad total de productos en la categoría

    // Método para obtener el ID de la categoría
    public int getId_categoria() {
        return id_categoria;
    }

    // Método para establecer el ID de la categoría
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    // Método para obtener el nombre de la categoría
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre de la categoría
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener la descripción de la categoría
    public String getDescripcion() {
        return descripcion;
    }

    // Método para establecer la descripción de la categoría
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la cantidad total de productos en la categoría
    public int getCantidad_total() {
        return cantidad_total;
    }

    // Método para establecer la cantidad total de productos en la categoría
    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }
}
