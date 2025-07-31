// Paquete que almacena nuestro javaclass
package Modelo;

// Clase Detalle_Pedido que representa el detalle de un pedido con sus atributos y métodos.
public class Detalle_Pedido {

    // Atributos privados de la clase Detalle_Pedido
    private int id_detalle_ped; // Identificador único del detalle del pedido
    private String cod_pedido, cod_producto, cod_cliente; // Códigos relacionados al pedido, producto y cliente
    private int cantidad; // Cantidad de productos en el detalle
    private double precio_unitario; // Precio unitario del producto en el momento del pedido

    // Método para obtener el ID del detalle del pedido
    public int getId_detalle_ped() {
        return id_detalle_ped;
    }

    // Método para establecer el ID del detalle del pedido
    public void setId_detalle_ped(int id_detalle_ped) {
        this.id_detalle_ped = id_detalle_ped;
    }

    // Método para obtener el código del pedido
    public String getCod_pedido() {
        return cod_pedido;
    }

    // Método para establecer el código del pedido
    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    // Método para obtener el código del producto
    public String getCod_producto() {
        return cod_producto;
    }

    // Método para establecer el código del producto
    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    // Método para obtener el código del cliente
    public String getCod_cliente() {
        return cod_cliente;
    }

    // Método para establecer el código del cliente
    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    // Método para obtener la cantidad del producto
    public int getCantidad() {
        return cantidad;
    }

    // Método para establecer la cantidad del producto
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método para obtener el precio unitario del producto
    public double getPrecio_unitario() {
        return precio_unitario;
    }

    // Método para establecer el precio unitario del producto
    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
