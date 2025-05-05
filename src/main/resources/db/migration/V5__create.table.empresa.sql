CREATE TABLE empresa (
                         id TEXT PRIMARY KEY UNIQUE NOT NULL,
                         razao_social TEXT NOT NULL,
                         nome_fantasia TEXT NOT NULL,
                         cnpj TEXT NOT NULL UNIQUE,
                         email TEXT NOT NULL,
                         telefone TEXT NOT NULL,
                         endereco_id TEXT REFERENCES endereco(id)
);
