use newsservlet;

CREATE TABLE role(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createddate TIMESTAMP NULL,
    modìfieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE user(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NULL,
    status int NOT NULL,
    roleid BIGINT NOT NULL,
    createddate TIMESTAMP NULL,
    modìfieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);

CREATE TABLE news(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    title VARCHAR(255) NULL,
    thumbnail VARCHAR(255) NULL,
    shortdescription TEXT NULL,
    content TEXT NULL,
    categoryid BIGINT NOT NULL,
    createddate TIMESTAMP NULL,
    modìfieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

CREATE TABLE category(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createddate TIMESTAMP NULL,
    modìfieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id);

CREATE TABLE comment(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    content TEXT NULL,
    user_id BIGINT NOT NULL,
    news_id BIGINT NOT NULL,
    createddate TIMESTAMP NULL,
    modìfieddate TIMESTAMP NULL,
    createdby VARCHAR(255) NULL,
    modifiedby VARCHAR(255) NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (news_id) REFERENCES news(id);