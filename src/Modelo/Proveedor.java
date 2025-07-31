// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Proveedor que representa la entidad Proveedor con sus atributos y métodos.
public class Proveedor {

    // Atributos privados de la clase Proveedor
    private int id_proveedor; // Identificador único del proveedor
    private String cod_proveedor, nombre, ruc, telefono, correo, direccion, pais; // Datos del proveedor

    // Método para obtener el ID del proveedor
    public int getId_proveedor() {
        return id_proveedor;
    }

    // Método para establecer el ID del proveedor
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    // Método para obtener el código del proveedor
    public String getCod_proveedor() {
        return cod_proveedor;
    }

    // Método para establecer el código del proveedor
    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    // Método para obtener el nombre del proveedor
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del proveedor
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener el RUC del proveedor
    public String getRuc() {
        return ruc;
    }

    // Método para establecer el RUC del proveedor
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    // Método para obtener el teléfono del proveedor
    public String getTelefono() {
        return telefono;
    }

    // Método para establecer el teléfono del proveedor
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para obtener el correo del proveedor
    public String getCorreo() {
        return correo;
    }

    // Método para establecer el correo del proveedor
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método para obtener la dirección del proveedor
    public String getDireccion() {
        return direccion;
    }

    // Método para establecer la dirección del proveedor
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para obtener el país del proveedor
    public String getPais() {
        return pais;
    }

    // Método para establecer el país del proveedor
    public void setPais(String pais) {
        this.pais = pais;
    }
}
