--CREATE DATABASE Announcement-System;

DROP TABLE IF EXISTS paid;
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
category_id UUID NOT NULL,
creator_id UUID NOT NULL,
customer_id UUID,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
closed_at TIMESTAMP,
status VARCHAR(50) DEFAULT 'ACTIVE',
FOREIGN KEY (creator_id) REFERENCES users(id),
FOREIGN KEY (customer_id) REFERENCES users(id),
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
date_of_creating TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
date_of_editing TIMESTAMP,
sender_id UUID,
announcement_id UUID,
FOREIGN KEY (sender_id) REFERENCES users(id),
FOREIGN KEY (announcement_id) REFERENCES announcement(id)
);

CREATE TABLE paid ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
paid_from TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
paid_to TIMESTAMP,
announcement_id UUID,
FOREIGN KEY (announcement_id) REFERENCES announcement(id)
);

CREATE TABLE grade ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
grade SMALLINT CHECK (grade between 0 and 10),
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

INSERT INTO announcement (body, price, category_id, creator_id) 
values('dark car', 200, '3a9bfc2c-ebbb-4077-b668-66aef6b4a793', '96b2d3c2-7a28-4651-949f-8793e7023f4c');

select * from roles;
select * from users;
select * from category;
select * from announcement;
select * from grade;
select * from paid;
select * from comment;

INSERT INTO grade (grade, sender_id, receiver_id) 
values(7, 'f3705439-804c-456c-8d84-f959b4f1b79b', '96b2d3c2-7a28-4651-949f-8793e7023f4c');
INSERT INTO grade (grade, sender_id, receiver_id) 
values(8, '96b2d3c2-7a28-4651-949f-8793e7023f4c', 'f3705439-804c-456c-8d84-f959b4f1b79b');

insert into comment (body, sender_id, announcement_id)
values ('comment1', '044ee757-8b09-414e-a2c4-7e952fb5a6e3', '6ad5b920-1c36-4336-bc3a-17e020c17823');

select u.id, u.first_name, u.surname, avg(g.grade) from Users u 
LEFT JOIN Grade g ON u.id = g.receiver_id  GROUP BY u.id ;

select a.id, a.body, a.price, avg(g.grade) from Announcement a 
LEFT outer JOIN category c ON a.category_id = c.id 
left outer join Grade g ON a.creator_id = g.receiver_id 
left outer join paid p ON a.id = p.announcement_id 
WHERE c.name = 'car' and a.price < 1000
and a.status = 'ACTIVE'
GROUP BY a.id, p.paid_from, p.paid_to
order by 
CASE WHEN p.paid_from < CURRENT_TIMESTAMP and p.paid_to > CURRENT_TIMESTAMP THEN p.paid_to end asc,
avg(g.grade) desc;

INSERT INTO paid (announcement_id, paid_to) 
values('6ad5b920-1c36-4336-bc3a-17e020c17823', '2022-05-06');





