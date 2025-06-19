CREATE TABLE psychologist (
    id UUID PRIMARY KEY,
    external_id VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    crp VARCHAR(50),
    phone VARCHAR(20),
    on_board_completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    edited_at TIMESTAMP
);