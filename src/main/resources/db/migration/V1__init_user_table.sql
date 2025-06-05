CREATE SEQUENCE IF NOT EXISTS user_id_sequence START WITH 1 INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
    );

ALTER SEQUENCE IF EXISTS user_id_sequence OWNED BY users.id;

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$Fj8tPl2dOsyFQeU2ZqUjR.jvtxQv93rIaCVLuBtb0rZnHzlLVkwSG', 'ROLE_ADMIN');
