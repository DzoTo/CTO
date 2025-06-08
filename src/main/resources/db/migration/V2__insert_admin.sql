-- Insert admin
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$/ZOiy7OWRFLFwlUlOdKNouCdU89Q0fQJz89AANwsLN.C.u8bIXgBq', 'ROLE_ADMIN');

-- Sync the sequence
SELECT setval('user_id_sequence', (SELECT MAX(id) FROM users));