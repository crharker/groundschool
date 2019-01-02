DROP TABLE IF EXISTS USER;
CREATE TABLE USER (id bigint not null auto_increment, role varchar(10) not null, username varchar(255) not null, password varchar(255) not null, first_name varchar(50), last_name varchar(100), email varchar(255), primary key (id)) engine=MyISAM;
INSERT INTO USER (id, role, username, password, first_name, last_name) VALUES (1, 'ADMIN', 'admin', '$2a$10$nVtuKyUUeePzUvSnb1brW.yKYk2poTVyhig/AUUsUxZcssK8QmrUy', 'admin', 'user');
