CREATE SEQUENCE IF NOT EXISTS books_id_seq;

CREATE TABLE IF NOT EXISTS books (
	id INTEGER DEFAULT nextval('books_id_seq') PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255),
	publish_date DATE,
	price INTEGER
);
