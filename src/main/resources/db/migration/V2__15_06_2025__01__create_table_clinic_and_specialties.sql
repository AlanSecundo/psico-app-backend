CREATE TABLE IF NOT EXISTS clinic_profile (
    id UUID PRIMARY KEY,
    psychologist_id UUID UNIQUE NOT NULL REFERENCES psychologist(id) ON DELETE CASCADE,
    clinic_name VARCHAR(255),
    care_type VARCHAR(20) NOT NULL CHECK (care_type IN ('IN_PERSON', 'ONLINE', 'HIBRID')),
    clinic_status VARCHAR(50) NOT NULL CHECK (
        clinic_status IN (
            'INITIAL',
            'FEW_PATIENTS',
            'REGULAR_CAN_GROW',
            'FULL_SCHEDULE',
            'EXPANSION'
        )
    ),
    session_price NUMERIC(10,2) NOT NULL
);


CREATE TABLE IF NOT EXISTS specialty (
    id UUID PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS clinic_profile_specialty (
    clinic_profile_id UUID NOT NULL REFERENCES clinic_profile(id) ON DELETE CASCADE,
    specialty_id UUID NOT NULL REFERENCES specialty(id) ON DELETE CASCADE,
    PRIMARY KEY (clinic_profile_id, specialty_id)
);