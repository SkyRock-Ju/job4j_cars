CREATE TABLE auto_users
(
    id   SERIAL PRIMARY KEY,
    login VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL
);