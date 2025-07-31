// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Cliente que representa la entidad Cliente con sus atributos y métodos.
public class Cliente {

    // Atributos privados de la clase Cliente
    private int id_cliente; // Identificador único del cliente
    private String cod_cliente, nombre, apellido, correo, telefono, direccion; //Datos personales del cliente

    // Método para obtener el ID del cliente
    public int getId_cliente() {
        return id_cliente;
    }

    // Método para establecer el ID del cliente
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    // Método para obtener el codigo del cliente
    public String getCod_cliente() {
        return cod_cliente;
    }

    // Método para establecer el codigo del cliente
    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    // Método para obtener el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener el apellido del cliente
    public String getApellido() {
        return apellido;
    }

    // Método para establecer el apellido del cliente
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Método para obtener el correo del cliente
    public String getCorreo() {
        return correo;
    }

    // Método para establecer el correo del cliente
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método para obtener el telefono del cliente
    public String getTelefono() {
        return telefono;
    }

    // Método para establecer el telefono del cliente
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método para obtener la dirección del cliente
    public String getDireccion() {
        return direccion;
    }

    // Método para establecer la dirección del cliente
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
