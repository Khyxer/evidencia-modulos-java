package modelo;

public class Usuario {
    // Atributos - representan las columnas de la tabla usuarios
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String rol;

    // Constructor vacío - crea un usuario sin datos
    public Usuario() {
    }

    // Constructor con parámetros - crea un usuario con datos
    public Usuario(String nombre, String email, String telefono, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Getters - obtener valores
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    // Setters - cambiar valores
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // toString - mostrar el objeto como texto
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}