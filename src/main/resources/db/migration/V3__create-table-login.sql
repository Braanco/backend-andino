CREATE TABLE logins (
id INTEGER PRIMARY KEY,
user_name VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
status VARCHAR(20)
);

INSERT INTO logins (id,user_name,password,status) VALUES (1,'gerente','gerente','GERENTE');
INSERT INTO logins (id,user_name,password,status) VALUES (2,'vendedor','vendedor','VENDEDOR');
