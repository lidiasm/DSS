CREATE TABLE `dss`.`usuario` (
  `username` VARCHAR(250) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `name` VARCHAR(45) NULL,
  `lat` DOUBLE NULL,
  `lon` DOUBLE NULL,
  `preferencias` VARCHAR(45) NULL,
  `pedidos` VARCHAR(500) NULL,
  `carrito` VARCHAR(500) NULL,
  `rol` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`username`));

CREATE TABLE `dss`.`juguete` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `minEdadRecomendada` INT NULL,
  `precio` DOUBLE NULL,
  `almacen` INT NULL,
  `unidades` INT NULL,
  PRIMARY KEY (`id`));