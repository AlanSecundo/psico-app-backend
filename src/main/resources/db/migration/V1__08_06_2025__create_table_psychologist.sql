CREATE TABLE psychologist (
    id UUID PRIMARY KEY,
    keycloak_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    license_number VARCHAR(50),
    created_at TIMESTAMP DEFAULT NOW()
);
