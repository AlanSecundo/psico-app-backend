CREATE TABLE IF NOT EXISTS representative (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL REFERENCES patient(id) ON DELETE CASCADE,
    full_name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    relationship VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(255),
    is_legal_responsible BOOLEAN DEFAULT FALSE,
    is_financial_responsible BOOLEAN DEFAULT FALSE
);
