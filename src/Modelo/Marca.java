// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Marca que representa la entidad Marca con sus atributos y métodos.
public class Marca {

    // Atributos privados de la clase Marca
    private int id_marca; // Identificador único de la marca
    private String nombre, pais, cod_proveedor; // Nombre de la marca, país de origen y código del proveedor asociado

    // Método para obtener el ID de la marca
    public int getId_marca() {
        return id_marca;
    }

    // Método para establecer el ID de la marca
    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    // Método para obtener el nombre de la marca
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre de la marca
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener el país de origen de la marca
    public String getPais() {
        return pais;
    }

    // Método para establecer el país de origen de la marca
    public void setPais(String pais) {
        this.pais = pais;
    }

    // Método para obtener el código del proveedor asociado a la marca
    public String getCod_proveedor() {
        return cod_proveedor;
    }

    // Método para establecer el código del proveedor asociado a la marca
    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }
}
