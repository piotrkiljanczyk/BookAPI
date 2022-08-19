CREATE TABLE BookAPI.log (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    record VARCHAR (300) NOT NULL
);

CREATE TABLE BookAPI.books (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR (30),
    title VARCHAR (300),
    author VARCHAR (150),
    publisher VARCHAR (100),
    type VARCHAR (50)
);

INSERT INTO books VALUES
                      (1, '978-83-287-0078-9', 'Władca Pierścieni', 'J.R.R. Tolkien', 'Muza', 'Fantasy')