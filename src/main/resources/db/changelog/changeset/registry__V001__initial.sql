CREATE TABLE district
(
    id         BIGSERIAL PRIMARY KEY,
    code       BIGINT       NOT NULL,
    name       VARCHAR(255) UNIQUE NOT NULL,
    archived   BOOLEAN     DEFAULT false,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);

CREATE INDEX idx_district_name ON district(name);