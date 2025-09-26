-- Script de Base de Datos - Sistema Punto de Venta SENA
-- GA7-220501096-AA2-EV01

CREATE DATABASE IF NOT EXISTS proyecto_sena;
USE proyecto_sena;

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    rol ENUM('cajero', 'supervisor', 'admin') DEFAULT 'cajero'
);

-- Tabla productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT UNSIGNED DEFAULT 0
);

-- Tabla ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabla detalle_ventas
CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Datos de prueba
INSERT INTO usuarios (nombre, email, telefono, rol) VALUES
('Admin SENA', 'admin@sena.edu.co', '3001234567', 'admin'),
('Juan Cajero', 'juan.cajero@sena.edu.co', '3007654321', 'cajero'),
('María Supervisora', 'maria.super@sena.edu.co', '3009876543', 'supervisor');

INSERT INTO productos (nombre, precio, stock) VALUES
('Coca Cola 500ml', 2500.00, 50),
('Pan Tajado', 3200.00, 25),
('Leche 1L', 4500.00, 30),
('Huevos x12', 6800.00, 15);