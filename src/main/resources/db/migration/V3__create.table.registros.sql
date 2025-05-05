CREATE TABLE registros (
                           id TEXT PRIMARY KEY UNIQUE NOT NULL,
                           registro TEXT NOT NULL,
                           data_registro TIMESTAMP,
                           usuario_id TEXT NOT NULL,
                           CONSTRAINT fk_registro_usuario FOREIGN KEY (usuario_id) REFERENCES users(id)
);
