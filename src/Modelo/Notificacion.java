// Paquete que almacena nuestro javaclass
package Modelo;

// Importación de la clase Date para manejar fechas
import java.sql.Date;

// Clase Notificacion que representa la entidad Notificación con sus atributos y métodos.
public class Notificacion {

    // Atributos privados de la clase Notificacion
    private int id_noti; // Identificador único de la notificación
    private String cod_proveedor, asunto, mensaje, estado; // Código del proveedor, asunto de la notificación, contenido del mensaje y estado
    private Date fecha; // Fecha en que se generó la notificación

    // Método para obtener el ID de la notificación
    public int getId_noti() {
        return id_noti;
    }

    // Método para establecer el ID de la notificación
    public void setId_noti(int id_noti) {
        this.id_noti = id_noti;
    }

    // Método para obtener el código del proveedor relacionado con la notificación
    public String getCod_proveedor() {
        return cod_proveedor;
    }

    // Método para establecer el código del proveedor relacionado con la notificación
    public void setCod_proveedor(String cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    // Método para obtener el asunto de la notificación
    public String getAsunto() {
        return asunto;
    }

    // Método para establecer el asunto de la notificación
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    // Método para obtener el contenido del mensaje de la notificación
    public String getMensaje() {
        return mensaje;
    }

    // Método para establecer el contenido del mensaje de la notificación
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Método para obtener el estado de la notificación (ej. leída/no leída)
    public String getEstado() {
        return estado;
    }

    // Método para establecer el estado de la notificación
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para obtener la fecha de la notificación
    public Date getFecha() {
        return fecha;
    }

    // Método para establecer la fecha de la notificación
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
