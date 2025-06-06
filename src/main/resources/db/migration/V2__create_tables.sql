
CREATE SEQUENCE IF NOT EXISTS request_id_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS request_history_id_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS requests (
                                        id BIGINT PRIMARY KEY DEFAULT nextval('request_id_sequence'),
    title VARCHAR(255),
    content TEXT,
    status INTEGER,
    creator_name VARCHAR(255),
    user_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_request_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
    );

CREATE TABLE IF NOT EXISTS request_history (
                                               id BIGINT PRIMARY KEY DEFAULT nextval('request_history_id_sequence'),
    status INTEGER,
    message TEXT,
    worker_name VARCHAR(255),
    request_id BIGINT,
    updated_at TIMESTAMP,
    CONSTRAINT fk_history_request FOREIGN KEY (request_id) REFERENCES requests (id) ON DELETE CASCADE
    );


ALTER SEQUENCE request_id_sequence OWNED BY requests.id;
ALTER SEQUENCE request_history_id_sequence OWNED BY request_history.id;
