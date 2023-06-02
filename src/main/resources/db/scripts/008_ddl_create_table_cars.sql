CREATE TABLE cars
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    engine_id int NOT NULL UNIQUE REFERENCES engines(id)
);