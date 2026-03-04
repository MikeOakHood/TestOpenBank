-- Crear la tabla de salas
CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Crear la tabla de usuarios
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    dni VARCHAR(15) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    role VARCHAR(50) NOT NULL,
    room_id INTEGER,
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE SET NULL
);

-- Insertar datos iniciales
INSERT INTO rooms (name) VALUES ('Sala 1'), ('Sala 2');


INSERT INTO users (name, email, dni, phone, role, room_id) VALUES
('Juan Pérez', 'juan@example.com', '11111111H', '34123456789', 'ADMIN',1),
('María López', 'maria@example.com', '22222222J', '34012345678', 'SUPERADMIN', 2);


-- INSERT INTO users (name, email, role, room_id) VALUES
-- ('Juan Pérez', 'juan@example.com', 'ADMIN', 1),
-- ('María López', 'maria@example.com', 'USER', 2);
