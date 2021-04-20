DROP DATABASE IF EXISTS `lettadb`;
CREATE DATABASE `lettadb` CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `lettadb`.`events` (
	`id` int NOT NULL AUTO_INCREMENT,
	`title` varchar(20) NOT NULL,
	`description` varchar(5000),
	`category` varchar(20) NOT NULL,
	`location` varchar(30) NOT NULL,
	`capacity` int NOT NULL,
	`event_date` DATETIME NOT NULL,
	`creation_date` DATETIME NOT NULL,
	`duration` int NOT NULL,
	`creator` varchar(10) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `lettadb`.`users` (
	`login` varchar(10) NOT NULL,
	`password` varchar(64) NOT NULL,
	`role` varchar(10) NOT NULL,
	PRIMARY KEY (`login`),
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `lettadb`.`assistants` (
	`id_event` int NOT NULL,
	`id_user` varchar(10) NOT NULL,
	PRIMARY KEY (`id_event`, `id_user`)
); 

CREATE USER IF NOT EXISTS 'letta'@'localhost' IDENTIFIED WITH mysql_native_password BY 'letta';
GRANT ALL ON `lettadb`.* TO 'letta'@'localhost';