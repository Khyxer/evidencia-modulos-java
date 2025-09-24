//conectar con la base de datos MySQL
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3307/proyecto_sena"; // yo ya tenia MySQL usando el puerto 3306 por eso lo cambie al 3307
    private static final String USER = "root";
    private static final String PASS = "";

    // método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        //System.out.println("Conexión establecida"); cada que se llamaba a este metodo imprimia en consola ese mensaje se volvia muy molesto y no aportaba nada
        return conn;
    }
// probar que la conexion sea exitosa
//    public static void main(String[] args) {
//        System.out.println("=== PROBANDO CONEXIÓN ===");
//        try {
//            Connection conn = getConnection();
//            System.out.println("¡Conexión exitosa!");
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
}
