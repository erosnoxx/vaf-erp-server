CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE routes (
    id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ
);

CREATE TABLE cities (
    id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(7) NOT NULL UNIQUE,
    state VARCHAR(2) NOT NULL,
    route_id UUID NOT NULL REFERENCES routes(id),
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ
);
