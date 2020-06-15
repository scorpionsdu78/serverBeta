-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           10.5.3-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour stage
CREATE DATABASE IF NOT EXISTS `stage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stage`;

-- Listage de la structure de la table stage. article
CREATE TABLE IF NOT EXISTS `article` (
  `idarticle` int(11) NOT NULL AUTO_INCREMENT,
  `publication` date DEFAULT NULL,
  `content` varchar(45) NOT NULL,
  `titre` varchar(45) NOT NULL,
  `featured` int(11) NOT NULL,
  `creation` date NOT NULL,
  PRIMARY KEY (`idarticle`),
  UNIQUE KEY `idarticle_UNIQUE` (`idarticle`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.article : ~7 rows (environ)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`idarticle`, `publication`, `content`, `titre`, `featured`, `creation`) VALUES
	(2, '2012-01-02', 'test de changement whallah', 'new title', 1, '2011-01-01'),
	(3, '2020-01-09', 'on a plein de piece', 'nos piece', 1, '2020-01-08'),
	(4, NULL, 'lalalilaloulonisu', 'lsd', 0, '2019-11-23'),
	(5, NULL, 'lalalilaloulonisu', 'lsd', 0, '2019-11-23'),
	(6, '2018-12-31', 'test d\'insert tout nouveau', 'yes?', 1, '2020-06-08'),
	(7, '2019-12-30', 'test avec photo', 'test avec photo', 1, '2020-06-08'),
	(8, '2019-12-30', 'thus  us  sss', 'test2', 1, '2020-06-08');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- Listage de la structure de la table stage. engagement
CREATE TABLE IF NOT EXISTS `engagement` (
  `idengagement` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `description` varchar(45) NOT NULL,
  `places` int(11) DEFAULT NULL,
  `dates` date NOT NULL,
  PRIMARY KEY (`idengagement`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.engagement : ~2 rows (environ)
/*!40000 ALTER TABLE `engagement` DISABLE KEYS */;
INSERT INTO `engagement` (`idengagement`, `email`, `telephone`, `description`, `places`, `dates`) VALUES
	(1, 'fboni.78@gmail.com', '0636009630', 'on veut vous engager', 1523654, '2056-01-23'),
	(2, 'francois.boni@efrei.net', '0130719630', 'y\'a de l\'arget a la cle', 2, '2019-10-07');
/*!40000 ALTER TABLE `engagement` ENABLE KEYS */;

-- Listage de la structure de la table stage. membre
CREATE TABLE IF NOT EXISTS `membre` (
  `idMembre` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `description` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`idMembre`),
  UNIQUE KEY `description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.membre : ~3 rows (environ)
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
INSERT INTO `membre` (`idMembre`, `nom`, `prenom`, `description`) VALUES
	(1, 'boni', 'mariano', 'commedient, metteur en scene '),
	(2, 'le goff', 'isabel', 'commedienne et directrice'),
	(3, 'rilliot', 'celine', 'metteuse en scène, régisseuse, secretaire general');
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;

-- Listage de la structure de la table stage. membre_has_role
CREATE TABLE IF NOT EXISTS `membre_has_role` (
  `Membre_idMembre` int(11) NOT NULL,
  `role_idrole` int(11) NOT NULL,
  PRIMARY KEY (`Membre_idMembre`,`role_idrole`),
  KEY `fk_Membre_has_role_role1` (`role_idrole`),
  CONSTRAINT `fk_Membre_has_role_Membre1` FOREIGN KEY (`Membre_idMembre`) REFERENCES `membre` (`idMembre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Membre_has_role_role1` FOREIGN KEY (`role_idrole`) REFERENCES `role` (`idrole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.membre_has_role : ~8 rows (environ)
/*!40000 ALTER TABLE `membre_has_role` DISABLE KEYS */;
INSERT INTO `membre_has_role` (`Membre_idMembre`, `role_idrole`) VALUES
	(1, 1),
	(1, 2),
	(1, 12),
	(2, 1),
	(2, 3),
	(3, 2),
	(3, 4),
	(3, 5);
/*!40000 ALTER TABLE `membre_has_role` ENABLE KEYS */;

-- Listage de la structure de la table stage. message
CREATE TABLE IF NOT EXISTS `message` (
  `idmessage` int(11) NOT NULL AUTO_INCREMENT,
  `emailenvoyeur` varchar(45) NOT NULL,
  `message` varchar(45) NOT NULL,
  `traite` tinyint(4) NOT NULL,
  PRIMARY KEY (`idmessage`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.message : ~2 rows (environ)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`idmessage`, `emailenvoyeur`, `message`, `traite`) VALUES
	(1, 'fboni.78@gmail.com', 'bite', 1),
	(2, 'francois.boni@efrei.net', 'test', 0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

-- Listage de la structure de la table stage. photo
CREATE TABLE IF NOT EXISTS `photo` (
  `idphoto` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(45) NOT NULL,
  `article_idarticle` int(11) DEFAULT NULL,
  `Membre_idMembre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idphoto`),
  UNIQUE KEY `idphoto_UNIQUE` (`idphoto`),
  KEY `fk_photo_article` (`article_idarticle`),
  KEY `fk_photo_Membre1` (`Membre_idMembre`),
  CONSTRAINT `fk_photo_Membre1` FOREIGN KEY (`Membre_idMembre`) REFERENCES `membre` (`idMembre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_photo_article` FOREIGN KEY (`article_idarticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.photo : ~16 rows (environ)
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` (`idphoto`, `url`, `article_idarticle`, `Membre_idMembre`) VALUES
	(1, 'papa1.jpg', NULL, 1),
	(2, 'maman1?png', NULL, 2),
	(3, 'maraine1', NULL, 3),
	(4, 'articlepic.jpg', NULL, NULL),
	(5, 'article2.jpg', NULL, 1),
	(6, 'photo.png', NULL, NULL),
	(7, 'testInsert1', NULL, NULL),
	(8, 'testIsert2', NULL, 1),
	(9, 'testinsert3', NULL, 2),
	(12, 'photo', 7, NULL),
	(13, 'photo1', 7, NULL),
	(14, 'photo2', 7, NULL),
	(15, 'phtot1', 8, NULL),
	(16, 'phtot2', 8, NULL),
	(17, 'phtot3', 8, NULL),
	(18, 'francois.jpg', NULL, NULL);
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;

-- Listage de la structure de la table stage. repertoire
CREATE TABLE IF NOT EXISTS `repertoire` (
  `idrepertoire` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `auteur` varchar(45) NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`idrepertoire`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.repertoire : ~4 rows (environ)
/*!40000 ALTER TABLE `repertoire` DISABLE KEYS */;
INSERT INTO `repertoire` (`idrepertoire`, `nom`, `auteur`, `active`) VALUES
	(1, 'le plaisir de rompre', 'jule renard', 0),
	(2, 'le pain de menage', 'jule renard', 0),
	(3, 'l\'ours', 'tchekov', 1),
	(4, 'le fusil', 'tchekov', 1);
/*!40000 ALTER TABLE `repertoire` ENABLE KEYS */;

-- Listage de la structure de la table stage. repertoire_has_membre
CREATE TABLE IF NOT EXISTS `repertoire_has_membre` (
  `repertoire_idrepertoire` int(11) NOT NULL,
  `Membre_idMembre` int(11) NOT NULL,
  PRIMARY KEY (`repertoire_idrepertoire`,`Membre_idMembre`),
  KEY `fk_repertoire_has_Membre_Membre1` (`Membre_idMembre`),
  CONSTRAINT `fk_repertoire_has_Membre_Membre1` FOREIGN KEY (`Membre_idMembre`) REFERENCES `membre` (`idMembre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_repertoire_has_Membre_repertoire1` FOREIGN KEY (`repertoire_idrepertoire`) REFERENCES `repertoire` (`idrepertoire`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.repertoire_has_membre : ~8 rows (environ)
/*!40000 ALTER TABLE `repertoire_has_membre` DISABLE KEYS */;
INSERT INTO `repertoire_has_membre` (`repertoire_idrepertoire`, `Membre_idMembre`) VALUES
	(1, 1),
	(1, 2),
	(2, 1),
	(2, 2),
	(3, 1),
	(3, 2),
	(4, 1),
	(4, 2);
/*!40000 ALTER TABLE `repertoire_has_membre` ENABLE KEYS */;

-- Listage de la structure de la table stage. reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `idresa` int(11) NOT NULL AUTO_INCREMENT,
  `nom_reservation` varchar(45) NOT NULL,
  `nb_place` int(11) NOT NULL,
  `spectacle_idspectacle` int(11) NOT NULL,
  PRIMARY KEY (`idresa`),
  KEY `reservation_fk` (`spectacle_idspectacle`),
  CONSTRAINT `reservation_fk` FOREIGN KEY (`spectacle_idspectacle`) REFERENCES `spectacle` (`idspectacle`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.reservation : ~2 rows (environ)
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`idresa`, `nom_reservation`, `nb_place`, `spectacle_idspectacle`) VALUES
	(1, 'les gens', 2, 1),
	(2, 'd\'autre gens', 33, 2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;

-- Listage de la structure de la table stage. role
CREATE TABLE IF NOT EXISTS `role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.role : ~6 rows (environ)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`idrole`, `nom`) VALUES
	(1, 'commedient'),
	(2, 'metteur en scene'),
	(3, 'presidente'),
	(4, 'secretaire'),
	(5, 'regisseuse'),
	(12, 'dev');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Listage de la structure de la table stage. spectacle
CREATE TABLE IF NOT EXISTS `spectacle` (
  `idspectacle` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `dates` date NOT NULL,
  `nbplace` int(11) NOT NULL,
  `place_restante` int(11) NOT NULL,
  `prix` varchar(45) NOT NULL,
  `repertoire_idrepertoire` int(11) NOT NULL,
  PRIMARY KEY (`idspectacle`),
  KEY `fk_spectacle_repertoire1` (`repertoire_idrepertoire`),
  CONSTRAINT `fk_spectacle_repertoire1` FOREIGN KEY (`repertoire_idrepertoire`) REFERENCES `repertoire` (`idrepertoire`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.spectacle : ~2 rows (environ)
/*!40000 ALTER TABLE `spectacle` DISABLE KEYS */;
INSERT INTO `spectacle` (`idspectacle`, `nom`, `dates`, `nbplace`, `place_restante`, `prix`, `repertoire_idrepertoire`) VALUES
	(1, 'premier', '2021-01-12', 120, 69, '1euros', 1),
	(2, 'second', '2021-01-13', 120, 28, '2euros', 2);
/*!40000 ALTER TABLE `spectacle` ENABLE KEYS */;

-- Listage de la structure de la table stage. users
CREATE TABLE IF NOT EXISTS `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Listage des données de la table stage.users : ~3 rows (environ)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`idusers`, `username`, `password`, `email`) VALUES
	(1, 'admin', 'admin', 'fboni.78@gmail.com'),
	(2, 'maman', '456', 'mail@test.fr'),
	(4, 'papa', '1234', 'mail@test.fr');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
