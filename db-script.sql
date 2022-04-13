DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS category;
DROP TABLE IF exists user_role;
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
password VARCHAR(200) NOT NULL,
first_name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
birthday DATE NOT null,
rating INT
);

CREATE TABLE announcement ( 
id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
body VARCHAR(500) NOT NULL,
price NUMERIC NOT NULL,
rating INT,
category_id UUID NOT NULL,
creator_id UUID NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
closed_at TIMESTAMP,
status VARCHAR(50) NOT NULL,
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

CREATE TABLE user_role ( 
user_id UUID NOT NULL,
role_id UUID NOT NULL,
primary key (user_id, role_id),
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (role_id) REFERENCES roles(id)
);
