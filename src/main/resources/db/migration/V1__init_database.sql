CREATE TABLE users (
     id bigint NOT NULL AUTO_INCREMENT,
     full_name varchar(255) DEFAULT NULL,
     address varchar(255) DEFAULT NULL,
     username varchar(255) DEFAULT NULL,
     password varchar(255) DEFAULT NULL,
     roles varchar(255) DEFAULT NULL,
     PRIMARY KEY (id),
     UNIQUE KEY UKr43af9ap4edm43mmtq01oddj6 (username)
) ENGINE=InnoDB;

CREATE TABLE garments (
    id bigint NOT NULL AUTO_INCREMENT,
    type varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    size varchar(255) DEFAULT NULL,
    price double DEFAULT NULL,
    publisher_id bigint NOT NULL,
    PRIMARY KEY (id),
    KEY FKb7jgh3mur1a52evx8r2s3ot9a (publisher_id),
    CONSTRAINT FKb7jgh3mur1a52evx8r2s3ot9a FOREIGN KEY (publisher_id) REFERENCES users (id)
) ENGINE=InnoDB;
