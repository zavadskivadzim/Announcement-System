--CREATE DATABASE Announcement-System;

DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE category ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
name VARCHAR(50) NOT NULL unique
);

CREATE TABLE roles ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
login VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(200),
first_name VARCHAR(50),
surname VARCHAR(50),
birthday DATE,
email VARCHAR(50),
role_id UUID,
FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE announcement (
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
body VARCHAR(500) NOT NULL,
price NUMERIC NOT NULL,
--rating INT,
category_id UUID NOT NULL,
creator_id UUID NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
closed_at TIMESTAMP,
status VARCHAR(50) DEFAULT 'ACTIVE',
FOREIGN KEY (creator_id) REFERENCES users(id),
FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE message ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
body VARCHAR(500) NOT NULL,
sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
sender_id UUID,
receiver_id UUID,
FOREIGN KEY (sender_id) REFERENCES users(id),
FOREIGN KEY (receiver_id) REFERENCES users(id)
);

CREATE TABLE comment ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
body VARCHAR(500) NOT NULL,
commented_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
sender_id UUID,
announcement_id UUID,
FOREIGN KEY (sender_id) REFERENCES users(id),
FOREIGN KEY (announcement_id) REFERENCES announcement(id)
);

CREATE TABLE grade ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
grade SMALLINT CHECK (grade between 0 and 10 ),
sender_id UUID,
receiver_id UUID,
FOREIGN KEY (sender_id) REFERENCES users(id),
FOREIGN KEY (receiver_id) REFERENCES users(id),
check (sender_id != receiver_id),
unique (sender_id, receiver_id)
);

INSERT INTO roles (name) values('ROLE_ADMIN');
INSERT INTO roles (name) values('ROLE_USER');

INSERT INTO category (name) values('bike');
INSERT INTO category (name) values('car');
INSERT INTO category (name) values('bus');

INSERT INTO announcement (body, price, creator_id) 
values('ddd', 120, 'efd14c01-50ff-4cad-a747-a51deb72562b');

select * from roles;
select * from users;
select * from category;
select * from announcement;
select * from grade;

INSERT INTO grade (grade, sender_id, receiver_id) 
values(9, 'efd14c01-50ff-4cad-a747-a51deb72562b', '9bd422f9-d902-448a-881d-a3685fd7c066');
INSERT INTO grade (grade, sender_id, receiver_id) 
values(8, '9bd422f9-d902-448a-881d-a3685fd7c066', 'efd14c01-50ff-4cad-a747-a51deb72562b');

select u.id, u.first_name, u.surname, avg(g.grade) from Users u LEFT JOIN Grade g ON u.id = g.receiver_id  GROUP BY u.id  


