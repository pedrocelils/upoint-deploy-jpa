ALTER TABLE users
    ADD CONSTRAINT fk_users_empresa
        FOREIGN KEY (empresa_id) REFERENCES empresa(id);
