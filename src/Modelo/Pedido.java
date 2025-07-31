// Paquete que almacena nuestro javaclass
package Modelo;

// Importación de la clase Date para manejar fechas
import java.sql.Date;

// Clase Pedido que representa la entidad Pedido con sus atributos y métodos.
public class Pedido {

    // Atributos privados de la clase Pedido
    private int id_pedido; // Identificador único del pedido
    private String cod_pedido, nombre_cliente, estado; // Código del pedido, nombre del cliente y estado actual
    private double monto_total; // Monto total del pedido
    private Date fecha_pedido; // Fecha en que se realizó el pedido

    // Método para obtener el ID del pedido
    public int getId_pedido() {
        return id_pedido;
    }

    // Método para establecer el ID del pedido
    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    // Método para obtener el código del pedido
    public String getCod_pedido() {
        return cod_pedido;
    }

    // Método para establecer el código del pedido
    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    // Método para obtener el nombre del cliente asociado al pedido
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    // Método para establecer el nombre del cliente asociado al pedido
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    // Método para obtener el estado del pedido
    public String getEstado() {
        return estado;
    }

    // Método para establecer el estado del pedido
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para obtener el monto total del pedido
    public double getMonto_total() {
        return monto_total;
    }

    // Método para establecer el monto total del pedido
    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    // Método para obtener la fecha del pedido
    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    // Método para establecer la fecha del pedido
    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }
}
