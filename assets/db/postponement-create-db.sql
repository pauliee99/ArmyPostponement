-- first connect as root 
-- =====================


-- -----------------------------------------------------
-- Schema postponement
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `postponement` ;

CREATE SCHEMA IF NOT EXISTS `postponement` DEFAULT CHARACTER SET utf8 ;


-- -----------------------------------------------------
-- User postponement
-- -----------------------------------------------------
DROP USER IF EXISTS `postponement` ;

-- CREATE USER `postponement`@`localhost` IDENTIFIED BY 'changeit' ;
-- DROP USER `postponement`@`localhost` ;

-- CREATE USER `postponement`@`%` IDENTIFIED BY 'changeit' ;
-- GRANT ALL PRIVILEGES ON *.* TO `postponement`@`%` ;

CREATE USER `postponement`@`%` IDENTIFIED BY 'changeit' ;
GRANT ALL PRIVILEGES ON postponement.* TO `postponement`@`%` ;

SHOW GRANTS for `postponement`@`%` ;

USE `postponement` ;

commit ;
