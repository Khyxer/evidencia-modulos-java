package dao;

import conexion.ConexionBD;
import modelo.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    // insertar venta
    public int insertar(Venta venta) {
        String sql = "INSERT INTO ventas (usuario_id, total) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, venta.getUsuarioId());
            stmt.setDouble(2, venta.getTotal());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                // Obtener el ID
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int ventaId = generatedKeys.getInt(1);
                    System.out.println("Venta insertada con ID: " + ventaId);
                    return ventaId;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
        }
        return -1; // Error
    }

    // consultar todas las ventas
    public List<Venta> consultarTodos() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha DESC";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                venta.setTotal(rs.getDouble("total"));
                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar ventas: " + e.getMessage());
        }

        return ventas;
    }

    //Actualizar venta
    public boolean actualizar(Venta venta) {
        String sql = "UPDATE ventas SET usuario_id = ?, total = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venta.getUsuarioId());
            stmt.setDouble(2, venta.getTotal());
            stmt.setInt(3, venta.getId());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Venta actualizada correctamente");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar venta: " + e.getMessage());
        }
        return false;
    }


}