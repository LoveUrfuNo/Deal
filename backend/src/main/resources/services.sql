CREATE TABLE `deal_users`.`services` (
  `id` INT(11) NOT NULL,
  `service_name` VARCHAR(65) NOT NULL,
  `username` VARCHAR(45) NOT NULL DEFAULT 'name',
  `cost` INT(10) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `description` VARCHAR(3001) NULL,
  `country` VARCHAR(65) NOT NULL,
  `city` VARCHAR(65) NOT NULL,
  `type_of_service` ENUM('vip', 'notvip') NOT NULL DEFAULT 'notvip',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
