CREATE TABLE owners
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    user_id int NOT NULL UNIQUE REFERENCES auto_users(id)
);