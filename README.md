# SISTEMA PUNTO DE VENTA - PROYECTO SENA
## GA7-220501096-AA2-EV01 - Codificación de módulos del software según requerimientos del proyecto

### INFORMACIÓN DEL PROYECTO
- **Grupo:** NOVA348
- **Tecnologías:** Java 8+, MySQL, JDBC
- **IDE Utilizado:** IntelliJ IDEA

---

### REQUISITOS PREVIOS

#### Software necesario:
1. **Java JDK 8** o superior
2. **MySQL Server** (puerto 3307)
3. **XAMPP**
4. **IntelliJ IDEA**

#### Configuración de Base de Datos:
- **Host:** localhost
- **Puerto:** 3307
- **Base de datos:** proyecto_sena
- **Usuario:** root
- **Contraseña:** (vacía)

---

### INSTALACIÓN Y CONFIGURACIÓN

#### 1. Configurar MySQL:
```bash
1. Iniciar XAMPP Control Panel como administrador
2. Iniciar Apache y MySQL
3. Abrir phpMyAdmin: http://localhost/phpmyadmin
```

#### 2. Crear Base de Datos:
Ejecutar el siguiente script en phpMyAdmin:

```sql
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
```

#### 3. Configurar el Proyecto en IntelliJ:
1. **Importar proyecto:** File → Open → Seleccionar carpeta del proyecto
2. **Configurar librerías:**
    - File → Project Structure (F4)
    - Libraries → + → Java
    - Agregar: `lib/mysql-connector-j-9.4.0.jar`
    - Apply → OK

---

### CÓMO EJECUTAR EL PROGRAMA

#### Opción 1 - Desde IntelliJ IDEA:
1. Abrir el proyecto en IntelliJ
2. Navegar a: `src/main/MenuPrincipal.java`
3. Hacer clic en la flecha verde ▶ junto a `public static void main`
4. O presionar `Ctrl + Shift + F10`

#### Opción 2 - Desde línea de comandos:
```bash
# Compilar
javac -cp lib/mysql-connector-j-9.4.0.jar src/main/MenuPrincipal.java

# Ejecutar
java -cp lib/mysql-connector-j-9.4.0.jar:src main.MenuPrincipal
```

---

### CÓMO PROBAR LAS FUNCIONALIDADES

El sistema presenta un **menú interactivo** con las siguientes opciones:

#### 1. GESTIÓN DE USUARIOS (CRUD Completo):
- **Agregar usuario:** Permite insertar nuevos usuarios con roles
- **Listar usuarios:** Consulta y muestra todos los usuarios
- **Actualizar usuario:** Modifica datos de usuarios existentes
- **Eliminar usuario:** Borra usuarios de la base de datos

#### 2. GESTIÓN DE PRODUCTOS (CRUD Completo):
- **Agregar producto:** Inserta productos con precio y stock
- **Listar productos:** Consulta inventario disponible
- **Actualizar producto:** Modifica información de productos
- **Eliminar producto:** Remueve productos del inventario

#### 3. GESTIÓN DE VENTAS (CRUD Completo):
- **Nueva venta:** Proceso completo de venta con múltiples productos
- **Listar ventas:** Consulta histórico de ventas realizadas
- **Ver detalle:** Muestra productos vendidos en cada transacción

### EVIDENCIA DE FUNCIONALIDADES CRUD

#### CREATE (Inserción):
- Usuarios: `UsuarioDAO.insertar()`
- Productos: `ProductoDAO.insertar()`
- Ventas: `VentaDAO.insertar()`
- Detalles: `DetalleVentaDAO.insertar()`

#### READ (Consulta):
- Consultar todos: `consultarTodos()` en cada DAO
- Búsquedas específicas: `buscarPorId()`

#### UPDATE (Actualización):
- Modificación de registros: `actualizar()` en cada DAO
- Actualización automática de stock en ventas

#### DELETE (Eliminación):
- Eliminación individual: `eliminar(id)` en cada DAO
- Eliminación en cascada: `eliminarPorVenta()`

---

### ESTRUCTURA DEL PROYECTO

```
ProyectoSENA/
├── src/
│   ├── conexion/
│   │   └── ConexionBD.java          # Conexión JDBC a MySQL
│   ├── modelo/
│   │   ├── Usuario.java             # Entidad Usuario
│   │   ├── Producto.java            # Entidad Producto
│   │   ├── Venta.java               # Entidad Venta
│   │   └── DetalleVenta.java        # Entidad DetalleVenta
│   ├── dao/
│   │   ├── UsuarioDAO.java          # CRUD Usuarios
│   │   ├── ProductoDAO.java         # CRUD Productos
│   │   ├── VentaDAO.java            # CRUD Ventas
│   │   └── DetalleVentaDAO.java     # CRUD Detalles
│   └── main/
│       ├── MenuPrincipal.java       # Aplicación principal
│       └── PruebaUsuarios.java      # Archivo de prueba
├── lib/
│   └── mysql-connector-j-9.4.0.jar # Driver JDBC MySQL
└── README.md                        # Este archivo
```



---

### ESTÁNDARES DE CODIFICACIÓN IMPLEMENTADOS

#### Nomenclatura de Variables:
- Camel case: `usuarioId`, `precioUnitario`, `fechaCreacion`
- Descriptivas: `totalVenta`, `filasAfectadas`

#### Nomenclatura de Métodos:
- Verbos descriptivos: `insertar()`, `consultarTodos()`, `actualizar()`
- Camel case: `buscarPorId()`, `eliminarPorVenta()`

#### Nomenclatura de Clases:
- Pascal case: `ConexionBD`, `UsuarioDAO`, `MenuPrincipal`
- Sufijos descriptivos: `DAO` para acceso a datos

#### Nomenclatura de Paquetes:
- Lowercase: `conexion`, `modelo`, `dao`, `main`
- Organización lógica por funcionalidad

---

### SOPORTE

Si encuentra algún problema:
1. **Verificar conexión MySQL:** Ejecutar `src/conexion/ConexionBD.java`
2. **Revisar configuración de puerto:** Confirmar MySQL en puerto 3307
3. **Validar base de datos:** Verificar que `proyecto_sena` exista con las tablas

---

### TECNOLOGÍAS Y LIBRERÍAS

- **Java:** JDK 8+ con JDBC
- **Base de Datos:** MySQL 8.0+
- **Driver:** mysql-connector-j-9.4.0
- **Patrón:** DAO (Data Access Object)
- **Arquitectura:** MVC (Modelo-Vista-Controlador)