CREATE TABLE IF NOT EXISTS payment_details (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL UNIQUE REFERENCES patient(id) ON DELETE CASCADE,
    payment_method VARCHAR(50),
    session_value NUMERIC(10, 2),
    session_frequency VARCHAR(50),
    fixed_schedule VARCHAR(100),
    health_plan VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);
