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
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `lettadb`.`users` (
	`login` varchar(10) NOT NULL,
	`password` varchar(64) NOT NULL,
	`role` varchar(10) NOT NULL,
	PRIMARY KEY (`login`)
) CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `lettadb`.`assistants` (
	`id_event` int NOT NULL,
	`id_user` varchar(10) NOT NULL,
	PRIMARY KEY (`id_event`, `id_user`)
) CHARACTER SET utf8 COLLATE utf8_general_ci; 

-- CREATE USER IF NOT EXISTS 'letta'@'%' IDENTIFIED  BY 'letta';
-- GRANT ALL PRIVILEGES ON lettadb.* TO 'letta'@'%' WITH GRANT OPTION;

CREATE USER IF NOT EXISTS 'letta'@'localhost' IDENTIFIED WITH mysql_native_password BY 'letta';
GRANT ALL ON `lettadb`.* TO 'letta'@'localhost';

INSERT INTO `lettadb`.`events` (`id`, `title`, `description`, `category`, `location`, `capacity`, `event_date`, `creation_date`, `duration`,`creator`) VALUES
(1, 'Reunión GoT', 'Nos reunimos para criticar la última temporada de Juego de Tronos', 'Series', 'Bar Antonio', 20, '2021-04-25 22:00:00', '2020-04-14 13:00:00', 120, 'jose' ),
(2, 'Charlas de fútbol', 'Comentamos como sería el mercado de fichajes después del coronavirus', 'Deportes', 'SportsBar Gol', 30, '2021-04-20 22:00:00', '2020-04-15 20:30:00', 120, 'pepe'),
(3, 'Charla FIFA', 'Un pequeño tiempo libre por si a alguien le interesa compartir información sobre su equipo en FIFA FUT', 'Videojuegos', 'Campus de Ourense', 6, '2021-04-21 12:45:00', '2020-04-16 19:30:00', 30, 'maria'),
(4, 'Castelao', 'Charla para lectores para comentar la vida, obra e influencia en la literatura del autor de Rianxo', 'Literatura', 'Café Central', 10, '2021-05-01 17:00:00', '2020-04-22 21:30:00', 180, 'luisa'),
(5, 'O que arde', 'Una propuesta diferente y única o una decepción? La nueva pelécula de Óliver Laxe ha dado mucho que hablar por su ritmo desinteresado y poco común y su enfoque político. Invitamos a los amantes del cine a que comenten esta película tomando un buen café en un ambiente agradable.', 'Cine', 'Charlotte', 12, '2021-05-05 19:00:00', '2020-04-28 20:15:00', 120, 'pepe'),
(6, 'I Love Reggaeton', 'Una noche de viernes para comentar el nuevo disco de bad bunny antes de ir a perrar', 'Musica', 'Ceda el Vaso', '30', '2021-04-10 23:30:00', '2020-04-08 20:15:00', '180', 'luisa'),
(7, 'Rato libre', 'Un rato libre entre clase y clase para hablar de todas las problematicas de la universidad', 'Otro', 'Cafetería politécnica', '5', '2021-04-27 12:30:00', '2020-04-26 23:17:00', '30', 'maria'),
(8, 'Viaje a casa', 'Tengo tiempo libre en el bus que me lleva hasta a Valenzá desde el campus. Preferiría hablar de la casa de papel pero me gustan todas las series', 'Series', 'Bus urbano 5', '2', '2021-04-29 13:30:00', '2020-04-29 13:00:00', '15', 'jose'),
(9, 'Reunión simracer', 'Quedada de amantes de los juegos de simulación automovilística(Asetto corsa, Gran Turismo, Dirt rally y F1) para comentar el hardware utilizado, juegos, reglajes, trucos, organizar eventos y cualquier cosa que se les ocurra ', 'Videojuegos', 'Bar Mclaren', 20, '2021-03-29 22:30:00', '2020-03-21 20:15:00', 120, 'luisa'),
(10, 'Música entre horas', 'Rato libre para hablar sobre música electrónica entre turnos de trabajo', 'Musica', 'Plaza San Lázaro', 5, '2019-12-15 12:30:00', '2019-12-14 20:15:00', 30, 'luisa'),
(11, 'La gran ópera', 'Retransmisión en dircto del estreno de las nuevas obras de teatro en el maravilloso teatro de Viena. Disfrútala con nosotros y comparte tu opinión con amantes del género', 'Teatro', 'Café central', 30, '2021-06-30 20:15:00', '2020-04-08 15:00:00', 180, 'maria'),
(12, 'Hablar de F1', 'Después de trabajar, con una cerveza en un bar con buen ambiente, me gustaría encontrar gente con la que hablar de mi gran pasión, la Formula 1, y recordar carreras, pilotos y coches históricos. Sois mas de Lauda o de Hunt? De Senna o de Prost?', 'Deportes', 'Bar Bekas', 8, '2021-04-29 20:30:00', '2020-04-29 19:15:00', 75, 'pepe');


INSERT INTO `lettadb`.`users`(`login`, `password`, `role`) VALUES
('jose', '713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca', 'normal'),
('pepe', '713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca', 'normal'),
('maria', ' ', 'normal'),
('lucia', '', 'normal'),
('david', '', 'normal'),
('antonio', '', 'normal'),
('alba', '', 'normal'),
('luisa', '', 'normal');

INSERT INTO `lettadb`.`assistants`(`id_event`, `id_user`) VALUES
(1,'pepe'),
(1,'jose'),
(1,'maria'),
(1,'luisa'),
(2, 'pepe'),
(3, 'lucia'),
(3, 'jose'),
(3, 'pepe'),
(3,'maria'),
(3, 'antonio'),
(3, 'alba'),
(4, 'pepe'),
(4, 'jose'),
(4, 'luisa'),
(5, 'luisa'),
(5, 'pepe'),
(6, 'maria'),
(6, 'luisa'),
(6, 'antonio'),
(7, 'maria'),
(8, 'jose'),
(9, 'luisa'),
(10, 'luisa'),
(10, 'alba'),
(10, 'jose'),
(10, 'antonio'),
(11, 'maria'),
(11, 'alba'),
(12, 'pepe'),
(12, 'maria');


