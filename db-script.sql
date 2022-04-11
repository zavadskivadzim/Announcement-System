DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS comment_table;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS role_table;
DROP TABLE IF exists user_role;

CREATE TABLE role_table ( 
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_table (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, 
first_name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
birthday DATE NOT null,
role_id INT
);

CREATE TABLE announcement ( 
id INT GENERATED ALWAYS AS IDENTITY PRIMARY key,
creator INT NOT null,
FOREIGN KEY (creator) REFERENCES user_table(id)
);

CREATE TABLE message ( 
id INT GENERATED ALWAYS AS IDENTITY PRIMARY key,
sender_id INT,
receiver_id INT,
FOREIGN KEY (sender_id) REFERENCES user_table(id),
FOREIGN KEY (receiver_id) REFERENCES user_table(id)
);

CREATE TABLE comment_table ( 
id INT GENERATED ALWAYS AS IDENTITY PRIMARY key,
sender_id INT,
announcement_id INT,
FOREIGN KEY (sender_id) REFERENCES user_table(id),
FOREIGN KEY (announcement_id) REFERENCES announcement(id)
);

CREATE TABLE user_role ( 
user_id INT,
role_id INT,
FOREIGN KEY (user_id) REFERENCES user_table(id),
FOREIGN KEY (role_id) REFERENCES role_table(id)
);

