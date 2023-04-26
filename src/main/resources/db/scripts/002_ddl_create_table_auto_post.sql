CREATE TABLE auto_posts
(
    id   SERIAL PRIMARY KEY,
    description VARCHAR NOT NULL,
    created TIMESTAMP NOT NULL,
    auto_user_id INT REFERENCES auto_users (id) NOT NULL
);