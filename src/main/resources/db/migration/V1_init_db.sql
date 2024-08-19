CREATE TABLE IF NOT EXISTS Note(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR(1000)
);
INSERT INTO Note(title, content) VALUES ('Title', 'Content');
