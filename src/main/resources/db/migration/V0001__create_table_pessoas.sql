CREATE TABLE IF NOT EXISTS `pessoas` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(50) NOT NULL,
  `email` VARCHAR(45),
  `sexo` VARCHAR(50),
  `data_nascimento` DATE NOT NULL,
  `naturalidade` VARCHAR(50),
  `nacionalidade` VARCHAR(45),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC))
ENGINE = InnoDB;