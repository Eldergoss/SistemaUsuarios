-- ======================================================
-- Sistema de Gestión de Usuarios
-- Schema de Base de Datos SQLite
-- ======================================================

-- Elimina la tabla si ya existe
DROP TABLE IF EXISTS users;

-- ======================================================
-- Tabla de usuarios
-- ======================================================

CREATE TABLE users (

    id INTEGER PRIMARY KEY AUTOINCREMENT,

    nombre TEXT NOT NULL,

    correo TEXT NOT NULL UNIQUE,

    contraseña TEXT NOT NULL,

    rol INTEGER NOT NULL DEFAULT 0

);

-- ======================================================
-- Datos de prueba
-- ======================================================

INSERT INTO users (nombre, correo, contraseña, rol)
VALUES
('Juan', 'juan@gmail.com', '1234', 0),

('Pedro', 'pedro@gmail.com', '1234', 1),

('Ana', 'ana@gmail.com', '1234', 2);

-- ======================================================
-- Roles
-- ======================================================
-- 0 = Usuario
-- 1 = Administrador
-- 2 = SuperAdministrador