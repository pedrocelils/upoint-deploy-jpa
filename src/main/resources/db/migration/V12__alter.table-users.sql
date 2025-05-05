ALTER TABLE users
    DROP CONSTRAINT IF EXISTS users_empresa_id_fkey;
ALTER TABLE users
    ALTER COLUMN empresa_id TYPE TEXT USING empresa_id::text;