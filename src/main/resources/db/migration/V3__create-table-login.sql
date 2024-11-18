CREATE TABLE logins (
id INTEGER AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
role VARCHAR(100) NOT NULL
);

INSERT INTO logins (user_name,password,role) VALUES ('admin','admin','ADMIN');