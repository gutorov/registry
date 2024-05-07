CREATE TABLE district
(
    id         BIGSERIAL PRIMARY KEY,
    code       BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    archived   BOOLEAN     DEFAULT false,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);