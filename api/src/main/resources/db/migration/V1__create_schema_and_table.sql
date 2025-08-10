CREATE SCHEMA IF NOT EXISTS stocks;

CREATE TABLE stocks.ticker (
    updated_at date,
    code varchar(10),
    name text,
    market text,
    sector_code text,
    sector text,
    subsector_code text,
    subsector text,
    scale_code text,
    index_name text
);
