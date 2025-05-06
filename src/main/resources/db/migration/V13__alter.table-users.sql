ALTER TABLE users
    ADD CONSTRAINT users_empresa_id_fkey
        FOREIGN KEY (empresa_id) REFERENCES empresa(id);