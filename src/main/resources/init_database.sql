CREATE DATABASE diploma_work;

CREATE TABLE country_currency (
    id bigserial PRIMARY KEY,
    country VARCHAR(255),
    country_code VARCHAR(255),
    currency VARCHAR(255),
    currency_code VARCHAR(255)
);