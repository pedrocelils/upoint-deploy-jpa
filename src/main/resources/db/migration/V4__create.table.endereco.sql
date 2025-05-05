CREATE TABLE endereco (
                          id TEXT PRIMARY KEY UNIQUE NOT NULL,
                          logradouro TEXT NOT NULL,
                          numero TEXT NOT NULL,
                          complemento TEXT,
                          bairro TEXT NOT NULL,
                          cidade TEXT NOT NULL,
                          uf TEXT NOT NULL,
                          cep TEXT NOT NULL
);
