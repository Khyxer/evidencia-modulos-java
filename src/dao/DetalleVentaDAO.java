package dao;

import conexion.ConexionBD;
import modelo.DetalleVenta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// detalle venta guarda una fila por cada producto con su id de venta en lugar de guardar columnas por cada producto

public class DetalleVentaDAO {

    // Insertar detalle de venta
    public boolean insertar(DetalleVenta detalle) {
        String sql = "INSERT INTO detalle_ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getVentaId());
            stmt.setInt(2, detalle.getProductoId());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Detalle de venta insertado correctamente");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar detalle: " + e.getMessage());
        }
        return false;
    }

    // Consultar detalles por venta_id
    public List<DetalleVenta> consultarPorVenta(int ventaId) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_ventas WHERE venta_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ventaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setId(rs.getInt("id"));
                detalle.setVentaId(rs.getInt("venta_id"));
                detalle.setProductoId(rs.getInt("producto_id"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalle.setSubtotal(rs.getDouble("subtotal"));
                detalles.add(detalle);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar detalles por venta: " + e.getMessage());
        }

        return detalles;
    }


}