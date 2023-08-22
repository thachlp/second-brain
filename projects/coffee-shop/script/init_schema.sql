DROP TABLE IF EXISTS category;
CREATE TABLE category (
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (category_id)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    product_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    image TINYTEXT,
    category_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    order_id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INT DEFAULT 0,
    price DECIMAL(10,2) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (order_id)
);

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE order_detail (
    order_detail_id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INT DEFAULT 0,
    price DECIMAL(10,2) NOT NULL,
    order_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY (order_detail_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    phone_number VARCHAR(15),
    address VARCHAR(255),
    role ENUM('CUSTOMER', 'ADMIN', 'STAFF') NOT NULL DEFAULT 'CUSTOMER'
);
