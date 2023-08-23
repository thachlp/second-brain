DROP TABLE IF EXISTS category;
CREATE TABLE category
(
    id         BIGINT NOT NULL auto_increment,
    name       VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id          BIGINT NOT NULL auto_increment,
    name        VARCHAR(255) NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    description TEXT,
    image       TINYTEXT,
    category_id BIGINT NOT NULL,
    created_at  DATETIME,
    updated_at  DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    id         BIGINT NOT NULL auto_increment,
    quantity   INT DEFAULT 0,
    price      DECIMAL(10, 2) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE order_detail
(
    id         BIGINT NOT NULL auto_increment,
    quantity   INT DEFAULT 0,
    price      DECIMAL(10, 2) NOT NULL,
    order_id   BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES `order`(id)
);

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE user_info
(
    id           INT auto_increment PRIMARY KEY,
    username     VARCHAR(50) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    full_name    VARCHAR(100),
    phone_number VARCHAR(15),
    address      VARCHAR(255),
    created_at   DATETIME,
    updated_at   DATETIME,
    role         ENUM('CUSTOMER', 'ADMIN', 'STAFF') NOT NULL DEFAULT 'CUSTOMER'
);