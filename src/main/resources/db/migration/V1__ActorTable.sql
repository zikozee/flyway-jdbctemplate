CREATE  TABLE actor(
    id BIGSERIAL primary key,
    name TEXT NOT NULL,
    movie BIGINT REFERENCES movie (id),
    unique (name, movie)
)