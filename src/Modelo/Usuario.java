// Paquete que contiene nuestro javaclass
package Modelo;

public class Usuario {

    // Atributos privados de la clase
    private String usuario; // Nombre de usuario
    private String contra; // Contraseña del usuario

    // Constructor de la clase Usuario.
    public Usuario(String usuario, String contra) {
        this.usuario = usuario;
        this.contra = contra;
    }

    // Método para obtener el nombre de usuario.
    public String getUsuario() {
        return usuario;
    }

    // Método para establecer el nombre de usuario.
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // Método para obtener la contraseña del usuario.
    public String getContra() {
        return contra;
    }

    // Método para establecer la contraseña del usuario.
    public void setContra(String contraseña) {
        this.contra = contraseña;
    }

    // Método para validar si las credenciales ingresadas coinciden con las almacenadas.
    public boolean validarUsuario(String user, String pass) {
        return this.usuario.equals(user) && this.contra.equals(pass);
    }
}
