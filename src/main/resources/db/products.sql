-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.9-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla store.products
CREATE TABLE IF NOT EXISTS `products` (
  `section` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` double(5,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `imported` varchar(50) DEFAULT NULL,
  `origin_country` varchar(50) DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla store.products: ~14 rows (aproximadamente)
DELETE FROM `products`;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`section`, `name`, `price`, `created_date`, `imported`, `origin_country`, `updated_date`, `product_id`) VALUES
	('SPORTS', 'Ball', 50.00, '2007-07-24', 'NO', 'ESPANIA', '2022-01-08', 1),
	('CLOTHING', 'Shirt', 69.99, '2021-05-30', 'FALSO', 'ITALIA', '2022-01-08', 2),
	('GAMES', 'Monkey Island 3', 16.99, '2021-05-30', 'SI', 'JAPAN', '2022-01-08', 3),
	('SPORTS', 'Weigth', 40.66, '1988-02-24', 'SI', 'ITALIA', '2022-01-08', 4),
	('SECURITY', 'Helmet', 55.00, '2020-03-12', 'NO', 'ESPANIA', '2022-01-08', 5),
	('HOME', 'Sponge', 200.00, '2014-02-12', 'NO', 'ESPANIA', '2022-01-08', 6),
	('GAMES', 'Metal Gear Solid', 100.99, '2021-02-12', 'SI', 'CHINA', '2022-01-08', 7),
	('FORNITURE', 'Table', 100.23, '2001-02-10', 'VERDADERO', 'ESPAÑA', '2022-01-08', 8),
	('FORNITURE', 'Chair', 80.23, '2001-02-10', 'VERDADERO', 'ITALIA', '2022-01-08', 9),
	('TOYS', 'Plate', 300.23, '2001-02-10', 'FALSO', 'ESPAÑA', '2022-01-08', 10),
	('IRON', 'Keys', 500.23, '2001-02-10', 'VERDADERO', 'MORROCO', '2022-01-08', 11),
	('SPORTS', 'Glass', 50.23, '2001-02-10', 'FALSO', 'PORTUGAL', '2022-01-08', 12),
	('TOYS', 'Fork', 2.23, '2001-02-10', 'VERDADERO', 'ESPAÑA', '2022-01-08', 13),
	('GAMES', 'Computer', 9.23, '2001-02-10', 'VERDADERO', 'ESPAÑA', '2022-01-08', 14);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
