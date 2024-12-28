DROP DATABASE IF EXISTS `movie-review`;
CREATE DATABASE `movie-review`;

USE `movie-review`;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX email_index (email)
)
COLLATE=utf8mb4_bin;

CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    content TEXT,
    rating DECIMAL NOT NULL,
    user_id INT NOT NULL,
    movie_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    INDEX movie_id_index (movie_id)
)
COLLATE=utf8mb4_bin;