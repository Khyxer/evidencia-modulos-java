package main;

import dao.UsuarioDAO;
import modelo.Usuario;
import java.util.List;

public class PruebaUsuarios {

    public static void main(String[] args) {
        System.out.println("🚀 === PROBANDO SISTEMA DE USUARIOS === 🚀");

        // Crear objeto para manejar usuarios
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // INSERTAR
        System.out.println("\n1️⃣ === INSERTANDO USUARIO ===");
        Usuario nuevoUsuario = new Usuario("Pedro Cajero", "pedro@sena.edu.co", "3001112233", "cajero");
        usuarioDAO.insertar(nuevoUsuario);

        // CONSULTAR
        System.out.println("\n2️⃣ === CONSULTANDO USUARIOS ===");
        List<Usuario> usuarios = usuarioDAO.consultarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en la base de datos");
        } else {
            System.out.println("Usuarios encontrados: " + usuarios.size());
            System.out.println("─".repeat(80));
            for (Usuario u : usuarios) {
                System.out.printf("ID: %d | %-20s | %-25s | %-12s | %s%n",
                        u.getId(), u.getNombre(), u.getEmail(), u.getTelefono(), u.getRol());
            }
        }

        System.out.println("\n Fin de la prueba");
    }
}