CREATE TABLE owners
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    user_id int UNIQUE NOT NULL REFERENCES auto_users(id)
);