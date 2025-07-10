CREATE TABLE IF NOT EXISTS initial_assessment (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL UNIQUE REFERENCES customer(id) ON DELETE CASCADE,
    main_complaint TEXT,
    referred_by VARCHAR(255),
    psychological_history TEXT,
    medication_use TEXT,
    medical_diagnosis TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);
