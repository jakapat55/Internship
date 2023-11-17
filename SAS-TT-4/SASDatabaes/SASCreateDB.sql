SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE SCHEMA IF NOT EXISTS `sas` DEFAULT CHARACTER SET utf8 ;
USE `sas` ;
CREATE TABLE IF NOT EXISTS `sas`.`categories` (
  `categoryId` INT NOT NULL,
  `categoryName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `sas`.`announcements` (
  `announcementId` INT NOT NULL AUTO_INCREMENT,
  `announcementTitle` VARCHAR(200) NOT NULL,
  `announcementDescription` VARCHAR(10000) NOT NULL,
  `publishDate` DATETIME NULL,
  `closeDate` DATETIME NULL,
  `announcementDisplay` ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`announcementId`),
  INDEX `fk_announcements_categorys_idx` (`categoryId` ASC) VISIBLE,
  CONSTRAINT `fk_announcements_categorys`
    FOREIGN KEY (`categoryId`)
    REFERENCES `sas`.`categories` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- TT4 

alter table announcements 
add viewCount SMALLINT default 0;


use SAS;
TRUNCATE TABLE announcements ;

insert into announcements (announcementTitle,announcementDescription, categoryId, publishDate, closeDate, announcementDisplay) values 
('(PBI10) Title 0','(PBI10) Description 0',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 1','(PBI10) Description 1',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 2','(PBI10) Description 2',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 3','(PBI10) Description 3',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 4','(PBI10) Description 4',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 5','(PBI10) Description 5',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 6','(PBI10) Description 6',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 7','(PBI10) Description 7',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 8','(PBI10) Description 8',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 9','(PBI10) Description 9',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 10','(PBI10) Description 10',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 11','(PBI10) Description 11',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 12','(PBI10) Description 12',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 13','(PBI10) Description 13',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 14','(PBI10) Description 14',1,'2023-05-01 06:00:00+07:00','2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 16','(PBI10) Description 16',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 17','(PBI10) Description 17',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 18','(PBI10) Description 18',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 19','(PBI10) Description 19',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 20','(PBI10) Description 20',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 21','(PBI10) Description 21',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 22','(PBI10) Description 22',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 23','(PBI10) Description 23',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 24','(PBI10) Description 24',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 25','(PBI10) Description 25',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 26','(PBI10) Description 26',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 27','(PBI10) Description 27',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 28','(PBI10) Description 28',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 29','(PBI10) Description 29',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 30','(PBI10) Description 30',1,null,'2023-12-31 18:00:00+07:00','Y'),
('(PBI10) Title 31','(PBI10) Description 31',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 32','(PBI10) Description 32',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 33','(PBI10) Description 33',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 34','(PBI10) Description 34',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 35','(PBI10) Description 35',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 36','(PBI10) Description 36',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 37','(PBI10) Description 37',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 38','(PBI10) Description 38',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 39','(PBI10) Description 39',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 40','(PBI10) Description 40',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 41','(PBI10) Description 41',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 42','(PBI10) Description 42',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 43','(PBI10) Description 43',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 44','(PBI10) Description 44',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 45','(PBI10) Description 45',2,'2023-05-01 06:00:00+07:00',null,'Y'),
('(PBI10) Title 46','(PBI10) Description 46',2,null,null,'Y'),
('(PBI10) Title 47','(PBI10) Description 47',2,null,null,'Y'),
('(PBI10) Title 48','(PBI10) Description 48',2,null,null,'Y'),
('(PBI10) Title 49','(PBI10) Description 49',2,null,null,'Y'),
('(PBI10) Title 50','(PBI10) Description 50',2,null,null,'Y'),
('(PBI10) Title 51','(PBI10) Description 51',2,null,null,'Y'),
('(PBI10) Title 52','(PBI10) Description 52',2,null,null,'Y'),
('(PBI10) Title 53','(PBI10) Description 53',2,null,null,'Y'),
('(PBI10) Title 54','(PBI10) Description 54',2,null,null,'Y'),
('(PBI10) Title 55','(PBI10) Description 55',2,null,null,'Y'),
('(PBI10) Title 56','(PBI10) Description 56',2,null,null,'Y'),
('(PBI10) Title 57','(PBI10) Description 57',2,null,null,'Y'),
('(PBI10) Title 58','(PBI10) Description 58',2,null,null,'Y'),
('(PBI10) Title 59','(PBI10) Description 59',2,null,null,'Y'),
('(PBI10) Title 60','(PBI10) Description 60',2,null,null,'Y'),
('(PBI10) Title 61','(PBI10) Description 61',3,null,'2023-05-01 18:00:00+07:00','Y'),
('(PBI10) Title 62','(PBI10) Description 62',3,'2023-05-01 06:00:00+07:00','2023-05-01 18:00:00+07:00','Y'),
('(PBI10) Title 63','(PBI10) Description 63',3,null,'2023-05-01 18:00:00+07:00','N'),
('(PBI10) Title 64','(PBI10) Description 64',3,'2023-06-01 06:00:00+07:00','2023-12-01 18:00:00+07:00','Y'),
('(PBI10) Title 65','(PBI10) Description 65',3,'2023-06-01 06:00:00+07:00',null,'Y')
