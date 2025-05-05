CREATE TABLE users (
                       id TEXT PRIMARY KEY UNIQUE NOT NULL,
                       login TEXT NOT NULL UNIQUE,
                       password TEXT NOT NULL,
                       role TEXT NOT NULL,
                       cpf TEXT UNIQUE NOT NULL,
                       email TEXT NOT NULL,
                       nome TEXT NOT NULL,
                       telefone TEXT NOT NULL
);